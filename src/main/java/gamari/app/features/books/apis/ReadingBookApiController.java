package gamari.app.features.books.apis;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamari.app.features.books.models.Memo;
import gamari.app.features.books.services.MemoService;

@RestController
@RequestMapping("/api/reading-books")
public class ReadingBookApiController {
    @Autowired
    private MemoService memoService;

    @PostMapping("/{id}/memos")
    public ResponseEntity<String> createMemo(@PathVariable String id, @RequestBody Memo memo) {
        System.out.println("AAAAAA");
        // TODO ReadingBookのユーザーのみ登録可能
        memo.setId(UUID.randomUUID().toString());
        memo.setReadingBookId(id);
        memo.setCreatedAt(new Date());
        memoService.save(memo);
        return ResponseEntity.ok("Memo created successfully.");
    }
}
