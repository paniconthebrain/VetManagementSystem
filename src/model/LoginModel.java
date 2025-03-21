package model;

public class LoginModel {

    private String userName;
    private String password;
    private String email;

    // Getters and Setters
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Constructors
    public LoginModel(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public LoginModel() {
        this.userName = "";
        this.password = "";
        this.email = "";
    }

    public LoginModel(LoginModel userLogin) {
        this.userName = userLogin.userName;
        this.password = userLogin.password;
        this.email = userLogin.email;
    }
}
