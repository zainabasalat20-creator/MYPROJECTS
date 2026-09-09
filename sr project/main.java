
import java.util.Date;
import java.util.List;
import java.util.Arrays;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    ExpenseType type;
    int amount;

    Expense(ExpenseType type, int amount) {
        try {
            this.type = type;
            this.amount = amount;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        extracted();

        try {
            for (Expense expense : expenses) {
                if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                    mealExpenses += expense.amount;
                }

                String expenseName = "";
                switch (expense.type) {
                    case DINNER:
                        expenseName = "Dinner";
                        break;
                    case BREAKFAST:
                        expenseName = "Breakfast";
                        break;
                    case CAR_RENTAL:
                        expenseName = "Car Rental";
                        break;
                }

                String marker = (expense.type == ExpenseType.DINNER && expense.amount > 5000) ||
                                (expense.type == ExpenseType.BREAKFAST && expense.amount > 1000) ? "X" : " ";

                System.out.println(expenseName + "\t" + expense.amount + "\t" + marker);

                total += expense.amount;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

    private void extracted() {
        System.out.println("Expenses " + new Date());
    }
}

