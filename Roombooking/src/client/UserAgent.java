package client;

import com.Request;
import com.Response;
import com.Room;
import com.Booking;
import com.User;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Stream;

public class UserAgent{

    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static ObjectOutputStream os = null;
    // The input stream
    private static ObjectInputStream is = null;


    public UserAgent() {
        // The default port.
        int portNumber = 2345;
        // The default host.
        String host = "localhost";

    /*
     * Open a socket on a given host and port. Open input and output streams.
     */

        try {
            clientSocket = new Socket(host, portNumber);
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }
    }

     /*
     * Client sends request to server and waits for response.
     */

    public Object processRequest(Request _request){
        Object _response = null;
        if (clientSocket != null && os != null && is != null) {
            try {
                    os.writeObject(_request);
                    _response = is.readObject();

            } catch (ClassNotFoundException | IOException e) {
                System.err.println("IOException:  " + e);
            }
        }

        return _response;
    }


}
