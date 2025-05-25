package Model;

import java.io.*;
import java.util.HashMap;


/**
 * Manages user registration, login, saving and loading from file.
 * This class saves users into a file using Java serialization.
 *
 * @author Vojtěch
 */
public class UserManager implements Serializable {
    private static final long serialVersionUID = 1L; //TODO IDK jestli tu musi byt, nabidlo mi to sama idea a funguje to, skusim pak jestli ok
    
    private HashMap<String, User> users;
    private static final String fileName = "users.txt";

    public UserManager() {
        users = loadUsers();
        if (users == null) {
            users = new HashMap<>();
        }
    }


    /**
     * Registers a new user if the username is not already used.
     *
     * @param user The user to register
     * @return true if registration was successful, false if username already exists
     */
    public boolean registerUser(User user) {
        if (users.containsKey(user.getUserName())) {
            return false;
        }
        users.put(user.getUserName(), user);
        saveUsers();
        return true;
    }


    /**
     * Logs in a user if the username and password match.
     *
     * @param username The username
     * @param password The password
     * @return The User object if login is successful, null otherwise
     */
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    /**
     * Saves all users to a file.
     * This uses Java object serialization.
     */
    public void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }


    /**
     * Loads users from the saved file.
     * If the file is not found or is invalid, returns an empty map.
     *
     * @return The loaded users, or a new empty map if something goes wrong
     */
    private HashMap<String, User> loadUsers() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HashMap<String, User>) input.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (InvalidClassException e) {
            new File(fileName).delete();
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error with loading users");
            return new HashMap<>();
        }
    }
}