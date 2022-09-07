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

import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface HistoryNodeMapper {

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and node_id = #{param2}"
			+ " and history_id = #{param3}")
	// @formatter:on
	public HistoryNodeEntity selectByKey(String flowId, String nodeId, String historyId);

	// @formatter:off
	@Insert("insert into history_node" 
			+ " (" 
			+ "flow_id," 
			+ "node_id," 
			+ "history_id," 
			+ "node_name,"
			+ "ref_name," 
			+ "ready_check," 
			+ "node_type," 
			+ "start_type," 
			+ "execute_type," 
			+ "start_cron," 
			+ "sub_flow_id,"
			+ "sub_node_id," 
			+ "layout_x," 
			+ "layout_y," 
			+ "skip_flag," 
			+ "skip_value," 
			+ "node_status," 
			+ "node_status_detail," 
			+ "return_value," 
			+ "start_time," 
			+ "finish_time," 
			+ "node_result_message," 
			+ "disable_flag," 
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
			+ "#{nodeName}," 
			+ "#{refName},"
			+ "#{readyCheck},"
			+ "#{nodeType}," 
			+ "#{startType}," 
			+ "#{executeType}," 
			+ "#{startCron}," 
			+ "#{subFlowId},"
			+ "#{subNodeId}," 
			+ "#{layoutX}," 
			+ "#{layoutY}," 
			+ "#{skipFlag}," 
			+ "#{skipValue}," 
			+ "#{nodeStatus}," 
			+ "#{nodeStatusDetail}," 
			+ "#{returnValue}," 
			+ "#{startTime}," 
			+ "#{finishTime}," 
			+ "#{nodeResultMessage}," 
			+ "#{disableFlag}," 
			+ "#{createUser}," 
			+ "#{updateUser}," 
			+ "now()," 
			+ "now()" 
			+ " ) ")
	// @formatter:on
	public void save(HistoryNodeEntity entity);

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public List<HistoryNodeEntity> selectByFlowHistoryId(String flowId, String historyId);

	// @formatter:off
	@Select("SELECT * FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}")
	// @formatter:on
	public List<HistoryNodeEntity> selectByFlowId(String flowId);

	// @formatter:off
	@Select("SELECT * FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}"
			+ " and node_status = #{param3}")
	// @formatter:on
	public List<HistoryNodeEntity> selectNodeListByStatus(String flowId, String historyId, int status);

	// @formatter:off
	@Select("SELECT * FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}"
			+ " and node_status = #{param3}" 
			+ " and node_status_detail = #{param4}")
	// @formatter:on
	public List<HistoryNodeEntity> selectNodeListByStatusDetail(String flowId, String historyId, int status,
			int statusDetail);

	// @formatter:off
	@Update("update history_node " 
			+ "  set node_status = #{param1}, " 
			+ "  update_user = #{param5}, "
			+ "  update_time = now() " 
			+ " where" 
			+ " flow_id = #{param2} " 
			+ " and history_id = #{param3} "
			+ " and node_id = #{param4} ")
	// @formatter:on
	public void updateStatusByNodeId(int status, String flowId, String historyId, String nodeId, String userId);

	// @formatter:off
	@Update("update history_node " 
			+ "  set node_status = #{param1}, " 
			+ "  node_status_detail = #{param2}, "
			+ "  update_user = #{param6}, " 
			+ "  update_time = now() " 
			+ " where" 
			+ " flow_id = #{param3} "
			+ " and history_id = #{param4} " 
			+ " and node_id = #{param5} ")
	// @formatter:on
	public void updateStatusDetailByNodeId(int status, int detailStatus, String flowId, String historyId, String nodeId,
			String userId);

	// @formatter:off
	@Update("update history_node " 
			+ "  set return_value = #{param4}, " 
			+ "  update_user = #{param5}, " 
			+ "  update_time = now() " 
			+ " where" 
			+ " flow_id = #{param1} "
			+ " and history_id = #{param2} " 
			+ " and node_id = #{param3} ")
	// @formatter:on
	public void updateReturnValueByNodeId(String flowId, String historyId, String nodeId, String returnValue,
			String userId);

	// @formatter:off
	@Delete("DELETE FROM history_node " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public int deleteByFlowHistoryId(String flowId, String historyId);

	// @formatter:off
	@Delete("DELETE FROM history_node " 
			+ " WHERE exists ( "
			+ " select 1 from history_flow t2 "
			+ " where " 
			+ " history_node.flow_id = t2.flow_id"
			+ " and history_node.history_id = t2.history_id "
			+ " and t2.flow_status = #{param1})")
	// @formatter:on
	public void deleteByFlowStatus(int flowStatus);

}