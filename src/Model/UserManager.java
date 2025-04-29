package Model;

import java.io.*;
import java.util.HashMap;

public class UserManager implements Serializable{
    private HashMap<String, User> users;

    private static final String fileName = "users.txt";

    public UserManager() {
        users = loadUsers();
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

    private void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //poradil chat
    private HashMap<String, User> loadUsers() {
        File file = new File("users.txt");
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = in.readObject();
            if (obj instanceof HashMap) {
                return (HashMap<String, User>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
