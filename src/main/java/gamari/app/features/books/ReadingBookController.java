package gamari.app.features.books;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamari.app.features.books.forms.ReadingBookForm;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.BookService;
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
@RequestMapping("/reading-books")
public class ReadingBookController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    private ReadingBookService readingBookService;

    @GetMapping("/new")
    public String newReadingBook(Model model) {
        model.addAttribute("readingBookForm", new ReadingBookForm());
        return "pages/reading-books/new";
    }

    @PostMapping
    public String createReadingBook(@ModelAttribute ReadingBookForm readingBookForm, Principal principal) {
        System.out.println("AAAAAA");
        String username = principal.getName();
        User user = userService.findByUsername(username);

        Book book = new Book();
        book.setTitle(readingBookForm.getTitle());
        book.setIsbn10(readingBookForm.getIsbn10());
        book.setIsbn13(readingBookForm.getIsbn13());
        System.out.println(book.getIsbn13());

        // bookService.save(book); // bookServiceは適切にAutowiredされる

        ReadingBook readingBook = new ReadingBook();
        readingBook.setBookId(book.getId());
        readingBook.setUserId(user.getId());

        // readingBookService.save(readingBook);

        return "redirect:/dashboard";
    }
}
