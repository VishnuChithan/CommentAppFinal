package extras;

public class CommentsData {
    String username,comment;
    long timestamps;
    public void setData(String username,String comment,long timestamps){
        this.username=username;
        this.comment=comment;
        this.timestamps=timestamps;
    }
    public String getUsername(){
        return this.username;
    }
    public String getComment(){
        return this.comment;
    }
    public long getTime(){
        return this.timestamps;
    }
}
