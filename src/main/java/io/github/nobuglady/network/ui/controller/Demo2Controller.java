package io.github.nobuglady.network.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.Demo2Service;

@Controller
public class Demo2Controller {

	@Autowired
	private Demo2Service demo2Service;

	@RequestMapping(value = "/demo2", method = RequestMethod.GET)
	public String demo() {
		return "demo2";
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public String start(@RequestParam(value = "username") String username) {
		return demo2Service.start(username);
	}
	
	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	@ResponseBody
	public String searchUser(@RequestParam(value = "historyId") String historyId) {
		demo2Service.searchUser(historyId);
		return "ok";
	}
	
	@RequestMapping(value = "/searchHotel", method = RequestMethod.POST)
	@ResponseBody
	public String searchHotel(@RequestParam(value = "historyId") String historyId) {
		demo2Service.searchHotel(historyId);
		return "ok";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public String order(@RequestParam(value = "historyId") String historyId) {
		demo2Service.order(historyId);
		return "ok";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	@ResponseBody
	public String success(@RequestParam(value = "historyId") String historyId) {
		demo2Service.success(historyId);
		return "ok";
	}
}
