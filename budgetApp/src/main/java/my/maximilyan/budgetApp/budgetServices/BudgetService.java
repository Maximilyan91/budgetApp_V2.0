package my.maximilyan.budgetApp.budgetServices;

import org.springframework.stereotype.Service;


public interface BudgetService {

    int getDailyBudget();

    int getBalance();

    int getVacationBonus(int daysCount);

    int getSalaryWithVacation(
            int vacationDaysCount,
            int vacationWorkingDaysCount,
            int workingDaysInMonth);
}
