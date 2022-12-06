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
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.mapper.HistoryFlowMapper;
import io.github.nobuglady.network.ui.util.AuthUtil;

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
		entity.setCreateUser(String.valueOf(AuthUtil.getLoginUserId()));
		entity.setUpdateUser(String.valueOf(AuthUtil.getLoginUserId()));
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
	public List<CustomHistoryFlowEntity> selectAll() {
		return historyFlowMapper.selectAll();
	}

	/**
	 * selectAll
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<CustomHistoryFlowEntity> selectHistoryByIdUser(String flowId, int userId) {
		return historyFlowMapper.selectHistoryByIdUser(flowId, String.valueOf(userId));
	}

	/**
	 * selectFlowHistoryLast
	 * 
	 * @param flowId flowId
	 * @return last flowHistoryId
	 */
	public String selectFlowHistoryLast(String flowId) {
		HistoryFlowEntity entity = historyFlowMapper.selectFlowHistoryLast(flowId);

		if (entity == null) {
			return null;
		} else {
			return entity.getHistoryId();
		}
	}

	/**
	 * updateStatusAndFinishTime
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param status    status
	 */
	public void updateStatusAndFinishTime(String flowId, String historyId, int status) {

		historyFlowMapper.updateStatusAndFinishTime(flowId, historyId, status, String.valueOf(AuthUtil.getLoginUserId()));
	}

	/**
	 * updateStatusAndStartTime
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param status    status
	 */
	public void updateStatusAndStartTime(String flowId, String historyId, int status) {

		historyFlowMapper.updateStatusAndStartTime(flowId, historyId, status, String.valueOf(AuthUtil.getLoginUserId()));
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