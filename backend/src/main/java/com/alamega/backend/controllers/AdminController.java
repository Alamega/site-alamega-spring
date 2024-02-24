package com.alamega.backend.controllers;

import com.alamega.backend.model.user.User;
import com.alamega.backend.model.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class AdminController {
    final
    UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public String admin() {
        return "admin/admin_panel";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll().toArray());
        return "admin/users";
    }

    @PostMapping("/users/role/{id}")
    @Transactional
    public String setUserRole(@PathVariable UUID id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.get();
            if (user.getRole().equals("USER")) {
                user.setRole("ADMIN");
            } else {
                user.setRole("USER");
            }
            userRepository.save(user);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}