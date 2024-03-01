package my.maximilyan.budgetApp.controllers;

import my.maximilyan.budgetApp.budgetServices.BudgetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final BudgetService budgetService;

    public VacationController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public int vacationBonus(@RequestParam int vacationDays) {
        return budgetService.getVacationBonus(vacationDays);
    }

    @RequestMapping("/salary")
    public int salaryWithVacation(
            @RequestParam int vacationDays,
            @RequestParam int workingDays,
            @RequestParam int vacWorkingDays) {

        return budgetService.getSalaryWithVacation(vacationDays, vacWorkingDays, workingDays);
    }
}
