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

import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;
import io.github.nobuglady.network.ui.dao.mapper.FlowInfoMapper;
import io.github.nobuglady.network.ui.util.AuthUtil;

/**
 * FlowInfo table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class FlowInfoDao {

	@Autowired
	private FlowInfoMapper flowInfoMapper;

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	/**
	 * selectByKey
	 * 
	 * @param flowId    flowId
	 * @return FlowInfoEntity
	 */
	public FlowInfoEntity selectByKey(String flowId) {

		return flowInfoMapper.selectByKey(flowId);
	}

	/**
	 * save
	 * 
	 * @param entity entity
	 */
	public void save(FlowInfoEntity entity) {
		entity.setCreateUser(String.valueOf(AuthUtil.getLoginUserId()));
		entity.setUpdateUser(String.valueOf(AuthUtil.getLoginUserId()));
		flowInfoMapper.save(entity);
	}

	/**
	 * update
	 * 
	 * @param entity entity
	 */
	public void update(FlowInfoEntity entity) {
		entity.setUpdateUser(String.valueOf(AuthUtil.getLoginUserId()));
		flowInfoMapper.update(entity);
	}


	/**
	 * deleteByKey
	 * 
	 * @param flowId flowId
	 */
	public void deleteByKey(String flowId) {
		flowInfoMapper.deleteByKey(flowId);
	}

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////

	/**
	 * selectAll
	 * 
	 * @return HistoryFlowEntity
	 */
	public List<FlowInfoEntity> selectAll() {
		return flowInfoMapper.selectAll();
	}

}