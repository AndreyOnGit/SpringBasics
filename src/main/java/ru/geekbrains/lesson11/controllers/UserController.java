package ru.geekbrains.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.lesson11.entities.User;
import ru.geekbrains.lesson11.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String showHome() {
        return "home";
    }

    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }

    @GetMapping("/score")       /* get/current- длинный запрос, сократил */
    public String showScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return String.format("%s 's score is %s", user.getUsername(), user.getScore());
    }

    @GetMapping("/score/inc/{n}")
    public String increaseScore(Principal principal, @PathVariable Integer n) {
        User user = userService.findByUsername(principal.getName()).get();
        userService.increaseScore(user, n);
        return "current score for " + user.getUsername() + " : " + user.getScore();
    }

    @GetMapping("/score/dec/{n}")
    public String decreaseScore(Principal principal, @PathVariable Integer n) {
        User user = userService.findByUsername(principal.getName()).get();
        userService.decreaseScore(user, n);
        return "current score for " + user.getUsername() + " : " + user.getScore();
    }

    @GetMapping("/score/{id}")
    public String showScoreById(@PathVariable Long id) {
        return String.format("User with id = %s has score %s", id, userService.getScoreById(id));
    }

}
