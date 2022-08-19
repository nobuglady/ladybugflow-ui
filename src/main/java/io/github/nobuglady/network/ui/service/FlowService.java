package io.github.nobuglady.network.ui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.component.IFlowAccessor;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.util.FlowUtil;

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
