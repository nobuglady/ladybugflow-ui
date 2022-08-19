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

import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;
import io.github.nobuglady.network.ui.dao.mapper.HistoryEdgeMapper;

/**
 * NodeHistory table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class HistoryEdgeDao {

	@Autowired
	private HistoryEdgeMapper edgeHistoryMapper;

	//////////////////////////////////////
	// Base
	//////////////////////////////////////

	/**
	 * save
	 * 
	 * @param entity entity
	 */
	public void save(HistoryEdgeEntity entity) {
		entity.setCreateUser("testUser");
		entity.setUpdateUser("testUser");
		edgeHistoryMapper.save(entity);
	}

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	/**
	 * selectByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryEdgeEntity
	 */
	public List<HistoryEdgeEntity> selectByFlowHistoryId(String flowId, String historyId) {

		return edgeHistoryMapper.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * updateStatusByKey
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param edgeId     edgeId
	 * @param edgeStatus edgeStatus
	 */
	public void updateStatusByKey(String flowId, String historyId, String edgeId, int edgeStatus) {
		edgeHistoryMapper.updateStatusByKey(flowId, historyId, edgeId, edgeStatus, "testUser");
	}

	/**
	 * deleteByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return affect count
	 */
	public int deleteByFlowHistoryId(String flowId, String historyId) {
		return edgeHistoryMapper.deleteByFlowHistoryId(flowId, historyId);
	}

	/**
	 * deleteByFlowStatus
	 * 
	 * @param flowStatus flowStatus
	 */
	public void deleteByFlowStatus(int flowStatus) {
		edgeHistoryMapper.deleteByFlowStatus(flowStatus);
	}

}