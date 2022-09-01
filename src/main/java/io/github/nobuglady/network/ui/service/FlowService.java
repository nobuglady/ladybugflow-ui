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
package io.github.nobuglady.network.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.component.IFlowAccessor;
import io.github.nobuglady.network.fw.constant.FlowStatus;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.util.FlowUtil;
import io.github.nobuglady.network.ui.controller.dto.FlowEntityVo;

/**
 * 
 * @author NoBugLady
 *
 */
@Service
public class FlowService {

	private IFlowAccessor flowAccessor = FlowComponentFactory.getFlowAccessor();

	/**
	 * 
	 * @return
	 */
	public List<HistoryFlowEntity> getAllFlowHistory() {
		return flowAccessor.selectAll();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FlowEntityVo> getAllFlow() {
		
		List<FlowEntityVo> flowEntityList = new ArrayList<>();
		
		Map<String,FlowEntityVo> flowMap = new HashMap<>();
		List<HistoryFlowEntity> historyList = flowAccessor.selectAll();
		for(HistoryFlowEntity historyEntity:historyList) {
			FlowEntityVo flowEntityVo = flowMap.get(historyEntity.getFlowId());
			if(flowEntityVo == null) {
				flowEntityVo = new FlowEntityVo();
				flowEntityVo.setFlowId(historyEntity.getFlowId());
				flowMap.put(historyEntity.getFlowId(), flowEntityVo);
				flowEntityList.add(flowEntityVo);
			}
			
			flowEntityVo.setHistoryCount(flowEntityVo.getHistoryCount() + 1);
			
			if(FlowStatus.PROCESSING == historyEntity.getFlowStatus() || FlowStatus.READY == historyEntity.getFlowStatus()) {
				flowEntityVo.setProcessingCount(flowEntityVo.getProcessingCount() + 1);
			}else if(FlowStatus.COMPLETE == historyEntity.getFlowStatus()) {
				flowEntityVo.setCompleteCount(flowEntityVo.getCompleteCount() + 1);
			}else if(FlowStatus.ERROR == historyEntity.getFlowStatus() || FlowStatus.CANCEL == historyEntity.getFlowStatus()) {
				flowEntityVo.setErrorCount(flowEntityVo.getErrorCount() + 1);
			}
		}
		
		return flowEntityList;
	}


	/**
	 * 
	 * @param flowId
	 * @param historyId
	 * @return
	 */
	public String getJsonHistory(String flowId, String historyId) {
		return FlowUtil.dumpJson(flowId, historyId);
	}
	
	/**
	 * 
	 * @param flowId
	 * @return
	 */
	public String getJsonFlow(String flowId) {
		return FlowUtil.dumpJsonFlow(flowId);
	}

	public void clearComplete() {

		flowAccessor.removeAllComplete();
	}

	public void clearError() {

		flowAccessor.removeAllError();
	}
}
