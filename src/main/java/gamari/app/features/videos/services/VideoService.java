package gamari.app.features.videos.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {
    public void saveVideo(String title, String description, Double price, MultipartFile videoFile) {
        // TODO エラーハンドリング
        try {
            // TODO UUIDにする
            // TODO バリデーション処理

            Path videoFilePath = Paths.get("src/main/resources/static/" + videoFile.getOriginalFilename());
            Files.write(videoFilePath, videoFile.getBytes());

            // TODO DBへの保存処理

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
