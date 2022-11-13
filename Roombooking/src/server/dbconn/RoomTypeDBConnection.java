package server.dbconn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDBConnection {

    private static final String DBURL = "jdbc:mysql://localhost/r_b_s";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String JDBCDRIVER = "com.mysql.jdbc.Driver";


    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static Connection getConnection(){
        try {
            return DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }catch (SQLException e){
            processException(e);
            return null;
        }
    }

    public static List<String> readDataFromDB(String sql) throws SQLException{
        System.out.println(sql);
        List<String> myResult = new ArrayList<>();
        try{

            try {
                //Step 2. Regsiter JDBC driver
                Class.forName(JDBCDRIVER);
                System.out.println("MySQL JDBC Driver Registered!");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is you, MySQL JDBC Driver?");
                e.printStackTrace();
            }

            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //get number of rows
            resultSet.last();
            int nRows = resultSet.getRow();
            if(0 == nRows){
                System.out.println("NO rows!");
                return null;
            }else{
                System.out.println("number of rows: " + resultSet.getRow());
                resultSet.beforeFirst();
                System.out.println();

                //Step 5. Extract data from result set
                while(resultSet.next()){
                    final String myRType = resultSet.getString("typeOfRoom");
                    myResult.add(myRType);
                }
            }
        }catch (SQLException e){
            processException(e);
        }finally
        {
            if(resultSet != null){
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

    private static void processException(SQLException e){
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL state: " + e.getSQLState());

        }


}
