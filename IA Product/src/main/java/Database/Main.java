package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main
{

    public static Connection getConnection() throws Exception
    {
        try
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String ip = "jdbc:mysql://localhost:3306/IA";
            String usr = "root";
            String pwd = "localkey";
            Class.forName(driver);

            Connection connection = DriverManager.getConnection(ip, usr, pwd);
            return connection;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<String> get(int index) throws Exception
    {
        try
        {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT name, amount, type FROM Catalog;");

            ResultSet result = statement.executeQuery();

            ArrayList<String> output = new ArrayList<>();

            while (result.next())
            {
                output.add(result.getString(index));
            }

            System.out.println(output);
            return output;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static int getAmount(String name) throws Exception {
        try
        {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT amount FROM Catalog WHERE name = \"" + name + "\";");

            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                return result.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }


    //Catalog` SET `amount` = '64' WHERE (`id` = '1');

    public static void update(String name, int amount) throws Exception
    {
        try
        {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE Catalog SET amount = " + amount + " WHERE name = \"" + name + "\";");

            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public static void main(String[] args) throws Exception {
        getAmount("Redstone");
    }

}
