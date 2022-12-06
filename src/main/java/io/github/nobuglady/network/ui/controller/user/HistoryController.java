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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.nobuglady.network.ui.controller.dto.HistoryFlowEntityVo;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.service.FlowService;
import io.github.nobuglady.network.ui.util.AuthUtil;
import io.github.nobuglady.network.ui.util.BeanUtil;

/**
 * 
 * @author NoBugLady
 *
 */
@Controller
public class HistoryController {

	@Autowired
	private FlowService flowService;
	
    @RequestMapping("/history")
    public String history(@RequestParam String flowId, Model model) {
    	List<CustomHistoryFlowEntity> historyList = flowService.getFlowHistoryByIdUser(flowId, AuthUtil.getLoginUserId());
    	model.addAttribute("historyList", BeanUtil.copyList(historyList, HistoryFlowEntityVo.class));
        return "/user/history";
    }

    @RequestMapping("/history_list")
    public String historyList(
			@RequestParam("flowId") String flowId, 
			@RequestParam("historyId") String historyId, 
			Model model) {
    	List<CustomHistoryNodeStatusEntity> statusHistoryList = flowService.selectByFlowHistoryId(flowId,historyId);
    	model.addAttribute("statusHistoryList", statusHistoryList);
        return "/user/history_list";
    }

    @RequestMapping("/history_detail")
    public String historyDetail() {
        return "/user/history_detail";
    }

}
