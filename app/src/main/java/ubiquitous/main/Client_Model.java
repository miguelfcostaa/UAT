package ubiquitous.main;

public class Client_Model {

    private int id;
    private String username;
    private String password;
    private boolean rememberMe;

    public Client_Model(int id, String username, String password, boolean rememberMe) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public Client_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "Client_Model{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
