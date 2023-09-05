package gamari.app.features.base.models;

import java.util.List;

import lombok.Data;

@Data
public class GoogleBooksResponse {
    private List<GoogleBooksItem> items;
}
