import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Brain.Program();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}