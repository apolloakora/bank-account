package trinity.banking.controller;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import trinity.banking.daoimpl.AccountImpl;
import trinity.banking.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired  private JmsTemplate jmsTemplate;
    @Autowired  private AccountImpl accountImpl;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ACCOUNT_SID = "AC070cf485fa849ca33a23b45a7be24172";
    private static final String AUTH_TOKEN = "61ce923d1d9bc03fec93a427200ebca6";

     @RequestMapping("/accounts")
       public List<Account> getAccount(){
        return accountImpl.getAllAccounts();
    }


     @RequestMapping(method = RequestMethod.PUT,value = "/accounts")
     @ResponseStatus (value= HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account){
         accountImpl.createAccount(account);
                  return account;
    }

    @RequestMapping(method=RequestMethod.PUT,value="/accounts/{id}")
    @ResponseStatus(value =HttpStatus.FOUND)
    public void creditAccount(@RequestBody Account account,@PathVariable int id) {
        accountImpl.creditAccount(id, account); }

            @RequestMapping(value="/accounts/{id}")
        public Optional<Account> getAccount(@PathVariable("id") Integer id) {
         if(id==null){
             return null;
         }
            return accountImpl.getAccount(id);

        }

    @RequestMapping(method = RequestMethod.POST, value = "/sms/{id}")
    public void sendSMS(@PathVariable int id) {
        Account transaction = new Account();
        logger.debug("Transaction+" + transaction);
        Optional<Account> account = accountImpl.getAccount(id);
        if (account.isPresent()) {
            transaction = account.get();
        } else {
            return;//fail
        }
        logger.info("Sending sms message", +id);
        jmsTemplate.convertAndSend("Account transition queue", transaction);
        accountImpl.creditAccount(id, transaction);
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("Body", "This is a test"));
            params.add(new BasicNameValuePair("To", " +447415778821"));
            params.add(new BasicNameValuePair("From", "+441923750441"));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println(message.getSid());
        } catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }

    }

}
























