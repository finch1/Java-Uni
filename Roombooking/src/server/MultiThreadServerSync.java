package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * A server that delivers messages.
 */
public class MultiThreadServerSync {

    // The server socket.
    private static ServerSocket serverSocket = null;
    // The client socket.
    private static Socket socket = null;
    // This  server can accept up to maxClientsCount clients' connections.
    private static final int maxClientsCount = 10;
    // Array of threads
    private static final clientThread[] threads = new clientThread[maxClientsCount];

    public static void main(String args[]) {

        // The default port number.
        int portNumber = 2345;

        System.out.println("Usage: Now using port number = " + portNumber);

        /*
         * Open a server socket on the portNumber (default 2345).
         */
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
         * Create a client socket for each connection and pass it to a new client
         * thread.
         */
        while (true) {
            try {
                    System.out.println("Server Waiting");
                    socket = serverSocket.accept();
                    int i;
                    for (i = 0; i < maxClientsCount; i++) {
                        if (threads[i] == null) {
                            (threads[i] = new clientThread(socket, threads)).start();
                            break;
                        }
                    }
                    if (i == maxClientsCount) {
                        PrintStream os = new PrintStream(socket.getOutputStream());
                        os.println("Server too busy. Try later.");
                        os.close();
                        socket.close();
                    }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}


