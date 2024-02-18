package my.maximilyan.budgetApp.budgetServices.budgetServiceImpl;

import my.maximilyan.budgetApp.budgetServices.BudgetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BudgetServiceImpl implements BudgetService {
    public static final int SALARY = 20_000;
    public static final double AVG_DAYS = 29.3;
    /**
     * средняя зарплата за год
     */
    public static final int AVG_SALARY = (
            10000 + 10000 + 10000 + 10000 + 10000 +
                    15000 + 15000 + 15000 + 15000 + 15000 +
                    15000 + 20000) / 12;

    @Override
    public int getDailyBudget() {
        return SALARY / 30;
    }

    @Override
    public int getBalance() {
        return LocalDate.now().getDayOfMonth() * getDailyBudget();
    }

    @Override
    public int getVacationBonus(int daysCount) {
        double avgDaysSalary = AVG_SALARY / AVG_DAYS;
        return (int) (daysCount * avgDaysSalary);
    }

    @Override
    public int getSalaryWithVacation(
            int vacationDaysCount,
            int vacationWorkingDaysCount,
            int workingDaysInMonth) {

        int salary = SALARY / workingDaysInMonth * (workingDaysInMonth - vacationWorkingDaysCount);
        return salary + getVacationBonus(vacationDaysCount);
    }
}
