 package kr.ac.hansung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {
	// login?erorr
	@RequestMapping("/login")
	public String showLogin( @RequestParam(value="error", required=false) String error,  @RequestParam(value="error", required=false) String logout, Model model) {
	
		if(error != null) {
			model.addAttribute("errorMsg", "Invaild username and password");
		}
		if(logout != null) {
			model.addAttribute("logoutMsg", "U have been logout successfully");
		}
		//errorMsg 라는 키값으로 출 
		
		return "login";

	}
	
	
}


