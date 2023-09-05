package gamari.app.features.books;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamari.app.features.base.BaseController;
import gamari.app.features.books.forms.ReadingBookForm;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.Memo;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.books.services.book.BookQueryService;
import gamari.app.features.books.services.book.BookRegistrationService;
import gamari.app.features.books.services.memo.MemoService;
import gamari.app.features.users.models.User;

@Controller
@RequestMapping("/reading-books")
public class ReadingBookController extends BaseController {
    @Autowired
    private BookRegistrationService bookRegistrationService;

    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private MemoService memoService;

    @Autowired
    private ReadingBookService readingBookService;

    @GetMapping("/new")
    public String newReadingBook(Model model) {
        model.addAttribute("readingBookForm", new ReadingBookForm());
        return "pages/reading-books/new";
    }

    @GetMapping("/{bookId}")
    public String showBookDetail(@PathVariable String bookId, Model model) {
        Optional<ReadingBook> optReadingBook = readingBookService.findById(bookId);

        if (optReadingBook.isEmpty()) {
            return "redirect:/error_page";
        }

        ReadingBook readingBook = optReadingBook.get();
        System.out.println(readingBook.getId());
        System.out.println(readingBook.getBookId());

        Optional<Book> book = bookQueryService.findBookById(readingBook.getBookId());
        List<Memo> memos = memoService.findByReadingBookId(bookId);

        model.addAttribute("book", book.get());
        model.addAttribute("reading_book", readingBook);
        model.addAttribute("memos", memos);

        return "pages/reading-books/detail";
    }

    /** 読書中登録処理。 */
    @PostMapping
    public String createReadingBook(@ModelAttribute ReadingBookForm readingBookForm, Principal principal) {
        User user = this.getUserFromPrincipal(principal);

        String isbn10 = readingBookForm.getIsbn10();
        String isbn13 = readingBookForm.getIsbn13();

        Optional<Book> optBook = bookRegistrationService.findBookByIsbn(isbn10, isbn13);
        Book book = optBook.orElseGet(() -> {
            Book newBook = new Book();
            newBook.setTitle(readingBookForm.getTitle());
            newBook.setIsbn10(isbn10);
            newBook.setIsbn13(isbn13);
            newBook.setThumbnail(readingBookForm.getThumbnail());
            bookRegistrationService.save(newBook);
            return newBook;
        });

        if (readingBookService.isAlreadyReadingBook(book.getId(), user.getId())) {
            return "redirect:/error_page";
        }

        readingBookService.saveReadingBook(user, book);
        return "redirect:/dashboard";
    }

}
