package serv;

        import java.lang.*;
        import java.io.*;
        import java.net.*;

class Server {

    public static void main(String args[]) {
        new Server();
    }

    public Server(){
        ServerSocket serverSocket = null;
        int clientNo = 0;// Number a client

        try {
            serverSocket = new ServerSocket(1234);
            System.out.print("Server Waiting...\n");

            while(true){
                Socket skt = serverSocket.accept();
                //System.out.println("Just connected to " + skt.getRemoteSocketAddress());
                System.out.println("Client No: " + clientNo);
                Thread sc = new ServerConnection(skt);
                clientNo = Thread.activeCount(); // Increment clientNo
                sc.start();
            }

        }
        catch(Exception e) {
            System.out.print("Whoops!\n");
            e.printStackTrace();
        }
        finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } //try
                catch (IOException e) {

                } //catch
            } //if
        } //finally
    }

}//server class