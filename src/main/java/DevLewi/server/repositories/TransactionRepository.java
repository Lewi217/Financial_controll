package DevLewi.server.repositories;

import DevLewi.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Transaction obj WHERE obj.user.id = :userId ORDER BY obj.id DESC")
    List<Transaction> findTransactions(@Param("userId") Integer userId);
}
