package gamari.app.features.videos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import gamari.app.features.videos.services.VideoService;

@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/upload")
    public String getUploadPage(Model model) {
        return "videos/upload";
    }

    @PostMapping("/upload")
    public String uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("videoFile") MultipartFile videoFile,
            Model model

    ) {
        // TODO バリデーションエラーとかする
        System.out.println("OK");
        System.out.println(videoFile);
        videoService.saveVideo(title, description, price, videoFile);
        return "videos/upload";
    }

    @GetMapping("/{id}")
    public String playVideo(@PathVariable String id, Model model) {
        // TODO DBからの取得処理
        model.addAttribute("videoUrl", "/movie1.mp4");
        return "videos/index";
    }

}
