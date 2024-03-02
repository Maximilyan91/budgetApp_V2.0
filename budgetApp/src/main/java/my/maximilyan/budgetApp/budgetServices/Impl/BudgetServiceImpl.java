package my.maximilyan.budgetApp.budgetServices.Impl;

import my.maximilyan.budgetApp.budgetServices.BudgetService;
import my.maximilyan.budgetApp.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BudgetServiceImpl implements BudgetService {
    public static final int SALARY = 20_000;
    public static final double AVG_DAYS = 29.3;
    public static final int SAVING = 3000;

    public static final int DAILY_BUDGET = (SALARY - SAVING) / LocalDate.now().lengthOfMonth();
    public static final int AVG_SALARY = SALARY;
    public static int balance = 0;
    /**
     * Используется TreeMap так как она по умолчанию отсорирована
     */
    private static Map<Month, Map<Long, Transaction>> transactions = new TreeMap<>();

    private static long lastId = 0;

    @Override
    public int getDailyBudget() {
        return DAILY_BUDGET;
    }

    @Override
    public int getBalance() {
        return SALARY - SAVING - getAllSpend();
    }

    @Override
    public long addTransaction(Transaction transaction) {
        /*Если на момент вызова метода Treemap transactions пуста,
        благодаря методу getOrDefault() создается новая транзакция.
        LinkedHashMap<>() выбрана из-за упорядоченности данных
         */
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        monthTransactions.put(lastId, transaction);
        return lastId++;
    }

    @Override
    public Transaction getTransaction(long id) {
        for (Map<Long, Transaction> transactionByMonth : transactions.values()) {
            Transaction transaction = transactionByMonth.get(id);
            if (transaction != null) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public int getDailyBalance() {
        return DAILY_BUDGET * LocalDate.now().getDayOfMonth() - getAllSpend();
    }

    @Override
    public int getAllSpend() {
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        int sum = 0;
        for (Transaction transactions : monthTransactions.values()) {
            sum += transactions.getSum();
        }
        return sum;
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
