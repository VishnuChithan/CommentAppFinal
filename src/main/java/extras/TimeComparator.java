package extras;

import extras.CommentsData;

import java.util.Comparator;

public class TimeComparator implements Comparator<CommentsData> {
    @Override
    public int compare(CommentsData a, CommentsData b) {
        return Long.compare(b.timestamps, a.timestamps);
    }
}
