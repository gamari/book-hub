package gamari.app.features.books.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gamari.app.features.base.BaseController;
import gamari.app.features.base.constants.URL;
import gamari.app.features.base.services.BookApiService;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.services.book.BookQueryService;
import gamari.app.features.books.services.book.BookRegistrationService;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.users.models.User;

@Controller
public class BookController extends BaseController {
    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private BookRegistrationService bookRegistrationService;

    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @Autowired
    BookApiService bookApiServiceImpl;

    @GetMapping("/books/isbn/{isbn}")
    public String bookDetailByIsbn(@PathVariable String isbn, Model model, Principal principal) {
        User user = this.getUserFromPrincipal(principal);

        Optional<Book> optBook = bookQueryService.findBookByIsbn(isbn);

        if (optBook.isPresent()) {
            Book book = optBook.get();
            return modelAndView(book, user, model);
        }

        Book item = bookApiServiceImpl.fetchBookByIsbn(isbn);
        item = bookRegistrationService.register(item);

        if (item != null) {
            return modelAndView(item, user, model);
        }

        return URL.ERROR_PAGE.getUrl();
    }

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable String id, Model model, Principal principal) {
        User user = this.getUserFromPrincipal(principal);
        Optional<Book> optBook = bookQueryService.findBookById(id);

        if (optBook.isPresent()) {
            Book book = optBook.get();
            return modelAndView(book, user, model);
        }

        return URL.ERROR_PAGE.getUrl();
    }

    private String modelAndView(Book book, User user, Model model) {
        boolean isReadingBook = readingBookQueryService.isBookRegisteredByUser(book.getId(), user);
        int numberOfPeopleReading = readingBookQueryService.countReadingBooksByBookId(book.getId());

        model.addAttribute("book", book);
        model.addAttribute("isReading", isReadingBook);
        model.addAttribute("numberOfPeopleReading", numberOfPeopleReading);

        return "pages/books/detail";
    }
}
