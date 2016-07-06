package uz.alano.notebook.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class User {

    @Size(min = 2, max = 20, message = "Name must be less than 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
    private String userName;

    @Size(min = 2, max = 20, message = "Login must be less than 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must be alphanumeric with no spaces")
    private String login;

    @Size(min = 6, max = 20, message = "Password must be larger than 6 characters long.")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
