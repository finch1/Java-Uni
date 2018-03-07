import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HotelDBConn {

    private static  Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static Connection getConnection(dbtype dbType) throws SQLException{

        switch(dbType){
            case MYSQL:
                return DriverManager.getConnection(dbutil.DBURL, dbutil.USERNAME, dbutil.PASSWORD);
            default:
                return null;
        }
    }

    public static void executeCommand(int rows)throws SQLException{
        System.out.println("Rows affected: " + rows);
    }

    public static List<Hotel> readDataFromDB(String sql) throws SQLException{
        System.out.println(sql);
        List<Hotel> myResult = new ArrayList<>();
        try{

            try {
                //Step 2. Regsiter JDBC driver
                Class.forName(dbutil.JDBCDRIVER);
                System.out.println("MySQL JDBC Driver Registered!");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is you, MySQL JDBC Driver?");
                e.printStackTrace();
            }

            connection = getConnection(dbtype.MYSQL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //get number of rows
            resultSet.last();
            int nRows = resultSet.getRow();
            if(0 == nRows){
                System.out.println("NO rows mate!");
                return null;
            }else{
                System.out.println("number of rows: " + resultSet.getRow());
                resultSet.beforeFirst();
                System.out.println();

                //Step 5. Extract data from result set
                while(resultSet.next()){
                    final Hotel myHotel = new Hotel(resultSet.getInt("hotelNo"), resultSet.getString("hotelName"), resultSet.getString("city"));
                    myResult.add(myHotel);
                }
            }
        }catch (SQLException e){
            processException(e);
        }finally
        {
            if(resultSet != null)
            {
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return myResult;
    }

    public static void writeDataToDB(String sql) throws SQLException{
        System.out.println(sql);
        try{

            try {
                //Step 2. Regsiter JDBC driver
                Class.forName(dbutil.JDBCDRIVER);
                System.out.println("MySQL JDBC Driver Registered!");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is you, MySQL JDBC Driver?");
                e.printStackTrace();
            }

            connection = getConnection(dbtype.MYSQL);
            statement = connection.createStatement();
            executeCommand(statement.executeUpdate(sql));

        }catch (SQLException e){
            processException(e);
        }finally {

            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }

    }

    public static void processException(SQLException e){
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());

    }

}
