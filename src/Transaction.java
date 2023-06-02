import java.sql.*;

public class Transaction extends Model {
    public static void MakeTransaction(String fromAccName, String toAccName, float amount, String userId) {
        try {
            float fromAccBalance = GetAccBalance(fromAccName);

            if (fromAccBalance >= amount) {
                UpdateAccBalance(fromAccName, fromAccBalance - amount);

                float toAccBalance = GetAccBalance(toAccName);

                UpdateAccBalance(toAccName, toAccBalance + amount);

                System.out.println("Transaktionen lyckades. " + amount + " överfördes från " + fromAccName + " till " + toAccName);

                InsertTransaction(fromAccName, toAccName, amount, userId);
            } else {
                System.out.println("Transaktionen misslyckades. Avsändarkontot har inte tillräckligt med saldo.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void TransHistory(String userId, String fromDate, String toDate){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM transactions WHERE user_id = ? AND timestamp BETWEEN ? AND ? ORDER BY timestamp DESC");

            stmt.setString(1, userId);
            stmt.setString(2, fromDate);
            stmt.setString(3, toDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String senderAcc = rs.getString("sender_account");
                String receiverAcc = rs.getString("receiver_account");
                String amount = rs.getString("amount");
                String created = rs.getString("timestamp");

                System.out.println("Transaktion från " + senderAcc + " till " + receiverAcc + " på " + amount + "kr, gjordes " + created);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static float GetAccBalance(String accName) throws SQLException {
        Connection conn = Model.GetConnection();

        assert conn != null;
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT balance FROM account WHERE account_name = ?");

        stmt.setString(1, accName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getFloat("balance");
        } else {
            return 0;
        }
    }
    private static void UpdateAccBalance(String accName, float newBalance) throws SQLException {
        Connection conn = Model.GetConnection();

        assert conn != null;
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE account SET balance = ? WHERE account_name = ?");
        stmt.setFloat(1, newBalance);
        stmt.setString(2, accName);
        stmt.executeUpdate();
    }
    private static void InsertTransaction(String fromAccName, String toAccName, float amount, String userId){
        try{
            Connection conn = Model.GetConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO transactions (sender_account, receiver_account, amount, user_id) VALUES (?, ?, ?, ?)");

            stmt.setString(1, fromAccName);
            stmt.setString(2, toAccName);
            stmt.setFloat(3, amount);
            stmt.setString(4, userId);

            int result = stmt.executeUpdate();
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Failed");
        }
    }
}
