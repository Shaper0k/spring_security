package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin/users")
	public String getAllUsers(ModelMap modelMap){
		List<User> users = userService.getAllUsers();
		modelMap.addAttribute("users", users);
		return "/user-list";
	}

	@GetMapping("/admin/user-create")
	public String addUserForm (User user){
		return "/user-create";
	}

	@PostMapping("/admin/user-create")
	public String addUser(User user){
		userService.addUser(user);
		return "user-list";
	}
	@GetMapping("/admin/user-delete/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id);
		return "user-list";
	}

	@GetMapping("/admin/user-update")
	public String userUpdateForm (@PathVariable("id") Long id, ModelMap modelMap){
		User user = userService.getUser(id);
		modelMap.addAttribute("user", user);
		return "/user-update";
	}
	@PatchMapping("/admin/user-update")
	public String userUpdate (User user){
		userService.updateUser(user);
		return "user-list";
	}

}
