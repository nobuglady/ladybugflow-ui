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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.fw.constant.FlowStatus;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.ui.service.FlowService;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class FlowController {

	@Autowired
	private FlowService flowService;

	@GetMapping("/list")
	public String instanceList(Model model) {

		List<HistoryFlowEntity> flowEntityList = flowService.getAllFlowHistory();

		model.addAttribute("entityList", flowEntityList);
		model.addAttribute("FlowStatus", new FlowStatus());

		return "instance_list";
	}

	@GetMapping("/network")
	public String network(Model model) {
		return "network";
	}

	@GetMapping("/getJson")
	@ResponseBody
	public String getJson(@RequestParam String flowId, @RequestParam String historyId) {

		return flowService.getJson(flowId, historyId);

	}

	@RequestMapping(value = "/clearComplete", method = RequestMethod.POST)
	@ResponseBody
	public String clearComplete() {

		flowService.clearComplete();
		return "ok";

	}

	@RequestMapping(value = "/clearError", method = RequestMethod.POST)
	@ResponseBody
	public String clearError() {

		flowService.clearError();
		return "ok";

	}
}
