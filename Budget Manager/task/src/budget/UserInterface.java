package budget;

import java.io.*;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    private BudgetManager bm;
    Context context;
    Category category;


    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.bm = new BudgetManager();
        this.context = new Context();
    }


    public void start() throws IOException, ClassNotFoundException {
        while (true) {
            printOptions();
            int action = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (action) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    printPurchaseListMenu();
                    System.out.println();
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                  // serialize(bm, "D:\\variousTests\\purchases.txt");
                      serialize(bm, "purchases.txt");
                    System.out.println("Purchases were saved!\n"); //
                    break;
                case 6:
                   // bm = (BudgetManager) deserialize("D:\\variousTests\\purchases.txt");
                        bm = (BudgetManager) deserialize("purchases.txt");
                    System.out.println("Purchases were loaded!\n"); //
                    break;
                case 7:
                    printPurchaseAnalyzerMenu();
                    break;
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);

            }
        }
    }

    private void printOptions() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
    }

    private void addIncome() {
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        scanner.nextLine();
        bm.addIncome(income);
        System.out.println("Income was added!\n");
    }

    private void showBalance() {
        System.out.printf("Balance: $%.2f\n%n", bm.getBalance());
    }

    private void addPurchase() {
        while (true) {

            printOptions1to4();
            System.out.println("5) Back");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 5) {
                return;
            }
            System.out.println();
            System.out.println("Enter purchase name:");
            String name = scanner.nextLine();
            System.out.println();
            System.out.println("Enter its price:");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println();
            Purchase purchase = new Purchase(name, price, category.findValueById(choice));
            bm.addPurchase(purchase);
            System.out.println("Purchase was added!\n");

        }
    }

    private void printPurchaseListMenu() {
        while (true) {
            printOptions1to4();
            printOptions5to6();
            int choice = scanner.nextInt();
            System.out.println();
            if (choice == 5) {
                context.setMethod(new PrintAllLists());
                context.sort(bm, false, category.findValueById(choice));
            } else if (choice == 6) {
                return;
            } else {
                context.setMethod(new PrintListByCategory());
                context.sort(bm, false, category.findValueById(choice));
            }
        }
    }

    private void printOptions1to4() {
        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
    }

    private void printOptions5to6() {
        System.out.println("" +
                "5) All\n" +
                "6) Back");
    }




    void printPurchaseAnalyzerMenu() throws IOException, ClassNotFoundException {

        while (true) {
            System.out.println("" +
                    "How do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println();
            switch (choice) {
                case 1:
                    context.setMethod(new PrintAllLists());
                    context.sort(bm, true, Category.ALL);
                    System.out.println();
                    break;
                case 2:
                    context.setMethod(new PrintTotalOfEachList());
                    context.sort(bm, true, Category.ALL);
                    System.out.println();
                    break;
                case 3:
                    printOptions1to4();
                    int option = scanner.nextInt();
                    System.out.println();
                    context.setMethod(new PrintListByCategory());
                    context.sort(bm, true, category.findValueById(option));
                    System.out.println();
                    break;
                case 4:
                    start();
            }

        }
    }


    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
