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
import io.github.nobuglady.network.ui.controller.dto.FlowEntityVo;
import io.github.nobuglady.network.ui.controller.dto.HistoryFlowEntityVo;
import io.github.nobuglady.network.ui.service.FlowService;
import io.github.nobuglady.network.ui.util.BeanUtil;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class FlowController {

	@Autowired
	private FlowService flowService;

	@GetMapping("/")
	public String home(Model model) {
		return instanceList(model);
	}
	
	@GetMapping("/home")
	public String instanceList(Model model) {

		List<HistoryFlowEntity> instanceList = flowService.getAllFlowHistory();
		List<FlowEntityVo> flowList = flowService.getAllFlow();
		
		int flowCount = flowList.size();
		int instanceCount = 0;
		int completeCount = 0;
		int processingCount = 0;
		int errorCount = 0;
		
		for(FlowEntityVo flowEntity:flowList) {
			instanceCount += flowEntity.getHistoryCount();
			completeCount += flowEntity.getCompleteCount();
			processingCount += flowEntity.getProcessingCount();
			errorCount += flowEntity.getErrorCount();
		}
		
		model.addAttribute("flowCount", flowCount);
		model.addAttribute("instanceCount", instanceCount);
		model.addAttribute("completeCount", completeCount);
		model.addAttribute("processingCount", processingCount);
		model.addAttribute("errorCount", errorCount);
		
		model.addAttribute("instanceList", BeanUtil.copyList(instanceList, HistoryFlowEntityVo.class));
		model.addAttribute("flowList", flowList);
		model.addAttribute("FlowStatus", new FlowStatus());

		return "/home";
	}

	@GetMapping("/network_history")
	public String networkHistory(Model model) {
		return "/network_history";
	}
	
	@GetMapping("/network_flow")
	public String networkFlow(Model model) {
		return "/network_flow";
	}

	@GetMapping("/create_flow")
	public String createFlow(Model model) {
		return "/network";
	}

	@GetMapping("/flow/getJsonHistory")
	@ResponseBody
	public String getJsonHistory(@RequestParam String flowId, @RequestParam String historyId) {

		return flowService.getJsonHistory(flowId, historyId);

	}
	
	@GetMapping("/flow/getJsonFlow")
	@ResponseBody
	public String getJsonFlow(@RequestParam String flowId) {

		return flowService.getJsonFlow(flowId);

	}

	@RequestMapping(value = "/flow/clearComplete", method = RequestMethod.POST)
	@ResponseBody
	public String clearComplete() {

		flowService.clearComplete();
		return "ok";

	}

	@RequestMapping(value = "/flow/clearError", method = RequestMethod.POST)
	@ResponseBody
	public String clearError() {

		flowService.clearError();
		return "ok";

	}
}
