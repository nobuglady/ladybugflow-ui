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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.constant.FlowStatus;
import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.persistance.entity.FlowEntity;
import io.github.nobuglady.network.ui.SpringContextBridge;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;

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
	private HistoryNodeStatusDao historyNodeStatusDao;
	private FlowInfoDao flowInfoDao;

	public FlowAccessorUI() {
		historyFlowDao = SpringContextBridge.getInstance().getHistoryFlowDao();
		historyNodeDao = SpringContextBridge.getInstance().getHistoryNodeDao();
		historyEdgeDao = SpringContextBridge.getInstance().getHistoryEdgeDao();
		historyNodeStatusDao = SpringContextBridge.getInstance().getHistoryNodeStatusDao();
		flowInfoDao = SpringContextBridge.getInstance().getFlowInfoDao();
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
	 * selectAllHistory
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<CustomHistoryFlowEntity> selectAllHistory() {
		return historyFlowDao.selectAll();
	}

	/**
	 * selectAllHistory
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<CustomHistoryFlowEntity> selectHistoryByIdUser(String flowId, int userId) {
		return historyFlowDao.selectHistoryByIdUser(flowId, userId);
	}

	/**
	 * selectByFlowHistoryId
	 * 
	 * @return CustomHistoryNodeStatusEntity
	 */
	public List<CustomHistoryNodeStatusEntity> selectByFlowHistoryId(String flowId, String historyId) {
		return historyNodeStatusDao.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * selectAllFlow
	 * 
	 * @return FlowInfoEntity
	 */
	public List<FlowInfoEntity> selectAllFlow() {
		return flowInfoDao.selectAll();
	}

	public FlowInfoEntity selectFlowByKey(String flowId) {
		return flowInfoDao.selectByKey(flowId);
	}

	/**
	 * saveFlowInfo
	 * 
	 * @param json
	 */
	public void saveOrUpdateFlowInfo(String json) {
		
		json = json.trim();
		
		try (Reader reader = new StringReader(json)) {

			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);

			FlowInfoEntity flowInfoEntity = flowInfoDao.selectByKey(flowDto.flowId);

			if (flowInfoEntity != null) {
				if (!flowInfoEntity.getFlowJson().equals(json)) {
					flowInfoEntity.setFlowName(flowDto.flowName);
					flowInfoEntity.setFlowDesc("");
					flowInfoEntity.setFlowJson(json);

					flowInfoDao.update(flowInfoEntity);
				}
			} else {

				flowInfoEntity = new FlowInfoEntity();
				flowInfoEntity.setFlowId(flowDto.flowId);
				flowInfoEntity.setFlowName(flowDto.flowName);
				flowInfoEntity.setFlowDesc("");
				flowInfoEntity.setFlowJson(json);

				flowInfoDao.save(flowInfoEntity);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> selectNodeHistoryListOpen(String flowId, String nodeId) {
		return this.historyNodeDao.selectByFlowNodeIdOpen(flowId, nodeId);
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> selectNodeHistoryListAll(String flowId, String nodeId) {
		return this.historyNodeDao.selectByFlowNodeIdAll(flowId, nodeId);
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> getNodeHistoryListAllByUser(String flowId, String nodeId, int userId) {
		return this.historyNodeDao.getNodeHistoryListAllByUser(flowId, nodeId, userId);
	}

}
