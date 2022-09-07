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
package io.github.nobuglady.network.ui.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.nobuglady.network.fw.constant.FlowStatus;
import io.github.nobuglady.network.fw.persistance.entity.FlowEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.ui.SpringContextBridge;

/**
 * 
 * @author NoBugLady
 *
 */
public class FlowAccessorUI {

	/** key: flowId, historyId */
	public static Map<String, FlowEntity> flowMap = new HashMap<>();

	private HistoryFlowDao historyFlowDao;
	private HistoryNodeDao historyNodeDao;
	private HistoryEdgeDao historyEdgeDao;

	public FlowAccessorUI() {
		historyFlowDao = SpringContextBridge.getInstance().getHistoryFlowDao();
		historyNodeDao = SpringContextBridge.getInstance().getHistoryNodeDao();
		historyEdgeDao = SpringContextBridge.getInstance().getHistoryEdgeDao();
	}

	/**
	 * removeAllComplete
	 * 
	 */
	public void removeAllComplete() {

		historyNodeDao.deleteByFlowStatus(FlowStatus.COMPLETE);
		historyEdgeDao.deleteByFlowStatus(FlowStatus.COMPLETE);
		historyFlowDao.deleteAllByStatus(FlowStatus.COMPLETE);
	}

	/**
	 * removeAllError
	 */
	public void removeAllError() {

		historyNodeDao.deleteByFlowStatus(FlowStatus.ERROR);
		historyEdgeDao.deleteByFlowStatus(FlowStatus.ERROR);
		historyFlowDao.deleteAllByStatus(FlowStatus.ERROR);
	}

	/**
	 * selectAll
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<HistoryFlowEntity> selectAll() {
		return historyFlowDao.selectAll();
	}

}
