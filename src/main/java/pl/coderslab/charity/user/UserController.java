package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult, HttpServletRequest request) {
        if(bindResult.hasErrors())
            return "register";
        if(!request.getParameter("password2").equals(user.getPassword())) {
            return "error";
        }
        else {
            userService.addUser(user);
            return "confirmRegistration";
        }
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/all")
    public String findAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable long id,
                                   Model model) {
        Optional<User> user = userService.findUserById(id);
        if (!user.isPresent()) {
            model.addAttribute("errorMessage", "Nie odnaleziono użytkownika");
            return "error";
        }
        model.addAttribute("user", user.get());
        return "register";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedUser(@Valid User user,
                                 BindingResult result,
                                 @PathVariable long id,
                                 Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (user.getId() != id) {
            model.addAttribute("errorMessage", "id sie nie zgadzają");
            return "error";
        }
        userService.addUser(user);
        return "redirect:/user/all";
    }


}
