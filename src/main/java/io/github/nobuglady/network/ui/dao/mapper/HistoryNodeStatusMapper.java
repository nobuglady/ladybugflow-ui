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
package io.github.nobuglady.network.ui.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.dao.entity.HistoryNodeStatusEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface HistoryNodeStatusMapper {

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_node_status " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and node_id = #{param2}"
			+ " and history_id = #{param3}"
			+ " and history_status_seq = #{param4}")
	// @formatter:on
	public HistoryNodeStatusEntity selectByKey(String flowId, String nodeId, String historyId, int historyStatusSeq);

	// @formatter:off
	@Insert("insert into history_node_status" 
			+ " (" 
			+ "flow_id," 
			+ "node_id," 
			+ "history_id," 
			+ "history_status_seq,"
			+ "node_status," 
			+ "node_status_detail," 
			+ "remark," 
			+ "create_user," 
			+ "update_user," 
			+ "create_time," 
			+ "update_time" 
			+ " )" 
			+ " values"
			+ " (" 
			+ "#{flowId}," 
			+ "#{nodeId}," 
			+ "#{historyId}," 
			+ "#{historyStatusSeq}," 
			+ "#{nodeStatus}," 
			+ "#{nodeStatusDetail}," 
			+ "#{remark}," 
			+ "#{createUser}," 
			+ "#{updateUser}," 
			+ "now()," 
			+ "now()" 
			+ " ) ")
	// @formatter:on
	@Options(useGeneratedKeys=true, keyProperty="historyStatusSeq")
	public void save(HistoryNodeStatusEntity entity);

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_node_status " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and node_id = #{param2}" 
			+ " and history_id = #{param3}")
	// @formatter:on
	public List<HistoryNodeStatusEntity> selectByFlowNodeHistoryId(String flowId, String nodeId, String historyId);

	// @formatter:off
	@Select("SELECT "
			+ " node.node_name,"
			+ " status.*"
			+ " FROM history_node_status status, history_node node" 
			+ " WHERE"
			+ " status.flow_id = node.flow_id"
			+ " and status.node_id = node.node_id"
			+ " and status.history_id = node.history_id" 
			+ " and status.flow_id = #{param1}" 
			+ " and status.history_id = #{param2}"
			+ " and status.node_status = '100'")
	// @formatter:on
	public List<CustomHistoryNodeStatusEntity> selectByFlowHistoryId(String flowId, String historyId);

}