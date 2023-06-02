import java.sql.SQLException;
import java.util.Scanner;
public class Brain {
    static Scanner scanner;
    public static void Program() throws SQLException {
        Model.CreateTables();

        scanner = new Scanner(System.in);
        boolean run = true;

        while(run){
            System.out.println();
            System.out.println("1 - Logga in");
            System.out.println("2 - Skapa ny användare");

            switch (scanner.nextLine()){
                case "1" -> LoggedInMenu();
                case "2" -> CreateUser();
                default -> run = false;
            }
        }
    }
    public static void LoggedInMenu(){

        System.out.println();
        System.out.println("Ditt Personnummer: ");
        String personalNumber = scanner.nextLine();
        System.out.println("Ditt Lösenord: ");
        String password = scanner.nextLine();

        boolean run = User.LoginUser(personalNumber, password);

        while(run){
            System.out.println();
            System.out.println("1 - Dina konto");
            System.out.println("2 - Transaktioner");
            System.out.println("3 - Uppdatera användaruppgifter");
            System.out.println("4 - Full summering av ditt konto");

            System.out.println("5 - Ta bort användare");
            System.out.println("6 - Logga ut");

            switch (scanner.nextLine()){
                case "1" -> AccountMenu();
                case "2" -> TransactionMenu();
                case "3" -> TaskUpdateUser();
                case "4" -> User.FullSummary(User.userId);
                case "5" -> {
                    RemoveUser();
                    run = false;
                }
                case "6" -> run = false;
                default -> {}
            }
        }
    }
    public static void AccountMenu(){

        boolean run = true;

        while(run){
            System.out.println();
            System.out.println("1 - Visa dina konto");
            System.out.println("2 - Skapa ett konto");
            System.out.println("3 - Ta bort ett konto");
            System.out.println("4 - Tillbaka");

            switch (scanner.nextLine()){
                case "1" -> Account.ShowLoggedInAccounts(User.userId);
                case "2" -> CreateAccount();
                case "3" -> RemoveAccount();
                case "4" -> run = false;
            }
        }
    }
    public static void TransactionMenu(){
        boolean run = true;

        while(run){
            System.out.println();
            System.out.println("1 - Gör en transaktion");
            System.out.println("2 - Visa transaktionshistorik från ÅÅÅÅ-MM-DD till ÅÅÅÅ-MM-DD");
            // TODO Gör så att man kan sortera transaktionerna med datum och printa ut dem

            System.out.println("3 - Tillbaka");

            switch (scanner.nextLine()){
                case "1" -> Transaction();
                case "2" -> TransDateHistory();
                case "3" -> run = false;
            }
        }
    }
    public static void CreateUser(){
        System.out.println();
        System.out.println("Användarnamn: ");
        String name = scanner.nextLine();
        System.out.println("Lösenord: ");
        String password = scanner.nextLine();
        System.out.println("Personnummer (ÅÅMMDDNNNN): ");
        String personalNumber = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Adress: ");
        String address = scanner.nextLine();
        System.out.println("Telefon nummer: ");
        String number = scanner.nextLine();

        boolean result = User.InsertUser(name, personalNumber, address, number, password, email);

        if (result) {
            System.out.println(name + " - " + personalNumber);
        } else {
            System.out.println("Failed to create");
        }
    }
    public static void CreateAccount(){
        System.out.println();
        System.out.println("Konto namn: ");
        String accName = scanner.nextLine();
        System.out.println("Hur mycket pengar ska finnas på kontot: ");
        float balance = Float.parseFloat(scanner.nextLine());
        System.out.println("Användare: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean result = Account.InsertAccount(accName, balance, id);

        if (result) {
            System.out.println(accName + " - " + balance);
        } else {
            System.out.println("Failed to create");
        }
    }
    public static void RemoveUser(){
        System.out.println("Personnummer: ");
        String personalNumber = scanner.nextLine();
        System.out.println("Lösenord: ");
        String password = scanner.nextLine();

        boolean result = User.DeleteUser(personalNumber, password);

        if (result){
            System.out.println("Användare med pers nr: " + personalNumber + ", har tagits bort.");

        } else {
            System.out.println("Failed to delete");
        }
    }
    public static void RemoveAccount(){
        System.out.println("Kontonamn: ");
        String accName = scanner.nextLine();

        boolean result = Account.DeleteAccount(accName);

        if (result){
            System.out.println("Konto med kontonamn: " + accName + ", har tagits bort.");

        } else {
            System.out.println("Failed to delete");
        }
    }
    public static void TaskUpdateUser(){

        System.out.println("Uppdatera namn till: ");
        String name = scanner.nextLine();
        System.out.println("Uppdatera lösenord till: ");
        String password = scanner.nextLine();
        System.out.println("Uppdatera telefonnummer till: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Uppdatera adress till: ");
        String address = scanner.nextLine();
        System.out.println("Uppdatera email till: ");
        String email = scanner.nextLine();

        boolean result = User.UpdateUser(name, password, phoneNumber, address, email);

        if (result){
            System.out.println("Nytt användarnamn: " + name);
            System.out.println("Lösenord uppdaterat");
            System.out.println("Nytt telefonnummer: " + phoneNumber);
            System.out.println("Ny adress: " + address);
            System.out.println("Ny email: " + email);
        } else {
            System.out.println("Failed to update");
        }
    }
    public static void Transaction(){

        System.out.println("Vilket konto vill du överföra från: ");
        String fromAccName = scanner.nextLine();
        System.out.println("Vilket konto vill du överföra till: ");
        String toAccName = scanner.nextLine();
        System.out.println("Vilken summa vill du överföra: ");
        float amount = Float.parseFloat(scanner.nextLine());

        Transaction.MakeTransaction(fromAccName, toAccName, amount, User.userId);

        System.out.println("Transaktionen genomförd!");
    }
    public static void TransDateHistory(){
        System.out.println("Vilket startdatum vill du kolla ifrån: ");
        String fromDate = scanner.nextLine();
        System.out.println("Till vilket datum: ");
        String toDate = scanner.nextLine();

        Transaction.TransHistory(User.userId, fromDate, toDate);
    }
}
