package my.maximilyan.budgetApp.controllers;

import my.maximilyan.budgetApp.budgetServices.BudgetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @RequestMapping("/daily")
    public int dailyBudget() {
        return budgetService.getDailyBudget();
    }

    @RequestMapping("/balance")
    public int balance() {
        return budgetService.getBalance();

    }

    @GetMapping("/vacation")
    public int vacationBonus(@RequestParam int vacationDays) {
        return budgetService.getVacationBonus(vacationDays);
    }

    @RequestMapping("/vacation/salary")
    public int salaryWithVacation(
            @RequestParam int vacationDays,
            @RequestParam int workingDays,
            @RequestParam int vacWorkingDays) {

        return budgetService.getSalaryWithVacation(vacationDays, vacWorkingDays, workingDays);
    }
}
