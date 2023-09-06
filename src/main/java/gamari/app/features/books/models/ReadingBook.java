package gamari.app.features.books.models;

import lombok.Data;
import java.util.Date;

@Data
public class ReadingBook {
    private String id;
    private String userId;
    private String bookId;
    private String title;
    // TODO 削除
    private boolean isReading;
    // unread reading done
    private String status;
    private Date startDate;
    private Date endDate;
}