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

import gamari.app.features.books.forms.ReadingBookForm;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.Memo;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.BookService;
import gamari.app.features.books.services.MemoService;
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
@RequestMapping("/reading-books")
public class ReadingBookController {
    @Autowired
    UserService userService;

    @Autowired
    private BookService bookService;

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
        Optional<ReadingBook> book = readingBookService.findById(bookId);
        if (book.isEmpty()) {
            return "redirect:/error_page";
        }

        List<Memo> memos = memoService.findByReadingBookId(bookId);
        System.out.println(memos.size());

        model.addAttribute("book", book.get());
        model.addAttribute("memos", memos);

        return "pages/reading-books/detail";
    }

    @PostMapping
    public String createReadingBook(@ModelAttribute ReadingBookForm readingBookForm, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        Book book = bookService.getOrCreateBook(readingBookForm.getTitle(), readingBookForm.getIsbn10(),
                readingBookForm.getIsbn13());

        if (readingBookService.isAlreadyReadingBook(book.getId(), user.getId())) {
            return "redirect:/error_page";
        }

        readingBookService.saveReadingBook(user, book);

        return "redirect:/dashboard";
    }

}
