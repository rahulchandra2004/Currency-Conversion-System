import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Currency {

    private String name;
    private String shortName;
    private HashMap<String, Double> exchangeValues = new HashMap<String, Double>();

    public Currency(String nameValue, String shortNameValue) {
        this.name = nameValue;
        this.shortName = shortNameValue;
    }

    public String getName() { return this.name; }
    public String getShortName() { return this.shortName; }
    public HashMap<String, Double> getExchangeValues() { return this.exchangeValues; }


    public void defaultValues() {
        switch (this.name) {
            case "US Dollar":
                this.exchangeValues.put("USD", 1.00);
                this.exchangeValues.put("EUR", 0.94);
                this.exchangeValues.put("GBP", 0.79);
                this.exchangeValues.put("CHF", 0.90);
                this.exchangeValues.put("CNY", 7.10);
                this.exchangeValues.put("JPY", 150.00);
                this.exchangeValues.put("INR", 83.50);
                break;

            case "Euro":
                this.exchangeValues.put("USD", 1.06);
                this.exchangeValues.put("EUR", 1.00);
                this.exchangeValues.put("GBP", 0.84);
                this.exchangeValues.put("CHF", 0.96);
                this.exchangeValues.put("CNY", 7.55);
                this.exchangeValues.put("JPY", 159.00);
                this.exchangeValues.put("INR", 88.80);
                break;

            case "British Pound":
                this.exchangeValues.put("USD", 1.27);
                this.exchangeValues.put("EUR", 1.19);
                this.exchangeValues.put("GBP", 1.00);
                this.exchangeValues.put("CHF", 1.14);
                this.exchangeValues.put("CNY", 9.00);
                this.exchangeValues.put("JPY", 189.00);
                this.exchangeValues.put("INR", 106.00);
                break;

            case "Swiss Franc":
                this.exchangeValues.put("USD", 1.11);
                this.exchangeValues.put("EUR", 1.04);
                this.exchangeValues.put("GBP", 0.88);
                this.exchangeValues.put("CHF", 1.00);
                this.exchangeValues.put("CNY", 7.90);
                this.exchangeValues.put("JPY", 165.00);
                this.exchangeValues.put("INR", 92.50);
                break;

            case "Chinese Yuan Renminbi":
                this.exchangeValues.put("USD", 0.14);
                this.exchangeValues.put("EUR", 0.13);
                this.exchangeValues.put("GBP", 0.11);
                this.exchangeValues.put("CHF", 0.13);
                this.exchangeValues.put("CNY", 1.00);
                this.exchangeValues.put("JPY", 21.00);
                this.exchangeValues.put("INR", 11.75);
                break;

            case "Japanese Yen":
                this.exchangeValues.put("USD", 0.0067);
                this.exchangeValues.put("EUR", 0.0063);
                this.exchangeValues.put("GBP", 0.0053);
                this.exchangeValues.put("CHF", 0.0061);
                this.exchangeValues.put("CNY", 0.048);
                this.exchangeValues.put("JPY", 1.00);
                this.exchangeValues.put("INR", 0.56);
                break;

            case "Indian Rupee":
                this.exchangeValues.put("USD", 0.012);
                this.exchangeValues.put("EUR", 0.011);
                this.exchangeValues.put("GBP", 0.0094);
                this.exchangeValues.put("CHF", 0.011);
                this.exchangeValues.put("CNY", 0.085);
                this.exchangeValues.put("JPY", 1.80);
                this.exchangeValues.put("INR", 1.00);
                break;
        }
    }


    public static ArrayList<Currency> init() {
        ArrayList<Currency> currencies = new ArrayList<Currency>();

        currencies.add(new Currency("US Dollar", "USD"));
        currencies.add(new Currency("Euro", "EUR"));
        currencies.add(new Currency("British Pound", "GBP"));
        currencies.add(new Currency("Swiss Franc", "CHF"));
        currencies.add(new Currency("Chinese Yuan Renminbi", "CNY"));
        currencies.add(new Currency("Japanese Yen", "JPY"));
        currencies.add(new Currency("Indian Rupee", "INR"));

        for (Currency c : currencies) {
            c.defaultValues();
        }
        return currencies;
    }

    public static Double convert(Double amount, Double exchangeValue) {
        Double price = amount * exchangeValue;
        price = Math.round(price * 100d) / 100d;
        return price;
    }


    public static void main(String[] args) {
        ArrayList<Currency> currencies = Currency.init();
        Scanner scanner = new Scanner(System.in);


        System.out.println("=======================================");
        System.out.println("       CURRENCY CONVERSION SYSTEM      ");
        System.out.println("=======================================\n");

        while (true) {
            System.out.println("=== Currency Converter Menu ===");
            System.out.println("1. List all currencies");
            System.out.println("2. Convert currency");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Currencies:");
                    for (int i = 0; i < currencies.size(); i++) {
                        System.out.println((i + 1) + ". " + currencies.get(i).getName() +
                                " (" + currencies.get(i).getShortName() + ")");
                    }
                    break;

                case 2:
                    System.out.println("\nSelect source currency:");
                    for (int i = 0; i < currencies.size(); i++) {
                        System.out.println((i + 1) + ". " + currencies.get(i).getName());
                    }
                    int srcIndex = scanner.nextInt() - 1;

                    System.out.println("Select target currency:");
                    for (int i = 0; i < currencies.size(); i++) {
                        System.out.println((i + 1) + ". " + currencies.get(i).getName());
                    }
                    int tgtIndex = scanner.nextInt() - 1;

                    String sourceShortName = currencies.get(srcIndex).getShortName();
                    String targetShortName = currencies.get(tgtIndex).getShortName();

                    System.out.print("Enter the " + sourceShortName + " value you want to change to " + targetShortName + ": ");
                    Double amount = scanner.nextDouble();

                    Double exchangeRate = currencies.get(srcIndex).getExchangeValues().get(targetShortName);
                    Double converted = Currency.convert(amount, exchangeRate);

                    System.out.println(amount + " " + sourceShortName +
                            " = " + converted + " " + targetShortName);

                 
                    System.out.println("\nPress Enter to continue...");
                    try {
                        System.in.read(); 
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("\n=======================================");
                    System.out.println(" Thank you for using Currency Conversion System ");
                    System.out.println("=======================================");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
