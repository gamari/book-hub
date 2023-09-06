package gamari.app.features.books.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import gamari.app.features.base.BaseController;
import gamari.app.features.base.constants.URL;
import gamari.app.features.books.forms.ReadingBookForm;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.Memo;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.book.BookQueryService;
import gamari.app.features.books.services.book.BookRegistrationService;
import gamari.app.features.books.services.memo.MemoService;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.books.services.reading_book.ReadingBookRegistrationService;
import gamari.app.features.users.models.User;

@Controller
@RequestMapping("/reading-books")
public class ReadingBookController extends BaseController {
    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private BookRegistrationService bookRegistrationService;

    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @Autowired
    private ReadingBookRegistrationService readingBookRegistrationService;

    @Autowired
    private MemoService memoService;

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, String> payload) {
        String bookId = payload.get("bookId");
        String status = payload.get("status");

        System.out.println(bookId);
        System.out.println(status);

        Optional<ReadingBook> optReadingBook = readingBookQueryService.findReadingBookById(bookId);

        if (optReadingBook.isEmpty()) {
            return ResponseEntity.badRequest().body("Reading book not found");
        }

        ReadingBook readingBook = optReadingBook.get();
        readingBook.setStatus(status);
        readingBookRegistrationService.updateStatus(readingBook);

        return ResponseEntity.ok().body("Status updated");
    }

    @GetMapping("/new")
    public String newReadingBook(Model model) {
        model.addAttribute("readingBookForm", new ReadingBookForm());
        return "pages/reading-books/new";
    }

    @GetMapping("/{bookId}")
    public String showBookDetail(@PathVariable String bookId, Model model) {
        Optional<ReadingBook> optReadingBook = readingBookQueryService.findReadingBookById(bookId);

        if (optReadingBook.isEmpty()) {
            return "redirect:/error_page";
        }

        ReadingBook readingBook = optReadingBook.get();

        Optional<Book> book = bookQueryService.findBookById(readingBook.getBookId());
        List<Memo> memos = memoService.findByReadingBookId(bookId);

        model.addAttribute("book", book.get());
        model.addAttribute("reading_book", readingBook);
        model.addAttribute("memos", memos);

        return "pages/reading-books/detail";
    }

    /** 読書中登録処理。 */
    @PostMapping
    public String registerReadingBook(@ModelAttribute ReadingBookForm readingBookForm, Principal principal) {
        User user = this.getUserFromPrincipal(principal);

        Book newBook = readingBookForm.toBook();
        Book book = bookRegistrationService.register(newBook);

        if (readingBookQueryService.isBookRegisteredByUser(book.getId(), user)) {
            return URL.ERROR_PAGE.getUrl();
        }

        readingBookRegistrationService.registerBook(user, book);
        return URL.DASHBOARD.getUrl();
    }

}
