package io.github.nobuglady.network.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.constant.NodeStatusDetail;
import io.github.nobuglady.network.ui.flow.Demo2Flow;

@Service
public class Demo2Service {

	@Autowired
	private Demo2Flow demo2Flow;

	public String start(String username) {
		return demo2Flow.startFlow(false, username);
	}

	public void searchUser(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("your flow id", historyId, "a1a38c2e-0e05-4c68-bd49-f12aea070876",
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	public void searchHotel(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("your flow id", historyId, "1a90a997-4390-470a-ae7c-626a725438d2",
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	public void order(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("your flow id", historyId, "52289e99-363d-4453-8077-ca8bdc6d35bf",
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}

	public void success(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode("your flow id", historyId, "16422cbb-ccb0-4fe2-952b-e3ad5c3acbb2",
				NodeStatusDetail.COMPLETE_SUCCESS, "0");
	}
}
