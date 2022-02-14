package extras;

public class UserDetails {
    private String username;
    private String password;
    private String secretCode;
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setSecretCode(String secretCode){
        this.secretCode=secretCode;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getSecretCode(){
        return this.secretCode;
    }
}

