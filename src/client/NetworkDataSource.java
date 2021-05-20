package client;

import client.UserDataSource;
import server.database.JBDCDataSource.Entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class NetworkDataSource implements UserDataSource {
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 10000;

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * These are the commands which will be sent across the network connection.
     */
    public enum Command {
        ADD_USER,
        EDIT_USER,
        DELETE_USER,
        GET_USER,
        GET_USERS,
        GET_NAME_SET,
        GET_SIZE,
    }

    public NetworkDataSource() {
        try {
            // Persist a single connection through the whole lifetime of the application.
            // We will re-use this same connection/socket, rather than repeatedly opening
            // and closing connections.
            socket = new Socket(HOSTNAME, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // If the server connection fails, we're going to throw exceptions
            // whenever the application actually tries to query anything.
            // But it wasn't written to handle this, so make sure your
            // server is running beforehand!
            System.out.println("Failed to connect to server");
        }
    }

    public void addUser(User u) {
        if (u == null)
            throw new IllegalArgumentException("User cannot be null");

        try {
            // tell the server to expect a person's details
            outputStream.writeObject(Command.ADD_USER);

            // send the actual data
            outputStream.writeObject(u);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        /**
         * Protocol documentation might look like:
         * GET_SIZE:
         *  - No parameters
         *
         * Server responds with:
         *   - (int) number of entries
         */
        try {
            outputStream.writeObject(Command.GET_SIZE);
            outputStream.flush();

            // read the person's details back from the server
            return inputStream.readInt();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deleteUser(String name) {
        /**
         * Protocol documentation might look like:
         * DELETE_PERSON:
         *  - (String) the person to remove
         *
         * Server does not respond.
         */

        try {
            outputStream.writeObject(Command.DELETE_USER);
            outputStream.writeObject(name);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void close() {
    }


    public Set<String> nameSet() {
        try {
            outputStream.writeObject(Command.GET_NAME_SET);
            outputStream.flush();
            return (Set<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }


    public User getUser(String name) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_USER);
            outputStream.writeObject(name);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Set<String> getUsers() {
        try {
            outputStream.writeObject(Command.GET_USERS);
            outputStream.flush();
            return (Set<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
