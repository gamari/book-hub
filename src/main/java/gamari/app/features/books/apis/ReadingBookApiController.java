package gamari.app.features.books.apis;

import java.security.Principal;
import java.util.Date;
import java.util.Map;
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
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@RestController
@RequestMapping("/api/reading-books")
public class ReadingBookApiController {
    @Autowired
    UserService userService;

    @Autowired
    private MemoService memoService;

    @Autowired
    private ReadingBookService readingBookService;

    // TODO memoを返したい
    @PostMapping("/{id}/memos")
    public ResponseEntity<Memo> createMemo(@PathVariable String id, @RequestBody Memo memo) {
        // TODO ReadingBookのユーザーのみ登録可能
        memo.setId(UUID.randomUUID().toString());
        memo.setReadingBookId(id);
        memo.setCreatedAt(new Date());
        memoService.save(memo);
        return ResponseEntity.ok(memo);
    }

    // TODO reading-bookじゃない？
    // TODO 登録処理を追加する
    // @PostMapping("/register")
    // public ResponseEntity<Map<String, Boolean>> registerBook(@RequestBody
    // ReadingBook readingBook) {
    // bookService.registerBook(readingBook);
    // return ResponseEntity.ok(Map.of("success", true));
    // }

    @PostMapping("/unregister")
    public ResponseEntity<Map<String, Boolean>> unregisterBook(@RequestBody Map<String, String> params,
            Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        String userId = user.getId();
        String bookId = params.get("bookId");
        readingBookService.unregisterBook(userId, bookId);
        return ResponseEntity.ok(Map.of("success", true));
    }
}
