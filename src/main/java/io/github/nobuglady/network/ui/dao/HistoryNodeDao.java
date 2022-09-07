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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;
import io.github.nobuglady.network.ui.dao.mapper.HistoryNodeMapper;

/**
 * NodeHistory table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class HistoryNodeDao {

	@Autowired
	private HistoryNodeMapper nodeHistoryMapper;

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	/**
	 * selectByKey
	 * 
	 * @param flowId    flowId
	 * @param nodeId    nodeId
	 * @param historyId historyId
	 * @return HistoryNodeEntity
	 */
	public HistoryNodeEntity selectByKey(String flowId, String nodeId, String historyId) {

		return nodeHistoryMapper.selectByKey(flowId, nodeId, historyId);
	}

	/**
	 * save
	 * 
	 * @param entity entity
	 */
	public void save(HistoryNodeEntity entity) {
		entity.setRefName(entity.getNodeName());
		entity.setCreateUser("testUser");
		entity.setUpdateUser("testUser");
		nodeHistoryMapper.save(entity);
	}

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	/**
	 * selectByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectByFlowHistoryId(String flowId, String historyId) {

		return nodeHistoryMapper.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * selectByFlowId
	 * 
	 * @param flowId flowId
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectByFlowId(String flowId) {

		return nodeHistoryMapper.selectByFlowId(flowId);
	}

	/**
	 * selectNodeListByStatus
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param status    status
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeListByStatus(String flowId, String historyId, int status) {

		return nodeHistoryMapper.selectNodeListByStatus(flowId, historyId, status);
	}

	/**
	 * selectNodeListByStatusDetail
	 * 
	 * @param flowId       flowId
	 * @param historyId    historyId
	 * @param status       status
	 * @param statusDetail statusDetail
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeListByStatusDetail(String flowId, String historyId, int status,
			int statusDetail) {

		return nodeHistoryMapper.selectNodeListByStatusDetail(flowId, historyId, status, statusDetail);
	}

	/**
	 * updateStatusByNodeId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param nodeId    nodeId
	 * @param status    status
	 */
	public void updateStatusByNodeId(String flowId, String historyId, String nodeId, int status) {

		nodeHistoryMapper.updateStatusByNodeId(status, flowId, historyId, nodeId, "testUser");
	}

	/**
	 * updateStatusDetailByNodeId
	 * 
	 * @param flowId       flowId
	 * @param instanceId   instanceId
	 * @param nodeId       nodeId
	 * @param status       status
	 * @param detailStatus detailStatus
	 */
	public void updateStatusDetailByNodeId(String flowId, String instanceId, String nodeId, int status,
			int detailStatus) {

		nodeHistoryMapper.updateStatusDetailByNodeId(status, detailStatus, flowId, instanceId, nodeId, "testUser");
	}

	/**
	 * deleteByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 */
	public void deleteByFlowHistoryId(String flowId, String historyId) {

		nodeHistoryMapper.deleteByFlowHistoryId(flowId, historyId);
	}

	/**
	 * updateReturnValueByNodeId
	 * 
	 * @param flowId      flowId
	 * @param historyId   historyId
	 * @param nodeId      nodeId
	 * @param returnValue returnValue
	 */
	public void updateReturnValueByNodeId(String flowId, String historyId, String nodeId, String returnValue) {
		nodeHistoryMapper.updateReturnValueByNodeId(flowId, historyId, nodeId, returnValue, "testUser");
	}

	/**
	 * deleteByFlowStatus
	 * 
	 * @param flowStatus flowStatus
	 */
	public void deleteByFlowStatus(int flowStatus) {
		nodeHistoryMapper.deleteByFlowStatus(flowStatus);
	}

}