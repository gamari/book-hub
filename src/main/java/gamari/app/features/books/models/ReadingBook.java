package gamari.app.features.books.models;

import lombok.Data;
import java.util.Date;

@Data
public class ReadingBook {
    private String id;
    private String userId;
    private String bookId;
    private String title;
    private boolean isReading;
    private Date startDate;
    private Date endDate;
}