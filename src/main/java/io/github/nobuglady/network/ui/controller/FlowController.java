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
