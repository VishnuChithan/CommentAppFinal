package CommentDatabase;

import extras.CommentsData;
import extras.constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

public class CommentsDB {
    public boolean addComment(String username, String comment) {
        try{
            String insertQuery = "INSERT INTO "+ constants.commentsTable+"(username,comments,timestamps ) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = UserDB.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, comment);
            preparedStatement.setLong(3, Instant.now().getEpochSecond());
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
    public ArrayList<CommentsData> retrieveComments(){
        try {
            ArrayList<CommentsData> result=null;
            ResultSet resultSet= UserDB.getStatement().executeQuery("SELECT username,comments,timestamps FROM comments_data ;");
            result=new ArrayList<>();
            while(resultSet.next()){
                CommentsData commentsData=new CommentsData();
                commentsData.setData(resultSet.getString("username"),resultSet.getString("comments"),resultSet.getLong("timestamps"));
                result.add(commentsData);
            }
            return result;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
