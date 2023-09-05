package gamari.app.features.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        List<ReadingBook> readingBooks = readingBookQueryService.findReadingBookByUserId(user.getId());
        model.addAttribute("readingBooks", readingBooks);
        return "pages/dashboard";
    }
}
