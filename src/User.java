import java.sql.*;

public class User {
    static String userId;
    public static boolean InsertUser (String name, String personalNumber, String address, String phoneNumber, String password, String email){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (name, personal_number, address, phone_number, password, email) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setString(1, name);
            stmt.setString(2, personalNumber);
            stmt.setString(3, address);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, password);
            stmt.setString(6, email);

            int result = stmt.executeUpdate();

            if (result > 0){
                System.out.println("Välkommen " + name + ", ditt konto är nu skapat!");
            } else {
                System.out.println("Det gick inte att skapa din användare...");
            }

            conn.close();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Failed");
        }
        return false;
    }
    public static boolean LoginUser(String personalNumber, String password){
        try {
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE personal_number = ? and password = ?");

            stmt.setString(1, personalNumber);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            boolean hasUser = rs.next();

            if (hasUser){
                userId = String.valueOf(rs.getInt("id"));
            }

            return hasUser;

        } catch (SQLException e) {
            System.out.println("Login Failed");
        }
        return false;
    }
    public static void FullSummary (String userId){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE id = ?");

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            Account.ShowLoggedInAccounts(userId);

            while (rs.next()) {
                int user_Id = rs.getInt("id");
                String userName = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String created = rs.getString("created");

                System.out.println("Användare ID: " + user_Id);
                System.out.println("Account Name: " + userName);
                System.out.println("Adress: " + address);
                System.out.println("Telefon nummer: " + phoneNumber);
                System.out.println("Email: " + email);
                System.out.println("Skapat: " + created);
            }

        } catch (Exception e) {
            System.out.println("Failed");
        }
    }
    public static boolean DeleteUser (String personalNumber, String password){
        try {
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM users WHERE personal_number = ? and password = ?");

            stmt.setString(1, personalNumber);
            stmt.setString(2, password);

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
    public static boolean UpdateUser (String name, String password, String phoneNumber, String address, String email){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE users SET name = ?, password = ?, phone_number = ?, address = ?, email = ? WHERE id = 4");

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, address);
            stmt.setString(5, email);

            int result = stmt.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            System.out.println("Kunde inte uppdatera");
        }
        return false;
    }
}
