package gamari.app.features.memo.models;

import lombok.Data;

@Data
public class Activity {
    public Activity(int count, String date) {
        this.count = count;
        this.date = date;
    }

    int count;
    String date;
}
