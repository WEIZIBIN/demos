package github.weizibin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/web/index")
	public String webindex(){
		return "index";
	}
	
	@RequestMapping("/error/access_denied")
	public String access_denied(){
		return "common/access_denied";
	}
	
}
