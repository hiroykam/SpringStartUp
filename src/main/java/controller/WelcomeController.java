package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.UsersDAO;
import service.HelloWorldService;

@Controller
public class WelcomeController {
	@Autowired
    private UsersDAO usersDAO;
	
	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		logger.info("test");
		this.helloWorldService = helloWorldService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView thymeleaf(ModelAndView mv) {
		Long allUsersCount = usersDAO.allUsersCount();
		logger.info("allUsersCount = {}", allUsersCount);

		mv.addObject("count", allUsersCount.toString());
		mv.setViewName("thymeleaf");
		return mv;
	}

}