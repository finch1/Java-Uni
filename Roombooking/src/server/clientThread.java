package server;

import com.Request;
import com.Response;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class clientThread extends Thread {

    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private Socket clientSocket = null;
    private final clientThread[] threads;
    private int maxClientsCount;

    public clientThread(Socket clientSocket, clientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        clientThread[] threads = this.threads;

        try {
      /*
       * Create input and output streams for this client.
       */
            is = new ObjectInputStream(clientSocket.getInputStream());
            os = new ObjectOutputStream(clientSocket.getOutputStream());

      /* Welcome the new the client. */

      /* Start the conversation. */
            while (true) {
                System.out.println("Server waiting for data");
                Request inCome = (Request) is.readObject();
                if (clientSocket.isClosed()) {
                    break;
                }

          /* The message is public, broadcast it to all other clients. */
                synchronized (this) {
                    System.out.println("Server transmitting data");
                    Object outGo = new Response(inCome).payload;
                    os.writeObject(outGo);
                }

            }//while waiting for quit

      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
      /*
       * Close the output stream, close the input stream, close the socket.
       */
            is.close();
            os.close();
            clientSocket.close();
        } catch (ClassNotFoundException | IOException e) {
        }
    }

}
