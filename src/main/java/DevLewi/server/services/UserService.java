package DevLewi.server.services;

import DevLewi.server.model.User;
import DevLewi.server.model.Transaction;
import DevLewi.server.repositories.UserRepository;
import DevLewi.server.repositories.TransactionRepository;
import DevLewi.server.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User insert(User user) {
        return userRepo.save(user);
    }

    public User update(User user, Integer id) {
        User existingUser = findById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setBalance(user.getBalance());
        existingUser.setRevenue(user.getRevenue());
        existingUser.setExpenses(user.getExpenses());
        return userRepo.save(existingUser);
    }

    public void delete(Integer id) {
        userRepo.deleteById(id);
    }

    public List<Transaction> findUserTransactions(Integer userId) {
        return transactionRepo.findTransactions(userId);
    }

    public Transaction insertTransaction(Transaction transaction, Integer userId) {
        User user = findById(userId);
        transaction.setUser(user);
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Integer userId, Integer transactionId) {
        Transaction transaction = transactionRepo.findById(transactionId).orElseThrow(() -> new ObjectNotFoundException("Transaction not found"));
        if (!transaction.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Transaction does not belong to user");
        }
        transactionRepo.deleteById(transactionId);
    }
}
