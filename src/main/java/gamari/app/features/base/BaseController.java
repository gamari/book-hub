package gamari.app.features.base;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
