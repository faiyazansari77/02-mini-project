package in.g77tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.g77tech.dto.LoginFormDto;
import in.g77tech.dto.SignupFormDto;
import in.g77tech.dto.UnlockFormDto;
import in.g77tech.service.UserService;

@Controller
public class UserDtlsController {


	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	public String handleSignupForm(@ModelAttribute("user") SignupFormDto formDto, Model model) {
		boolean signUp = service.signUp(formDto);
		if(signUp) {
			model.addAttribute("successMsg", "Account created! Please cheack your email");
		}
		else {
			model.addAttribute("errorMsg", "Please enter unique email!!");
		}
		
		return "signup";
	}
	
	
	@PostMapping("/unlock")
	public String handleUnlockAccountForm(@ModelAttribute("formDto") UnlockFormDto dto, Model model) {
		if(dto.getPassword().equals(dto.getConfirmPwd())) {
			boolean unlockAccount = service.unlockAccount(dto);
			if(unlockAccount) {
				model.addAttribute("successMsg", "Your account unlocked uccessully");
			}else {
				model.addAttribute("errorMsg", "Given temprory password is incorrect");
			}
		}else {
			model.addAttribute("errorMsg", "Password does not matched");
		}
		
		
		return "unlock";
	}
	
	@PostMapping("/login")
	public String handleLoginForm(@ModelAttribute("loginForm") LoginFormDto loginFormDto, Model model) {
		String status = service.login(loginFormDto);
		System.out.println(loginFormDto);
		if(status.equals("success")) {

//			redirect request to dashBoard method
			return "redirect:/dashboard";
		}
		
		model.addAttribute("errorMsg", status);
		return "login";
	}
	
	
	@PostMapping("/forgot_pwd")
	public String handleForgotPwdForm(@RequestParam("email") String email, Model m) {
		System.out.println(email);
		boolean msg = service.forgotPwd(email);
		if(msg) {
			m.addAttribute("successMsg", "Password has been sent to your email");
		}else {
			System.out.println("wrong email");
			m.addAttribute("errorMsg", "Please enter valid email");
		}
		return "forgot_pwd";
	}
	
	
	@GetMapping("/login")
	public String getLoginForm(Model m) {
		m.addAttribute("loginForm", new LoginFormDto());
		return "login";
	}
	
	@GetMapping("/signup")
	public String getSignupForm(Model model) {
		model.addAttribute("user", new SignupFormDto());
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String getUnlockForm(@RequestParam String email, Model model) {
		UnlockFormDto formDto = new UnlockFormDto();
		formDto.setEmail(email);
		model.addAttribute("formDto", formDto);
		return "unlock";
	}
	
	@GetMapping("/forgetPwd")
	public String getForgetPwdForm() {
		return "forgot_pwd";
	}
}
