package CommentDatabase;

import extras.UserDetails;
import extras.constants;

import java.sql.*;

public enum UserDB {
    INSTANCE;
    public static UserDB getInstance() {
        return INSTANCE;
    }
    private Object readResolve(){
        return INSTANCE;
    }
    private static Connection connection;
    private static Statement statement;
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection==null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logschema","root","SqlPass!23");
        }
        return connection;
    }
    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        if(statement==null){
            statement=UserDB.getConnection().createStatement();
        }
        return statement;
    }
    public boolean isUserExist(String username){
        try {
            ResultSet resultSet= UserDB.getStatement().executeQuery("SELECT username FROM user_credentials where username='"+username+"';");
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }
    public boolean addUser(UserDetails userDetails){
        try{
            String insertQuery = "INSERT INTO "+ constants.userTable+"(username,password,secretcode) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = UserDB.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1, userDetails.getUsername());
            preparedStatement.setString(2, userDetails.getPassword());
            preparedStatement.setString(3, userDetails.getSecretCode());
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
    public boolean authenticate(String username,String password)  {
        try{
            ResultSet resultSet= UserDB.getStatement().executeQuery("SELECT username,password FROM user_credentials where username='"+username+"';");
            if(resultSet.next()){
                return username.equals(resultSet.getString("username")) && password.equals(resultSet.getString("password"));
            }
            return false;
        }
        catch (SQLException | ClassNotFoundException exception){
            return false;
        }

    }

    public String verifyUser(String username,String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= UserDB.getStatement().executeQuery("SELECT password FROM user_credentials where username='"+username+"' and secretcode='"+code+"';");
        if(resultSet.next()){
            return resultSet.getString("password");
        }
        return null;
    }

}
