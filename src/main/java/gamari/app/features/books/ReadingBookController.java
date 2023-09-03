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
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.ReadingBookService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
@RequestMapping("/reading-books")
public class ReadingBookController {
    @Autowired
    UserService userService;

    @Autowired
    private ReadingBookService readingBookService;

    @GetMapping("/new")
    public String newReadingBook(Model model) {
        model.addAttribute("readingBookForm", new ReadingBookForm());
        return "pages/reading-books/new";
    }

    @PostMapping
    public String createReadingBook(@ModelAttribute ReadingBookForm readingBookForm, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        String userId = user.getId();

        // TODO リファクタリングしたい
        ReadingBook newReadingBook = new ReadingBook();
        newReadingBook.setUserId(userId);
        newReadingBook.setBookId(readingBookForm.getBookId());
        newReadingBook.setTitle(readingBookForm.getTitle());
        newReadingBook.setReading(readingBookForm.isReading());
        newReadingBook.setStartDate(readingBookForm.getStartDate());
        newReadingBook.setEndDate(readingBookForm.getEndDate());

        readingBookService.createReadingBook(newReadingBook, userId);

        return "redirect:/dashboard";
    }
}
