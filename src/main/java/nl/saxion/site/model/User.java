package nl.saxion.site.model;


import java.io.Serializable;

/**
 * @author Onyebuchi Iheuwadinachi Eleazu
 */


public class User implements Serializable {

    private String name;
    private String password;
    private String role;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;





    }
}
