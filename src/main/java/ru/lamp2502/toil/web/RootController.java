package ru.lamp2502.toil.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lamp2502.toil.model.User;
import ru.lamp2502.toil.repository.CrudUserRepository;
import ru.lamp2502.toil.repository.UserRepository;
import ru.lamp2502.toil.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CrudUserRepository repository;

/*    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }*/

/*    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }*/

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }
}
