import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Account extends Model {
    public static void ShowLoggedInAccounts(String userId){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM account WHERE user_id = ?");

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int accountId = rs.getInt("id");
                String accName = rs.getString("account_name");
                float balance = rs.getFloat("balance");

                System.out.println("Account ID: " + accountId);
                System.out.println("Account Name: " + accName);
                System.out.println("Balance: " + balance);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Failed");
        }
    }
    public static boolean InsertAccount (String accName, float balance, int id){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO account (account_name, balance, user_id) VALUES (?, ?, ?)");

            stmt.setString(1, accName);
            stmt.setFloat(2, balance);
            stmt.setInt(3, id);

            int result = stmt.executeUpdate();

            if (result > 0){
                System.out.println("Konto: " + accName + ", För användare " + id + " är nu skapat");
            } else {
                System.out.println("Det gick inte att skapa ditt konto...");
            }

            return result > 0;

        } catch (Exception e) {
            System.out.println("Failed");
        }
        return false;
    }
    public static boolean DeleteAccount (String accName){
        try {
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM account WHERE account_name = ?");

            stmt.setString(1, accName);

            int result = stmt.executeUpdate();

            if (result > 0){
                System.out.println("Ditt konto är nu raderat!");
            } else {
                System.out.println("Det gick inte att radera ditt konto...");
            }

            conn.close();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Failed to delete!");
        }
        return false;
    }
}
