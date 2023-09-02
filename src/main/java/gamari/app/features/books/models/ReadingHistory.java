package gamari.app.features.books.models;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ReadingHistory {
    private UUID id;

    private UUID bookId;
    private String note;
    private Date commitDate;
}
