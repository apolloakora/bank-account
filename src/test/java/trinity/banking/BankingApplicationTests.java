package trinity.banking;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import trinity.banking.dao.AccountDAO;
import trinity.banking.daoimpl.AccountImpl;
import trinity.banking.model.Account;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static trinity.banking.model.Sex.Female;
import static org.junit.jupiter.api.Assertions.assertEquals;



@RunWith(MockitoJUnitRunner.class)
class BankingApplicationTests {



	@Test
	public void createAccount() {
			Account account = new Account();
			account.setId(23);
			account.setFullName("Mary");
			account.setBalance(324.56);
			account.setSex(Female);
			assertEquals(23,account.getId());
			assertEquals("Mary",account.getFullName());
			assertEquals(324.56,account.getBalance());
			assertEquals(Female,account.getSex());

		}
	@Test
	public void listAllAccounts() {

			Account account = new Account();
			account.setId(23);
			account.setFullName("Mary");
			account.setBalance(987.56);
			account.setSex(Female);
			assertEquals(23,account.getId());
			assertEquals("Mary",account.getFullName());
			assertEquals(987.56,account.getBalance());
			assertEquals(Female,account.getSex());

		}

	}




























