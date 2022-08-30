/*
 * Copyright (c) 2021-present, NoBugLady Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.github.nobuglady.network.ui.controller.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.Demo2Service;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class Demo2Controller {

	@Autowired
	private Demo2Service demo2Service;

	@RequestMapping(value = "/demo/demo2", method = RequestMethod.GET)
	public String demo() {
		return "/demo/demo2";
	}

	@RequestMapping(value = "/demo/demo2/start", method = RequestMethod.POST)
	@ResponseBody
	public String start(@RequestParam(value = "username") String username) {
		return demo2Service.start(username);
	}

	@RequestMapping(value = "/demo/demo2/searchUser", method = RequestMethod.POST)
	@ResponseBody
	public String searchUser(@RequestParam(value = "historyId") String historyId) {
		demo2Service.searchUser(historyId);
		return "ok";
	}

	@RequestMapping(value = "/demo/demo2/searchHotel", method = RequestMethod.POST)
	@ResponseBody
	public String searchHotel(@RequestParam(value = "historyId") String historyId) {
		demo2Service.searchHotel(historyId);
		return "ok";
	}

	@RequestMapping(value = "/demo/demo2/order", method = RequestMethod.POST)
	@ResponseBody
	public String order(@RequestParam(value = "historyId") String historyId) {
		demo2Service.order(historyId);
		return "ok";
	}

	@RequestMapping(value = "/demo/demo2/success", method = RequestMethod.POST)
	@ResponseBody
	public String success(@RequestParam(value = "historyId") String historyId) {
		demo2Service.success(historyId);
		return "ok";
	}
}
