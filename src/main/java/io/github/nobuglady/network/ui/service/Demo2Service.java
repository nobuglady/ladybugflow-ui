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

	/**
	 * 启动流程
	 */
	public String start(String username) {
		return demo2Flow.startFlow(false, username);
	}

	/**
	 * 根据流程ID，节点ID和启动实例ID更新节点状态(点击【查询用户信息】结束后调用)
	 */
	public void searchUser(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode(
				"FLOW_2", 
				historyId, 
				"a1a38c2e-0e05-4c68-bd49-f12aea070876", // 节点ID【查询用户信息】
				NodeStatusDetail.COMPLETE_SUCCESS, 
				"0");
	}
	
	/**
	 * 根据流程ID，节点ID和启动实例ID更新节点状态(点击【查询酒店信息】结束后调用)
	 */
	public void searchHotel(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode(
				"FLOW_2", 
				historyId, 
				"1a90a997-4390-470a-ae7c-626a725438d2",// 节点ID【查询酒店信息】
				NodeStatusDetail.COMPLETE_SUCCESS, 
				"0");
	}
	
	/**
	 * 根据流程ID，节点ID和启动实例ID更新节点状态(点击【下单】结束后调用)
	 */
	public void order(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode(
				"FLOW_2", 
				historyId, 
				"52289e99-363d-4453-8077-ca8bdc6d35bf",// 节点ID【下单】
				NodeStatusDetail.COMPLETE_SUCCESS, 
				"0");
	}
	
	/**
	 * 根据流程ID，节点ID和启动实例ID更新节点状态(点击【下单成功】结束后调用)
	 */
	public void success(String historyId) {
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode(
				"FLOW_2", 
				historyId, 
				"16422cbb-ccb0-4fe2-952b-e3ad5c3acbb2",// 节点ID【下单成功】
				NodeStatusDetail.COMPLETE_SUCCESS, 
				"0");
	}
}
