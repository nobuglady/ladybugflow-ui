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
package io.github.nobuglady.network.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.Demo1Service;

/**
 * 
 * @author NoBugLady
 *
 */
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
