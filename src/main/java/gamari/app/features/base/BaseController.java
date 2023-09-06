package gamari.app.features.base;

import java.security.Principal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import gamari.app.features.base.libs.DateFormatter;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@Controller
public abstract class BaseController {
    @Autowired
    UserService userService;

    protected User getUserFromPrincipal(Principal principal) {
        String username = principal.getName();
        return userService.findByUsername(username);
    }

    protected void populateToday(Model model, String format) {
        String today = DateFormatter.dateToString(Calendar.getInstance().getTime(), format);
        model.addAttribute("today", today);
    }

    protected void populateUsername(Model model, User user) {
        model.addAttribute("username", user.getUsername());
    }
}
