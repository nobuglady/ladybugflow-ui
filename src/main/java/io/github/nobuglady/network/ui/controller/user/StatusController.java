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
package io.github.nobuglady.network.ui.controller.user;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.model.NodeDto;
import io.github.nobuglady.network.ui.controller.dto.CustomHistoryNodeEntityVo;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;
import io.github.nobuglady.network.ui.service.FlowService;
import io.github.nobuglady.network.ui.util.AuthUtil;
import io.github.nobuglady.network.ui.util.BeanUtil;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class StatusController {

	@Autowired
	private FlowService flowService;
	
    @RequestMapping("/status")
    public String status(@RequestParam String flowId, Model model) {

		List<NodeDto> nodeList = new ArrayList<>();
		
		FlowInfoEntity flowEntity = flowService.getFlowByKey(flowId);
		String json = flowEntity.getFlowJson();
		try (Reader reader = new StringReader(json)) {

			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			nodeList = flowDto.nodes;

			// check auth
			for(int i = nodeList.size()-1; i >= 0; i--) {
				NodeDto nodeDto = nodeList.get(i);
				if(!AuthUtil.checkFlowNodeAuth(json, nodeDto.id)) {
					nodeList.remove(i);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("flowId", flowId);
		model.addAttribute("nodeList", nodeList);
		
        return "/user/status";
    }

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @param model
	 * @return
	 */
	@GetMapping("/flow/node/instance/list_all")
	@ResponseBody
	public List<CustomHistoryNodeEntityVo> flowNodeListAll(
			@RequestParam("flowId") String flowId, 
			@RequestParam("nodeId") String nodeId, 
			Model model) {

		int watchFlag = 0;
		
		FlowInfoEntity flowEntity = flowService.getFlowByKey(flowId);
		String json = flowEntity.getFlowJson();
		try (Reader reader = new StringReader(json)) {

			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			List<NodeDto> nodeList = flowDto.nodes;

			for(NodeDto nodeDto:nodeList) {
				if(nodeDto.id.equals(nodeId)) {
					watchFlag = nodeDto.watchFlag;
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(watchFlag == 0) {
			List<CustomHistoryNodeEntityVo> nodeList = BeanUtil.copyList(flowService.getNodeHistoryListAll(flowId, nodeId),CustomHistoryNodeEntityVo.class);
			return nodeList;
		}else {
			List<CustomHistoryNodeEntityVo> nodeList = BeanUtil.copyList(flowService.getNodeHistoryListAllByUser(flowId, nodeId, AuthUtil.getLoginUserId()),CustomHistoryNodeEntityVo.class);
			return nodeList;
		}
	}
	
    @RequestMapping("/status_list")
    public String statusList() {
        return "/user/status_list";
    }

    @RequestMapping("/status_detail")
    public String statusDetail() {
        return "/user/status_detail";
    }

}
