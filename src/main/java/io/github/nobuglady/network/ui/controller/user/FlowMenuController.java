package io.github.nobuglady.network.ui.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class FlowMenuController {

	/**
	 * 
	 * @param flowId
	 * @param model
	 * @return
	 */
	@GetMapping("/flow_menu")
	public String flowNodeList(@RequestParam String flowId, Model model) {
		return "/user/flow_menu";
	}
}
