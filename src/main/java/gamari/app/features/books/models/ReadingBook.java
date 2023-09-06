package gamari.app.features.books.models;

import lombok.Data;
import java.util.Date;

@Data
public class ReadingBook {
    private String id;
    private String userId;
    private String bookId;
    private String title;
    private String description;
    private boolean isReading; // TODO 削除予定
    private String status;
    private Date startDate;
    private Date endDate;
}