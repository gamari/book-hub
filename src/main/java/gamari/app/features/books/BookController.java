package gamari.app.features.books;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gamari.app.features.books.models.Book;
import gamari.app.features.books.services.BookService;
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReadingBookService readingBookService;

    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @Autowired
    private UserService userService;

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable String id, Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Optional<Book> optBook = bookService.findBookById(id);

        if (optBook.isPresent()) {
            Book book = optBook.get();
            boolean idReadingBook = readingBookQueryService.isBookRegisteredByUser(id, user);
            int numberOfPeopleReading = readingBookQueryService.countReadingBooksByBookId(id);

            model.addAttribute("book", book);
            model.addAttribute("isReading", idReadingBook);
            model.addAttribute("numberOfPeopleReading", numberOfPeopleReading);
            System.out.println(idReadingBook);

            return "pages/books/detail";
        } else {
            return "redirect:/error-page";
        }
    }
}
