package gamari.app.features.videos.models;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Video {
    private String id;

    @NotNull
    private String title;
    private String description;
    private Double price;
    private String previewImagePath;
    private String videoFilePath;
    private Long ownerId;
    private Date createdAt;
    private Date updatedAt;
}
