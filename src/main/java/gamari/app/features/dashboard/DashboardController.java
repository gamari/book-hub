package gamari.app.features.dashboard;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamari.app.features.base.BaseController;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.models.ReadingBookWithThumbnail;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.users.models.User;

// TODO ページネーションを追加する

@Controller
public class DashboardController extends BaseController {
    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        User user = this.getUserFromPrincipal(principal);
        List<ReadingBookWithThumbnail> readingBooks = readingBookQueryService
                .findReadingBookWithThumbnailByUserId(user.getId());

        // TODO 1ヶ月分の読書量を取得する

        model.addAttribute("readingBooks", readingBooks);
        return "pages/dashboard";
    }
}
