package Model;
 
import java.util.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users", namespace="http://www.uts.edu.au/31284/wsd-diary")
public class Users implements Serializable {
    @XmlElement(name = "user")
    private ArrayList<User> list = new ArrayList<User>();
 
    public ArrayList<User> getList() {
        return list;
    }
    //Adds a user, takes a user object as an argument
    public void addUser(User user) {
        list.add(user);
    }
    //Removces a user, takes a user object as an argument
    public void removeUser(User user) {
        list.remove(user);
    }
    //A login method that makes sure loops through the users in the system and checks if the login exists
    public User login(String email, String password) {
        // For each user in the list...
        for (User user : list) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user; // Login correct. Return this user.
        }
        return null; // Login incorrect. Return null.
    }
    //
    public User getUser(String email) {
        // For each user in the list...
        for (User user : list) {
            if (user.getEmail().equals(email))
                return user; // Login correct. Return this user.
        }
        return null; // Login incorrect. Return null.
    }
}