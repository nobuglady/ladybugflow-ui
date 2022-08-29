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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.constant.NodeStatusDetail;
import io.github.nobuglady.network.ui.flow.Demo2Flow;

/**
 * 
 * @author NoBugLady
 *
 */
@Service
public class Demo2Service {

	@Autowired
	private Demo2Flow demo2Flow;

	/**
	 * Start flow
	 */
	public String start(String username) {
		return demo2Flow.startFlow(false, username);
	}

	/**
	 * Update node status by flowId,nodeId,HistoryId(click search user complete)
	 */
	public void searchUser(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("FLOW_2", historyId,
				"a1a38c2e-0e05-4c68-bd49-f12aea070876", // nodeId: search user
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	/**
	 * Update node status by flowId,nodeId,HistoryId(click search hotel complete)
	 */
	public void searchHotel(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("FLOW_2", historyId,
				"1a90a997-4390-470a-ae7c-626a725438d2", // nodeId: search hotel
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	/**
	 * Update node status by flowId,nodeId,HistoryId(click order complete)
	 */
	public void order(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("FLOW_2", historyId,
				"52289e99-363d-4453-8077-ca8bdc6d35bf", // nodeId: order
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	/**
	 * Update node status by flowId,nodeId,HistoryId(click order success complete)
	 */
	public void success(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("FLOW_2", historyId,
				"16422cbb-ccb0-4fe2-952b-e3ad5c3acbb2", // // nodeId: order success
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}
}
