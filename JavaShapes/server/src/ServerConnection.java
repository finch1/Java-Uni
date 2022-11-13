package serv;

import java.io.*;
import java.net.*;
import java.util.*;


//handle connection
public class ServerConnection extends Thread{
    Socket socket = null;

    DataInputStream din = null;
    DataOutputStream dout = null;

    ObjectInputStream serverIn = null;
    ObjectInputStream fileIn = null;

    ObjectOutputStream fileOut = null;
    ObjectOutputStream objOut = null;

    FileOutputStream toFile = null;

    FileInputStream fromFile = null;

    File clientFile = null;

    ArrayList<ArrayList<Object[]>> fileObjArr = new ArrayList<ArrayList<Object[]>>();
    ArrayList<ArrayList<Object[]>> sktObjArr = new ArrayList<ArrayList<Object[]>>();

    int instruction;
    String fileName;
    int option;

    final static String[] sendMessage = {   "Server sent Rectangle List!!\n",
                                            "Server sent Circle List!!\n",
                                            "Server sent Triangle List!!\n",
                                            "Server sent Shapes List!!\n"}; //type content


    public ServerConnection(Socket socket){
        super("ServerConnectionThread");
        this.socket = socket;
    }

    public void run(){
        try {

            din = new DataInputStream(socket.getInputStream());
            instruction = din.readInt();
            fileName = din.readUTF();

            System.out.println("Instr: " + instruction); //determines save or load
            System.out.println("File: " + fileName);

            switch (instruction) {
                case 1:
                    storeShapes();
                    break;
                case 2:
                    loadShapes();
                    break;
                default:
                    break;
            }
        }//try
        catch (IOException e) {
            e.printStackTrace();
        }//catch

        finally{
            try {
                if (din != null) {din.close(); System.out.println("din.close");}
                if (dout != null) {dout.close(); System.out.println("dout.close");}
                if (socket != null) {socket.close(); System.out.println("socket.close");}
                if (serverIn != null) {serverIn.close(); System.out.println("serverIn.close");}
                if (fileOut != null) {fileOut.close(); System.out.println("fileOut.close");}
                if (objOut != null) {objOut.close(); System.out.println("objOut.close");}
                if (toFile != null) {toFile.close(); System.out.println("toFile.close");}
            }catch (IOException e) {
                e.printStackTrace();
            }
        }//finally
    }//run

    private boolean checkFileExists() {

        try {
            clientFile = new File(fileName + ".txt");
            System.out.println("User Request File Name: " + clientFile.getPath());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return clientFile.exists();
    }

    private void readFromSocket() {
        try{
            serverIn = new ObjectInputStream(socket.getInputStream()); //get object stream
            sktObjArr = (ArrayList<ArrayList<Object[]>>) serverIn.readObject(); //deserialize
            //dout.writeUTF("\nGot your data!!\n");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }

    private void writeToSocket(int index) {
        try{
            objOut = new ObjectOutputStream(socket.getOutputStream()); //write object to client
            if(index == 3){
                    objOut.writeObject(fileObjArr);
            }
            else {
                objOut.writeObject(fileObjArr.get(index));
            }
            objOut.flush(); //write everything to file
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private void readFromFile() {
        try{
            fromFile = new FileInputStream(clientFile);
            fileIn = new ObjectInputStream(fromFile);
            fileObjArr = (ArrayList<ArrayList<Object[]>>) fileIn.readObject();
            fromFile.close();  //close file
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }


    }

    private void writeToFile(){
        try{
            toFile = new FileOutputStream(clientFile); //save to new file
            fileOut = new ObjectOutputStream(toFile); //write to the new file
            fileOut.writeObject(sktObjArr);

            System.out.print("OK got it and saved!!\n");
            fileOut.flush(); //write everything to file
            toFile.close();  //close file
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private void storeShapes(){
        try {
            readFromSocket();

            if(checkFileExists()){
                dout = new DataOutputStream(socket.getOutputStream()); //inform client
                dout.writeUTF("\nFile already exists!!\n");
                dout.writeInt(1); //notify Client that file exists already
                dout.flush();

                instruction = din.readInt(); //get user decision


                if( instruction == 1){//append true
                    //dout.writeUTF("\nData shall be appended!!\n");
                    readFromFile();
                    //combine arrays + iterate through all five //ArrayList<Object[]> onlyRect = fileObjArr.get(0); //ArrayList<Object[]> tempRect = sktObjArr.get(0); //onlyRect.addAll(tempRect);
                    for(int index = 0; index < fileObjArr.size(); index++) {
                        fileObjArr.get(index).addAll(sktObjArr.get(index));
                    }
                    sktObjArr = fileObjArr; //cause write to file only works with sktObjArr
                }

                if( instruction == 2){//overwrite
                    dout.writeUTF("\nData shall be overwritten!!\n");
                    dout.flush();
                }

                if( instruction == 3){//call file method again
                    instruction = 1;
                    storeShapes();
                }
            }
            else{ //write to a new file name
                dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF("\nData will be saved to new file!!\n");
                dout.writeInt(0); //notify Client that file doesn't exist
                dout.flush();
            }

            writeToFile();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void loadShapes(){
        try {
            if(checkFileExists()){

                dout = new DataOutputStream(socket.getOutputStream()); //inform client
                dout.writeUTF("\nFile found!!\n");
                dout.writeInt(1); //notify Client that file exists already
                dout.flush();

                instruction = din.readInt(); //get user decision

                readFromFile();
                writeToSocket(instruction);
                System.out.println();
            }
            else{
                dout = new DataOutputStream(socket.getOutputStream()); //inform client
                dout.writeUTF("Sorry file name not found!!\n\n");
                dout.writeInt(0); //notify Client that file doesn't exist
                dout.flush();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}//Thread
