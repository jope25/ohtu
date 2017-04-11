package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (invalidUsername(username)) {
            status.addError("username should have at least 3 characters");
        }

        if (passwordIsTooShort(password)) {
            status.addError("password should have at least 8 characters");
        }
        
        if (!passwordHasSpecialChar(password)) {
            status.addError("password can not contain only letters");
        }
        
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }
        
        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }
    
    private boolean invalidUsername(String username) {
        return !username.matches("[a-z]{3,}");
    }
    
    private boolean passwordIsTooShort(String password) {
        return password.length() < 8;
    }
    
    private boolean passwordHasSpecialChar(String password) {
        boolean specialCharFound = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!(Character.isAlphabetic(c) || Character.isWhitespace(c))) {
                specialCharFound = true;
                break;
            }
        }
        return specialCharFound;
    }
}
