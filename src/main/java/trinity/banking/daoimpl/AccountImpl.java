package trinity.banking.daoimpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import trinity.banking.dao.AccountDAO;
import trinity.banking.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountDAO accountDAO;


    public List<Account> getAllAccounts() {
        logger.debug("GetAccounts account called");
        List<Account> accounts = new ArrayList<>();
        accountDAO.findAll()
                .forEach(accounts::add);
        return accounts;
    }

    public Account createAccount(Account account) {

        return accountDAO.save(account);
    }

    public void creditAccount(int id, Account account) {
        logger.debug("CreditAccount called");

        accountDAO.save(account);
    }


    public Optional getAccount(int id) {
        //Optional<Account> account = accountDAO.findById(id);
        return accountDAO.findById(id);

    }
}

