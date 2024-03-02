package my.maximilyan.budgetApp.controllers;

import my.maximilyan.budgetApp.budgetServices.BudgetService;
import my.maximilyan.budgetApp.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final BudgetService budgetService;

    public TransactionController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }


    @PostMapping
    public ResponseEntity<Long> addTransaction(@RequestBody Transaction transaction) {
        long id = budgetService.addTransaction(transaction);
        return ResponseEntity.ok(id);
    }

    /**В данном методе параметр id будет передаваться
     * в URL в виде "http://localhost:8080/transaction/3"
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long id) {
        Transaction transaction = budgetService.getTransaction(id);
        if (transaction == null) {
          return   ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }
}
