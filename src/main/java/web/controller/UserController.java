package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/user")
	public String getUserPage2(Model model, Principal principal) {
		model.addAttribute("users", userService.findUserByUsername(principal.getName()));
		return "user-panel";
	}
}
