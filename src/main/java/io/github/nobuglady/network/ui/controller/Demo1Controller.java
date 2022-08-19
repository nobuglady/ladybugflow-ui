package io.github.nobuglady.network.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.Demo1Service;

@Controller
public class Demo1Controller {

	@Autowired
	private Demo1Service demo1Service;

	@RequestMapping(value = "/demo1", method = RequestMethod.GET)
	public String demo() {

		return "demo1";
	}

	@RequestMapping(value = "/bookingHotel", method = RequestMethod.POST)
	@ResponseBody
	public String bookingHotel(@RequestParam(value = "username") String username) {

		demo1Service.bookingHotel(username);

		return "ok";
	}
}
