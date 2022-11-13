import java.lang.*;
import java.sql.*;


public class main {
    public static void main(String[] args) throws SQLException{

         new main();
    }

    private main(){
        int selection;

    while (true) {
        System.out.print(   "0.SELECT – retrieve data from the a database\n" +
                            "1.INSERT – insert data into a table\n" +
                            "2.UPDATE – updates existing data within a table\n" +
                            "3.DELETE – Delete all records from a database table\n");

        selection = InputHelper.getIntInput("Select option: ");
        switch (selection){
            case 0:
                View.read();
                break;
            case 1:
                View.create();
                break;
            case 2:
                View.update();
                break;
            case 3:
                View.delete();
                break;
            default:
                System.out.println("Wrong input mate!");
                break;
        }

        }//while
    }//main
}//class
