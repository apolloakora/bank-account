package trinity.banking.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import trinity.banking.dao.AccountDAO;
import trinity.banking.model.Account;

@Component
public class AccountTransactionReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountDAO accountDAO;

    @JmsListener(destination = "AccountTransactionQueue",containerFactory = "myFactory")
    public void receiveSMS(Account transition){
       logger.info("Received"+"\t"+transition);
       accountDAO.save(transition);

    }

}
