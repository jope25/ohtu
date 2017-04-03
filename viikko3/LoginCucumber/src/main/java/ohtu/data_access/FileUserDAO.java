package ohtu.data_access;

import java.util.*;
import java.io.*;
import ohtu.domain.User;

public class FileUserDAO implements UserDao {

    private File file;

    public FileUserDAO(String file) {
        try {
            this.file = new File(file);
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
        }
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String username = s.next();
                String password = s.next();
                users.add(new User(username, password));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : listAll()) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(user.getUsername() + " " + user.getPassword() + " ");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
