package gamari.app.features.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error_page")
public class ErrorController {

    @GetMapping
    public String showErrorPage() {
        return "error_page";
    }
}
