package DevLewi.server.resources;

import DevLewi.server.services.UserService;
import DevLewi.server.model.User;
import DevLewi.server.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/email")
    public User findByEmail(@RequestParam(value = "value") String email) {
        return userService.findByEmail(email);
    }

    @CrossOrigin
    @PostMapping
    public User insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @PutMapping(value = "/{id}")
    public User update(@RequestBody User user, @PathVariable Integer id) {
        return userService.update(user, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}/transactions")
    public List<Transaction> findUserTransactions(@PathVariable Integer id) {
        return userService.findUserTransactions(id);
    }

    @CrossOrigin
    @PostMapping(value = "/{id}/transactions")
    public Transaction insertTransaction(@RequestBody Transaction transaction, @PathVariable Integer id) {
        return userService.insertTransaction(transaction, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}/transactions/{transactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Integer id, @PathVariable Integer transactionId) {
        userService.deleteTransaction(id, transactionId);
    }
}
