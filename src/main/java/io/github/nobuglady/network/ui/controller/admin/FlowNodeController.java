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
package io.github.nobuglady.network.ui.controller.admin;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.model.NodeDto;
import io.github.nobuglady.network.ui.controller.dto.CustomHistoryNodeEntityVo;
import io.github.nobuglady.network.ui.controller.dto.FlowEntityVo;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;
import io.github.nobuglady.network.ui.service.FlowService;
import io.github.nobuglady.network.ui.util.BeanUtil;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class FlowNodeController {

	@Autowired
	private FlowService flowService;

	/**
	 * 
	 * @param flowId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/flow_node_list")
	public String flowNodeList(@RequestParam String flowId, Model model) {
		List<FlowEntityVo> flowList = flowService.getAllFlow();
		model.addAttribute("flowList", flowList);
		
		List<NodeDto> nodeList = new ArrayList<>();
		
		FlowInfoEntity flowEntity = flowService.getFlowByKey(flowId);
		String json = flowEntity.getFlowJson();
		try (Reader reader = new StringReader(json)) {

			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			nodeList = flowDto.nodes;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("nodeList", nodeList);
		
		return "/admin/flow_node_list";
	}
	
	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/flow/node/instance/list")
	@ResponseBody
	public List<CustomHistoryNodeEntityVo> flowNodeList(
			@RequestParam("flowId") String flowId, 
			@RequestParam("nodeId") String nodeId, 
			Model model) {
		
		List<CustomHistoryNodeEntityVo> nodeList = BeanUtil.copyList(flowService.getNodeHistoryListOpen(flowId, nodeId),CustomHistoryNodeEntityVo.class);
		
		return nodeList;
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/flow/node/instance/list_all")
	@ResponseBody
	public List<CustomHistoryNodeEntityVo> flowNodeListAll(
			@RequestParam("flowId") String flowId, 
			@RequestParam("nodeId") String nodeId, 
			Model model) {
		
		List<CustomHistoryNodeEntityVo> nodeList = BeanUtil.copyList(flowService.getNodeHistoryListAll(flowId, nodeId),CustomHistoryNodeEntityVo.class);
		
		return nodeList;
	}
	
	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @param edgeId
	 * @param historyId
	 * @return
	 */
	@PostMapping("/admin/flow/node/instance/go")
	@ResponseBody
	public String flowNodeGo(
			@RequestParam("flowId") String flowId, 
			@RequestParam("nodeId") String nodeId, 
			@RequestParam("edgeId") String edgeId,
			@RequestParam("historyId") String historyId,
			Model model) {
		
		flowService.submitNodeToEdge(flowId, nodeId, edgeId, historyId);
		
		return "ok";
	}
	
	/**
	 * 
	 * @param flowId
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/flow/start", method = RequestMethod.POST)
	@ResponseBody
	public String bookingHotel(@RequestParam(value = "flowId") String flowId,
			@RequestParam(value = "username") String username) {

		flowService.startFlow(flowId,username);

		return "ok";
	}

}
