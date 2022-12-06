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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.model.NodeDto;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;
import io.github.nobuglady.network.ui.service.FlowService;
import io.github.nobuglady.network.ui.util.AuthUtil;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class ActionController {

	@Autowired
	private FlowService flowService;
	
    @RequestMapping("/action")
    public String action(@RequestParam String flowId, Model model) {

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
		
        return "/user/action";
    }

    @RequestMapping("/action_list")
    public String actionList() {
    	
        return "/user/action_list";
    }

    @RequestMapping("/action_detail")
    public String actionDetail() {
        return "/user/action_detail";
    }

}
