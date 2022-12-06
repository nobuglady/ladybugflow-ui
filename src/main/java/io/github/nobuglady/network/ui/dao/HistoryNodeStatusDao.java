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

import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.dao.entity.HistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.dao.mapper.HistoryNodeStatusMapper;
import io.github.nobuglady.network.ui.util.AuthUtil;

/**
 * NodeHistoryStatus table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class HistoryNodeStatusDao {

	@Autowired
	private HistoryNodeStatusMapper nodeHistoryStatusMapper;

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	/**
	 * selectByKey
	 * 
	 * @param flowId    flowId
	 * @param nodeId    nodeId
	 * @param historyId historyId
	 * @param historyStatusSeq historyStatusSeq
	 * @return HistoryNodeStatusEntity
	 */
	public HistoryNodeStatusEntity selectByKey(String flowId, String nodeId, String historyId, int historyStatusSeq) {

		return nodeHistoryStatusMapper.selectByKey(flowId, nodeId, historyId, historyStatusSeq);
	}

	/**
	 * save
	 * 
	 * @param entity entity
	 */
	public void save(HistoryNodeStatusEntity entity) {
		entity.setCreateUser(String.valueOf(AuthUtil.getLoginUserId()));
		entity.setUpdateUser(String.valueOf(AuthUtil.getLoginUserId()));
		nodeHistoryStatusMapper.save(entity);
	}

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	/**
	 * selectByFlowNodeHistoryId
	 * 
	 * @param flowId flowId
	 * @param nodeId nodeId
	 * @param historyId historyId
	 * @return HistoryNodeStatusEntity
	 */
	public List<HistoryNodeStatusEntity> selectByFlowNodeHistoryId(String flowId, String nodeId, String historyId) {

		return nodeHistoryStatusMapper.selectByFlowNodeHistoryId(flowId, nodeId, historyId);
	}
	
	/**
	 * selectByFlowNodeHistoryId
	 * 
	 * @param flowId flowId
	 * @param historyId historyId
	 * @return CustomHistoryNodeStatusEntity
	 */
	public List<CustomHistoryNodeStatusEntity> selectByFlowHistoryId(String flowId, String historyId) {

		return nodeHistoryStatusMapper.selectByFlowHistoryId(flowId, historyId);
	}

}