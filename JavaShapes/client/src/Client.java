package clnt;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Client {

    DataInputStream dis = null;
    DataOutputStream dos = null;

    ObjectOutputStream outToServer = null;

    Socket client = null;

    enum convert{r, c, t, a};

    int userInput;


    //send to server
    public Client(ArrayList<ArrayList<Object[]>> contents, int instruction, String fileName){


        try {   //open socket
            client = new Socket("127.0.0.1", 1234); //open communication endpoint
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeInt(instruction); //instruct server to save
            dos.writeUTF(fileName); //advice filename

            outToServer = new ObjectOutputStream(client.getOutputStream()); //send shapes
            outToServer.writeObject(contents);
            outToServer.flush();

            dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF()); //server response on file existence

            if(dis.readInt() == 1){ //if filename exists
                System.out.println("1 - Append file\n2 - Overwrite file\n3 - Try a new Name\n4 - Exit");
                try {
                    Scanner keyVal = new Scanner(System.in);
                    userInput = keyVal.nextInt();

                    if(userInput == 3)
                    {
                        //enter new file name
                    }
                    else if(userInput < 1 || userInput > 3){
                        System.out.println("invalid input");
                    }
                    else{
                        dos = new DataOutputStream(client.getOutputStream());
                        dos.writeInt(userInput);
                    }

                }
                catch (InputMismatchException ex){
                    System.out.println("Invalid input!!");
                }
            }//if



        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
        }
        finally {
            try {
                if (outToServer != null) {outToServer.close(); System.out.println("outToServer.close");}
                if (client != null) {client.close(); System.out.println("client.close");}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //get from server
    public Client(int instruction, String fileName, String option){
        try {
            client = new Socket("127.0.0.1", 1234); //open communication endpoint
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeInt(instruction);
            dos.writeUTF(fileName);

            dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF()); //server response on file life

            if(dis.readInt() == 1){//if file found by server, send index from enum
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeInt(convert.valueOf(option).ordinal());


                ObjectInputStream inFromServer = new ObjectInputStream(client.getInputStream());
                Object object = inFromServer.readObject();

                Store serverStore = new Store();

                serverStore.shapeDisplay(object, option);

                inFromServer.close();

                client.close();
            }
        }
        catch (IOException e) {
            System.out.print("Whoops! no connection!\n");
            e.printStackTrace();
        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
        }

    }
}
