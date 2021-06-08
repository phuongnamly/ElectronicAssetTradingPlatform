package client.gui.clientData;

import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.entity.User;
import client.model.entity.Organisation;
import client.model.exception.UserException;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class NetworkDataSource {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * These are the commands which will be sent across the network connection.
     */
    public enum Command {
        LOG_IN,
        ADD_USER,
        EDIT_USER,
        DELETE_USER,
        GET_USER,
        GET_USERS,
        GET_NAME_SET,
        GET_SIZE,

        //Asset
        ADD_ASSET,
        EDIT_ASSET,
        DELETE_ASSET,
        GET_ASSET,
        GET_ASSETS,
        GET_LISTING_BY_ASSET,

        //Listing
        ADD_LISTING,
        EDIT_LISTING,
        DELETE_LISTING,
        GET_LISTING,

        // Organisation
        ADD_ORGANISATION,
        EDIT_ORGANISATION,
        DELETE_ORGANISATION,
        GET_ORGANISATION,
        GET_ORGANISATIONS,

        // Inventory
        ADD_INVENTORY,
        EDIT_INVENTORY,
        DELETE_INVENTORY,
        GET_INVENTORY,
        GET_INVENTORIES,
        GET_INVENTORIES_BY_ORGANISATION_ID
    }

    public NetworkDataSource() {
        Properties props = new Properties();
        FileInputStream in = null;

        try {
            in = new FileInputStream("./src/server/properties/connection.props");
            props.load(in);
            in.close();

            final String HOSTNAME = props.getProperty("con.hostname");
            final int PORT = Integer.parseInt(props.getProperty("con.port"));


            // Persist a single connection through the whole lifetime of the application.
            // We will re-use this same connection/socket, rather than repeatedly opening
            // and closing connections.
            socket = new Socket(HOSTNAME, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException e) {
            // If the server connection fails, we're going to throw exceptions
            // whenever the application actually tries to query anything.
            // But it wasn't written to handle this, so make sure your
            // server is running beforehand!
            System.out.println("Failed to connect to server");
        }
    }

    public boolean login(String username, String password){
        try {
            outputStream.writeObject(Command.LOG_IN);
            ArrayList<String> loginData = new ArrayList<>();
            loginData.add(username);
            loginData.add(password);
            outputStream.writeObject(loginData);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            return (dataInputStream.readBoolean());

        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User user) throws IOException, UserException, ClassCastException {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        String func = "create";
        if (UserException.validate(func, user)){ ;
            // tell the server to expect a person's details
            outputStream.writeObject(Command.ADD_USER);

            // send the actual data
            outputStream.writeObject(user);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            return dataInputStream.readBoolean();
        }
        else{
            return false;
        }
    }

    public boolean editUser(User user) throws IOException, UserException, ClassCastException {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        String func = "editID";
        if (UserException.validate(func, user)){ ;
            // tell the server to expect a person's details
            outputStream.writeObject(Command.EDIT_USER);

            // send the actual data
            outputStream.writeObject(user);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            return dataInputStream.readBoolean();
        }
        else{
            return false;
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


    public ArrayList<User> getUser(String name) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_USER);
            outputStream.writeObject(name);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (ArrayList<User>) inputStream.readObject();
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

    // Organisation
    public boolean addOrganisation(String organisationName, String credits) {
        try {
            outputStream.writeObject(Command.ADD_ORGANISATION);
            Organisation organisation = new Organisation();
            organisation.setOrganisationName(organisationName);
            organisation.setCredits(credits);
            outputStream.writeObject(organisation);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            if (dataInputStream.readBoolean()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrganisation(String organisationID){
        try {
            outputStream.writeObject(Command.DELETE_ORGANISATION);
            // List<String>
            outputStream.writeObject(organisationID);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            if (dataInputStream.readBoolean()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editOrganisation(String organisationID, String organisationName, String credits){
        try {
            outputStream.writeObject(Command.EDIT_ORGANISATION);

            Organisation organisation = new Organisation();
            organisation.setOrganisationID(organisationID);
            organisation.setOrganisationName(organisationName);
            organisation.setCredits(credits);
            outputStream.writeObject(organisation);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            if (dataInputStream.readBoolean()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Organisation> getOrganisation(String id) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_ORGANISATION);
            outputStream.writeObject(id);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (ArrayList<Organisation>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Organisation> getOrganisations(){
        try {
            outputStream.writeObject(Command.GET_ORGANISATIONS);
            outputStream.flush();

            return (ArrayList<Organisation>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new ArrayList<Organisation>();
        }
    }

//     Asset
    public boolean addAsset(String assetType, String assetName) {
        try {
            outputStream.writeObject(Command.ADD_ASSET);
            Asset assetData = new Asset();
            assetData.setAssetType(assetType);
            assetData.setAssetName(assetName);

            outputStream.writeObject(assetData);
            outputStream.flush();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            if (dataInputStream.readBoolean()) {
                return false;
            } else {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editAsset(String assetID, String assetName, String assetType){
        try {
            outputStream.writeObject(Command.EDIT_ASSET);
            Asset asset= new Asset();
            asset.setAssetID(assetID);
            asset.setAssetName(assetName);
            asset.setAssetType(assetType);
            outputStream.writeObject(asset);
            outputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            if (dataInputStream.readBoolean()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Asset> getAssets(){
        try {
            outputStream.writeObject(Command.GET_ASSETS);
            outputStream.flush();

            return (ArrayList<Asset>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new ArrayList<Asset>();
        }
    }

    // Inventory
    public ArrayList<Inventory> getInventoriesByOrganisationId(String id) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_INVENTORIES_BY_ORGANISATION_ID);
            outputStream.writeObject(id);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (ArrayList<Inventory>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Asset> getListingsByAsset(Asset asset) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_LISTING_BY_ASSET);
            outputStream.writeObject(asset);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (ArrayList<Asset>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
