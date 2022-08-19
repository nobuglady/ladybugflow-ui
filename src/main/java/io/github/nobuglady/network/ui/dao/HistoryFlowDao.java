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

import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.mapper.HistoryFlowMapper;

/**
 * FlowHistory table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class HistoryFlowDao {

	@Autowired
	private HistoryFlowMapper historyFlowMapper;

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	/**
	 * selectByKey
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryFlowEntity
	 */
	public HistoryFlowEntity selectByKey(String flowId, String historyId) {

		return historyFlowMapper.selectByKey(flowId, historyId);
	}

	/**
	 * save
	 * 
	 * @param entity entity
	 */
	public void save(HistoryFlowEntity entity) {
		entity.setCreateUser("testUser");
		entity.setUpdateUser("testUser");
		historyFlowMapper.save(entity);
	}

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////

	/**
	 * selectAll
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<HistoryFlowEntity> selectAll() {
		return historyFlowMapper.selectAll();
	}

	/**
	 * updateFlowStatus
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param status    status
	 */
	public void updateFlowStatus(String flowId, String historyId, int status) {

		historyFlowMapper.updateStatus(flowId, historyId, status, "testUser");
	}

	/**
	 * deleteAllByKey
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 */
	public void deleteAllByKey(String flowId, String historyId) {

		historyFlowMapper.deleteByKey(flowId, historyId);
	}

	/**
	 * deleteAllByStatus
	 * 
	 * @param flowStatus flowStatus
	 */
	public void deleteAllByStatus(int flowStatus) {
		historyFlowMapper.deleteAllByStatus(flowStatus);
	}

}