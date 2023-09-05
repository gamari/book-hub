package gamari.app.features.books.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReadingBookWithThumbnail extends ReadingBook {
    private String thumbnail;
}
