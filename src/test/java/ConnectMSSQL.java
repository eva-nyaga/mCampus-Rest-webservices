/**
 * ConnectMSSQL.java
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
/**
 * @author www.javaworkspace.com
 *
 */
public class ConnectMSSQL {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
                    .getConnection(
                            "jdbc:sqlserver://192.180.3.2:1433;databaseName=cl_integrate;selectMethod=cursor",
                            "sa", "YesoBa1002");
 
            System.out.println("DATABASE NAME IS:"
                    + connection.getMetaData().getDatabaseProductName());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM dbo.smart_members");
            while (resultSet.next()) {
                System.out.println("First NAME:"
                        + resultSet.getString("FirstName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}