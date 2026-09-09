package project;
import java.util.Scanner;
public class LibraryManagementSystem {
    static int n = 0;
    static String[] books = new String[100];
    static String[] authors = new String[100];
    static boolean[] isAvailable = new boolean[100];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add a book");
            System.out.println("2. Check out a book");
            System.out.println("3. Return a book");
            System.out.println("4. List all books");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                addBook();
            } else if (choice == 2) {n
                checkOutBook();
            } else if (choice == 3) {
                returnBook();
            } else if (choice == 4) {
                listBooks();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    public static void listBooks() {
        for (String book : books) {
            System.out.println(book);
        }
    }
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the book: ");
        String book = scanner.nextLine();
        System.out.print("Enter the name of the author: ");
        String author = scanner.nextLine();
        try {
            books[n] = book;
            authors[n] = author;
            isAvailable[n] = true;
            n++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Book added successfully");
    }
    public static void checkOutBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the book: ");
        String book = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (books[i].equals(book)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Book not found");
        } else if (!isAvailable[index]) {
            System.out.println("Book is not available");
        } else {
            isAvailable[index] = false;
            books[index] = null;
            System.out.println("Book checked out successfully");
        }
    }
    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the book: ");
        String book = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (books[i] == null) {
                books[i] = book;
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Book not found");
        } else {
            System.out.println("Book is already available");
        }
    }
}


