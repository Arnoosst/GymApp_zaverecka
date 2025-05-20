package Model;

import java.io.*;
import java.util.HashMap;

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

    public boolean registerUser(User user) {
        if (users.containsKey(user.getUserName())) {
            return false;
        }
        users.put(user.getUserName(), user);
        saveUsers();
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    private HashMap<String, User> loadUsers() {
        File file = new File(fileName);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HashMap<String, User>) in.readObject();
        } catch (InvalidClassException e) {
            file.delete();
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new HashMap<>();
        }
    }

    //TODO projit si poradne tuhle load metodu
}