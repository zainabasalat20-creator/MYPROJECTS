import java.util.*;


enum ExpenseType { 
	//if user need to add or modify expense type then can only modify here.
	//no shotgun surgery
    DINNER("Dinner", true, 5000), //renamed  expense types 
    BREAKFAST("Breakfast", true, 1000),
    CAR_RENTAL("Car Rental", false, 0);

    private final String displayName; 
    private final boolean isMeal;     //encapsulated 
    private final int limit;          //encapsulated  

    ExpenseType(String displayName, boolean isMeal, int limit) {
        this.displayName = displayName;
        this.isMeal = isMeal;
        this.limit = limit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isMeal() {
        return isMeal;
    }

    public boolean isOverLimit(int amount) {
        return amount > limit;
    }
}
//class money for calculation of amount instead of integer
class Money { 
    private final int amount; //encapsulated 

    public Money(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative.");
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + " PKR";
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }
}
 //deals with expenses only 
class Expense { 
    private final ExpenseType type;
    private final Money amount;

    public Expense(ExpenseType type, Money amount) {
        this.type = type;
        this.amount = amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public Money getAmount() {
        return amount;
    }

    public boolean isMeal() {
        return type.isMeal();
    }

    public boolean isOverLimit() {
        return type.isOverLimit(amount.getAmount());
    }

    public String getDisplayName() {
        return type.getDisplayName();
    }
}

public class ExpenseReport {

    public void printReport(List<Expense> expenses) {
        Money total = new Money(0);
        Money mealTotal = new Money(0);

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealTotal = mealTotal.add(expense.getAmount());
            }

            String marker = expense.isOverLimit() ? "X" : " ";
            System.out.println(expense.getDisplayName() + "\t" + expense.getAmount() + "\t" + marker);

            total = total.add(expense.getAmount());
        }

        System.out.println("Meal expenses: " + mealTotal);
        System.out.println("Total expenses: " + total);
    }

    public static void main(String[] args) {
    	//hard coded values changed to dynamic value by taking input
        Scanner scanner = new Scanner(System.in);
        List<Expense> expenses = new ArrayList<>();

        System.out.print("Enter number of expenses: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter expense type (DINNER, BREAKFAST, CAR RENTAL): ");
            String typeInput = scanner.nextLine().trim().toUpperCase();

            ExpenseType type;
            try {
                type = ExpenseType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Skipping entry.");
                continue;
            }

            System.out.print("Enter amount (PKR): ");
            int amount = scanner.nextInt();
            scanner.nextLine();

            expenses.add(new Expense(type, new Money(amount)));
        }

        ExpenseReport report = new ExpenseReport();
        report.printReport(expenses);
    }
}
