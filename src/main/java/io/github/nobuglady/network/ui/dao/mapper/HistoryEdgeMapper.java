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

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface HistoryEdgeMapper {

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_edge " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and edge_id = #{param2}"
			+ " and history_id = #{param3}")
	// @formatter:on
	public HistoryEdgeEntity selectByKey(String flowId, String edgeId, String historyId);

	// @formatter:off
	@Insert("insert into history_edge" 
			+ " (" 
			+ "flow_id," 
			+ "edge_id," 
			+ "history_id," 
			+ "from_node_id,"
			+ "to_node_id," 
			+ "edge_name," 
			+ "ref_name," 
			+ "display_flag," 
			+ "edge_condition," 
			+ "edge_exception_return_type," 
			+ "skip_flag," 
			+ "skip_value," 
			+ "edge_status," 
			+ "disable_flag,"
			+ "create_user," 
			+ "update_user," 
			+ "create_time," 
			+ "update_time" 
			+ " )" 
			+ " values"
			+ " (" 
			+ "#{flowId}," 
			+ "#{edgeId}," 
			+ "#{historyId}," 
			+ "#{fromNodeId}," 
			+ "#{toNodeId},"
			+ "#{edgeName},"
			+ "#{refName},"
			+ "#{displayFlag},"
			+ "#{edgeCondition},"
			+ "#{edgeExceptionReturnType}," 
			+ "#{skipFlag}," 
			+ "#{skipValue}," 
			+ "#{edgeStatus}," 
			+ "#{disableFlag},"
			+ "#{createUser}," 
			+ "#{updateUser}," 
			+ "now()," 
			+ "now()" 
			+ " ) ")
	// @formatter:on
	public void save(HistoryEdgeEntity entity);

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_edge " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public List<HistoryEdgeEntity> selectByFlowHistoryId(String flowId, String historyId);

	// @formatter:off
	@Update("update history_edge " 
			+ "  set edge_status = #{param4}, " 
			+ "  update_user = #{param5}, " 
			+ "  update_time = now() " 
			+ " where" 
			+ " flow_id = #{param1} "
			+ " and history_id = #{param2} " 
			+ " and edge_id = #{param3} ")
	// @formatter:on
	public void updateStatusByKey(String flowId, String historyId, String edgeId, int edgeStatus, String userId);

	// @formatter:off
	@Delete("DELETE FROM history_edge " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public int deleteByFlowHistoryId(String flowId, String historyId);

	// @formatter:off
	@Delete("DELETE FROM history_edge " 
			+ " WHERE exists ( "
			+ " select 1 from history_flow t2 "
			+ " where " 
			+ " history_edge.flow_id = t2.flow_id"
			+ " and history_edge.history_id = t2.history_id "
			+ " and t2.flow_status = #{param1})")
	// @formatter:on
	public void deleteByFlowStatus(int flowStatus);

}