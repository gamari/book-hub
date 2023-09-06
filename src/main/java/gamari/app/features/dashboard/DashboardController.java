package gamari.app.features.dashboard;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamari.app.features.base.BaseController;
import gamari.app.features.books.models.ReadingBookWithThumbnail;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.dashboard.services.DashboardService;
import gamari.app.features.memo.models.Activity;
import gamari.app.features.users.models.User;

// TODO ページネーションを追加する

@Controller
public class DashboardController extends BaseController {
    @Autowired
    private ReadingBookQueryService readingBookQueryService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        User user = this.getUserFromPrincipal(principal);
        List<ReadingBookWithThumbnail> readingBooks = readingBookQueryService
                .findReadingBookWithThumbnailByUserId(user.getId());

        List<Activity> activities = dashboardService.calculateActivities(new Date(),
                new Date(), user);
        System.out.println(activities);

        model.addAttribute("readingBooks", readingBooks);
        model.addAttribute("activities", activities);

        return "pages/dashboard";
    }
}
