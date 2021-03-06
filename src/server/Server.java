package server;
//What up what up
import client.gui.clientData.NetworkDataSource;
import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.entity.Organisation;
import client.model.entity.User;
import server.database.schema.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This is the Server which listens to specific commands from NetworkDataSource in the clientData
 * package from the client side
 */
public class Server {
    private static int PORT;

    /**
     * this is the timeout inbetween accepting clients, not reading from the socket itself.
     */
    private static final int SOCKET_ACCEPT_TIMEOUT = 100;

    /**
     * This timeout is used for the actual clients.
     */
    private static final int SOCKET_READ_TIMEOUT = 5000;

    private AtomicBoolean running = new AtomicBoolean(true);

    /**
     * The connection to the database where everything is stored.
     */
    private JBDCAssetDataSource assetDatabase;
    private JBDCListingDataSource listingDatabase;
    private JBDCInventoryDataSource inventoryDatabase;
    private JBDCOrganisationDataSource organisationDatabase;
    private JBDCUserDataSource userDatabase;

    public Server(){
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./src/server/properties/connection.props");
            props.load(in);
            in.close();

            PORT = Integer.parseInt(props.getProperty("con.port"));

        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the connection received from ServerSocket.
     * Does not return until the client disconnects.
     * @param socket The socket used to communicate with the currently connected client
     */
    private void handleConnection(Socket socket) {
        try {
            /**
             * We create the streams once at connection time, and re-use them until the client disconnects.
             * This **must** be in the opposite order to the client, because creating an ObjectInputStream
             * reads data, and an ObjectOutputStream writes data.
             */
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            /**
             * while(true) here might seem a bit confusing - why do we have an infinite loop?
             * That's because we don't want to exit until the client disconnects, and when they do, readObject()
             * will throw an IOException, which will cause this method to exit. Another option could be to have
             * a "close" message/command sent by the client, but if the client closes improperly, or they lose
             * their network connection, it's not going to get sent anyway.
             */
            while (true) {
                try {
                    /**
                     * Read the command, this tells us what to send the client back.
                     * If the client has disconnected, this will throw an exception.
                     */
                    final NetworkDataSource.Command command = (NetworkDataSource.Command) inputStream.readObject();
                    handleCommand(socket, inputStream, outputStream, command);
                } catch (SocketTimeoutException e) {
                    /**
                     * We catch SocketTimeoutExceptions, because that just means the client hasn't sent
                     * any new requests. We don't want to disconnect them otherwise. Another way to
                     * check if they're "still there would be with ping/pong commands.
                     */
                    continue;
                }
            }
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            System.out.println(String.format("Connection %s closed", socket.toString()));
        }
    }

    /**
     * Handles a request from the client.
     * @param socket socket for the client
     * @param inputStream input stream to read objects from
     * @param outputStream output stream to write objects to
     * @param command command we're handling
     * @throws IOException if the client has disconnected
     * @throws ClassNotFoundException if the client sends an invalid object
     */
    private void handleCommand(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream,
                               NetworkDataSource.Command command) throws IOException, ClassNotFoundException {
        /**
         * Remember this is happening on separate threads for each client. Therefore access to the database
         * must be thread-safe in some way. The easiest way to achieve thread safety is to just put a giant
         * lock around all database operations, in this case with a synchronized block on the database object.
         */
        switch (command) {
            case LOG_IN: {
                // List<String> sent by the client
                final ArrayList<String> loginData = (ArrayList<String>) inputStream.readObject();
                String username = loginData.get(0);
                String password = loginData.get(1);

                // send the boolean back to the client
                synchronized (userDatabase) {
                    outputStream.writeBoolean(userDatabase.VerifyLogIn(username, password));
                }
                outputStream.flush();

            }
            break;

            case ADD_USER: {
                // client is sending us a new person
                final User user = (User) inputStream.readObject();

                synchronized (userDatabase) {
                    outputStream.writeBoolean(userDatabase.create(user));
                }

                outputStream.flush();

                System.out.println(String.format("Added user '%s' to database from client %s",
                        user.getUsername(), socket.toString()));
            }
            break;

            case GET_SIZE: {
                // no parameters sent by client

                // send the client back the size of the database
                synchronized (userDatabase) {
                    outputStream.writeInt(userDatabase.getSize());
                }
                outputStream.flush();

                System.out.println(String.format("Sent size of %d to client %s",
                        userDatabase.getSize(), socket.toString()));
            }
            break;

            case EDIT_USER: {
                // one parameter - the person's name
                final User user = (User) inputStream.readObject();

                synchronized (userDatabase) {
                    outputStream.writeBoolean(userDatabase.edit(user));
                }

                outputStream.flush();

                System.out.println(String.format("Edited user '%s' on behalf of client %s",
                        user.getUsername(), socket.toString()));
            }
            break;

            case DELETE_USER: {
                // one parameter - the person's name
                final String user_id = (String) inputStream.readObject();

                synchronized (userDatabase) {
                    outputStream.writeBoolean(userDatabase.delete(user_id));
                }

                outputStream.flush();

                System.out.println(String.format("Deleted user with id '%s' on behalf of client %s",
                        user_id, socket.toString()));
            }
            break;

            case GET_NAME_SET: {
                // no parameters sent by client

                // send the client back the name set
                synchronized (userDatabase) {
                    outputStream.writeObject(userDatabase.nameSet());
                }
                outputStream.flush();

                System.out.println(String.format("Sent name set to client %s",
                        socket.toString()));
            }
            break;

            case GET_USER: {
                // client sends us the name of the person to retrieve
                final String userName = (String) inputStream.readObject();
                synchronized (userDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<User> users = userDatabase.get(userName);

                    // send the client back the person's details, or null
                    outputStream.writeObject(users);

                    if (users != null)
                        System.out.println(String.format("Sent person '%s' to client %s",
                                users.get(0).getUsername(), socket.toString()));
                }
                outputStream.flush();
            }
            break;

            case GET_USERS: {
                synchronized (userDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<User> users = (ArrayList<User>) userDatabase.getAll();

                    // send the client back the person's details, or null
                    outputStream.writeObject(users);

                    if (users != null)
                        System.out.println(String.format("Sent all users to client %s",
                                socket.toString()));
                }
                outputStream.flush();
            }
            break;

            case ADD_ORGANISATION:{
                // List<String> sent by the client
                final Organisation organisation = (Organisation) inputStream.readObject();

                // send the boolean back to the client
                synchronized (organisationDatabase) {
                    outputStream.writeBoolean(organisationDatabase.create(organisation));
                }
                outputStream.flush();

                System.out.println(String.format("Added organisation '%s' to database from client %s",
                        organisation.getOrganisationName(), socket.toString()));
            }
            break;

            case DELETE_ORGANISATION:{
                // List<String> sent by the client
                final String organisationID = (String) inputStream.readObject();

                // send the boolean back to the client
                synchronized (organisationDatabase) {
                    outputStream.writeBoolean(organisationDatabase.delete(organisationID));
                }
                outputStream.flush();
            }
            break;

            case EDIT_ORGANISATION:{
                // List<String> sent by the client
                final Organisation organisation = (Organisation) inputStream.readObject();

                // send the boolean back to the client
                synchronized (organisationDatabase) {
                    outputStream.writeBoolean(organisationDatabase.edit(organisation));
                }
                outputStream.flush();
            }
            break;

            case GET_ORGANISATION:{
                final String organisation_id = (String) inputStream.readObject();

                synchronized (organisationDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<Organisation> organisations = organisationDatabase.get(organisation_id);

                    // send the client back the person's details, or null
                    outputStream.writeObject(organisations);

                    if (organisations != null)
                        System.out.println(String.format("Sent the organisation to client %s",
                                socket.toString()));
                }
                outputStream.flush();
            }
            break;

            case GET_ORGANISATIONS:{
                synchronized (organisationDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<Organisation> organisations = organisationDatabase.getAll();

                    // send the client back the person's details, or null
                    outputStream.writeObject(organisations);

                    if (organisations != null)
                        System.out.println(String.format("Sent all organisations to client %s",
                                socket.toString()));
                }
                outputStream.flush();
            }
            break;

            //Asset
            case ADD_ASSET: {
                // List<String> sent by the client
                final Asset asset = (Asset) inputStream.readObject();
                synchronized (assetDatabase) {
                    assetDatabase.create(asset);
                }
                System.out.println(String.format("Added asset '%s' to database from client %s",
                        asset.getAssetName(), socket.toString()));


            }
            break;

            case EDIT_ASSET: {
                final Asset asset = (Asset) inputStream.readObject();
                synchronized (assetDatabase) {
                    assetDatabase.edit(asset);
                }
                System.out.println(String.format("Edited asset '%s' to database from client %s",
                        asset.getAssetName(), socket.toString()));
            }
            break;

            case DELETE_ASSET: {
                final String assetID = (String) inputStream.readObject();
                synchronized (assetDatabase) {
                    assetDatabase.delete(assetID);
                }
                outputStream.flush();
            }
            break;

            case GET_ASSETS: {
                synchronized (assetDatabase) {
                    final ArrayList<Asset> assets = assetDatabase.getAll();

                    // send the client back the person's details, or null
                    outputStream.writeObject(assets);
                }
                outputStream.flush();
            }
            break;

            case GET_INVENTORIES_BY_ORGANISATION_ID:{
                final String organisation_id = (String) inputStream.readObject();

                synchronized (inventoryDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<Inventory> inventories = inventoryDatabase.getAllByOrganisationID(organisation_id);

                    // send the client back the person's details, or null
                    outputStream.writeObject(inventories);

                    outputStream.flush();

                    if (inventories != null)
                        System.out.println(String.format("Sent all inventories to client %s",
                                socket.toString()));
                }
                break;
            }

            case GET_LISTING_BY_ASSET: {
                final Asset asset = (Asset) inputStream.readObject();

                synchronized (assetDatabase) {
                    // synchronize both the get as well as the send, that way
                    // we don't send a half updated person
                    final ArrayList<Asset> assets = assetDatabase.getAllListingByAsset(asset);

                    // send the client back the person's details, or null
                    outputStream.writeObject(assets);

                    if (assets != null)
                        System.out.println(String.format("Sent the assets to client %s",
                                socket.toString()));
                }
                outputStream.flush();
            }
            break;
        }
    }

    /**
     * Returns the port the server is configured to use
     *
     * @return The port number
     */
    public static int getPort() {
        return PORT;
    }

    /**
     * Starts the server running on the default port
     */
    public void start() throws IOException {
        // Connect to the database.
        organisationDatabase = new JBDCOrganisationDataSource();
        userDatabase = new JBDCUserDataSource();
        assetDatabase = new JBDCAssetDataSource();
        listingDatabase = new JBDCListingDataSource();
        inventoryDatabase = new JBDCInventoryDataSource();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(SOCKET_ACCEPT_TIMEOUT);
            for (;;) {
                if (!running.get()) {
                    // The server is no longer running
                    break;
                }
                try {
                    final Socket socket = serverSocket.accept();
                    socket.setSoTimeout(SOCKET_READ_TIMEOUT);

                    // We have a new connection from a client. Use a runnable and thread to handle
                    // the client. The lambda here wraps the functional interface (Runnable).
                    final Thread clientThread = new Thread(() -> handleConnection(socket));
                    clientThread.start();
                } catch (SocketTimeoutException ignored) {
                    // Do nothing. A timeout is normal- we just want the socket to
                    // occasionally timeout so we can check if the server is still running
                } catch (Exception e) {
                    // We will report other exceptions by printing the stack trace, but we
                    // will not shut down the server. A exception can happen due to a
                    // client malfunction (or malicious client)
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // If we get an error starting up, show an error dialog then exit
            e.printStackTrace();
            System.exit(1);
        }

        // Close down the server
        System.exit(0);
    }

    /**
     * Requests the server to shut down
     */
    public void shutdown() {
        // Shut the server down
        running.set(false);
    }
}
