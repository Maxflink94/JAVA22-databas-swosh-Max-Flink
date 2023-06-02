import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Model {

    private static MysqlDataSource dataSource;
    static String url = "localhost";
    static int port = 3306;
    static String database = "swoshdb";
    static String username = "root";
    static String password = "";

    private static void InitializeDatabase(){
        try{
            System.out.print("Configuring data source...");
            dataSource = new MysqlDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setURL("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC");
            dataSource.setUseSSL(false);
            System.out.print("done!\n");

        } catch (Exception e) {
            System.out.println("Failed!\n");
        }
    }

    protected static Connection GetConnection() {
        if (dataSource == null){
            InitializeDatabase();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.print("Failed!\n");
        }
        return null;
    }

    protected static void CreateTables() throws SQLException {

        Connection conn = GetConnection();

        assert conn != null;
        Statement stmt = conn.createStatement();

        String userTableQuery = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), personal_number VARCHAR(12), address VARCHAR(50), phone_number VARCHAR(10), password VARCHAR(256), email VARCHAR(50), created TIMESTAMP default CURRENT_TIMESTAMP);";
        String accTableQuery = "CREATE TABLE IF NOT EXISTS account (id INT PRIMARY KEY AUTO_INCREMENT, account_name VARCHAR(50), balance DECIMAL, user_id INT, created TIMESTAMP default CURRENT_TIMESTAMP);";
        String transTableQuery = "CREATE TABLE IF NOT EXISTS transactions (id INT PRIMARY KEY AUTO_INCREMENT, sender_account VARCHAR(50), receiver_account VARCHAR(50), amount DECIMAL, user_id INT, timestamp TIMESTAMP default CURRENT_TIMESTAMP);";

        int userResult = stmt.executeUpdate(userTableQuery);
        int accResult = stmt.executeUpdate(accTableQuery);
        int transResult = stmt.executeUpdate(transTableQuery);

        System.out.println("Tabeller skapade " + userResult + " " + accResult + " " + transResult);
        conn.close();
    }
}
