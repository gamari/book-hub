package gamari.app.features.books.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// TODO isReadingをDoneにする
@Data
public class ReadingBookForm {
    private String title;
    private String bookId;
    private boolean isReading;

    // TODO どうにかする
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
