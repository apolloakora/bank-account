package trinity.banking.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trinity.banking.model.Account;

import java.util.Optional;

@Repository
public interface AccountDAO extends CrudRepository<Account,Integer> {


    Optional<Account> findById(Integer id);






}