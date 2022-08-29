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

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.component.IFlowAccessor;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.util.FlowUtil;

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
	 * @param flowId
	 * @param historyId
	 * @return
	 */
	public String getJson(String flowId, String historyId) {
		return FlowUtil.dumpJson(flowId, historyId);
	}

	public void clearComplete() {

		flowAccessor.removeAllComplete();
	}

	public void clearError() {

		flowAccessor.removeAllError();
	}
}
