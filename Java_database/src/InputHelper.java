import java.io.BufferedReader;
import java.io.InputStreamReader;


public class InputHelper {

    private static String getInput(String prompt){
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in)
        );
        System.out.print(prompt);
        System.out.flush();

        try{
            return stdin.readLine();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    public static int getIntInput(String prompt)throws NumberFormatException{

        String input = getInput(prompt);
        return Integer.parseInt(input);
    }

    public static String getStringInput(String prompt){

        String input = getInput(prompt);
        return input;
    }

    public static float getFloatInput(String prompt)throws NumberFormatException{

        String input = getInput(prompt);
        return Float.parseFloat(input);
    }

}
