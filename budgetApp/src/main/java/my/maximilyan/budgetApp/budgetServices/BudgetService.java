package my.maximilyan.budgetApp.budgetServices;

import my.maximilyan.budgetApp.model.Transaction;


public interface BudgetService {

    int getDailyBudget();

    int getBalance();

    long addTransaction(Transaction transaction);

    int getDailyBalance();

    int getAllSpend();

    int getVacationBonus(int daysCount);

    int getSalaryWithVacation(
            int vacationDaysCount,
            int vacationWorkingDaysCount,
            int workingDaysInMonth);
}
