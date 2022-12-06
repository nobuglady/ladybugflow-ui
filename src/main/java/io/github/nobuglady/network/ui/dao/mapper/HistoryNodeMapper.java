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
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.nobuglady.network.fw.constant.NodeStatus;
import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeEntity;

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
			+ "display_flag," 
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
			+ "#{displayFlag},"
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
	@Select("SELECT "
			+ " flow.flow_id as flow_entity_flow_id, "
			+ " flow.history_id as flow_entity_history_id, "
			+ " flow.category_id as flow_entity_category_id, "
			+ " flow.flow_name as flow_entity_flow_name, "
			+ " flow.flow_desc as flow_entity_flow_desc, "
			+ " flow.start_param as flow_entity_start_param, "
			+ " flow.flow_status as flow_entity_flow_status, "
			+ " flow.flow_result as flow_entity_flow_result, "
			+ " flow.start_time as flow_entity_start_time, "
			+ " flow.finish_time as flow_entity_finish_time, "
			+ " flow.error_time as flow_entity_error_time, "
			+ " flow.disable_flag as flow_entity_disable_flag, "
			+ " flow.create_user as flow_entity_create_user, "
			+ " flow.update_user as flow_entity_update_user, "
			+ " flow.create_time as flow_entity_create_time, "
			+ " flow.update_time as flow_entity_update_time, "
			+ " edge.flow_id as edge_entity_flow_id, "
			+ " edge.edge_id as edge_entity_edge_id, "
			+ " edge.history_id as edge_entity_history_id, "
			+ " edge.from_node_id as edge_entity_from_node_id, "
			+ " edge.to_node_id as edge_entity_to_node_id, "
			+ " edge.edge_name as edge_entity_edge_name, "
			+ " edge.ref_name as edge_entity_ref_name, "
			+ " edge.display_flag as edge_entity_display_flag, "
			+ " edge.edge_condition as edge_entity_edge_condition, "
			+ " edge.edge_exception_return_type as edge_entity_edge_exception_return_type, "
			+ " edge.skip_flag as edge_entity_skip_flag, "
			+ " edge.skip_value as edge_entity_skip_value, "
			+ " edge.edge_status as edge_entity_edge_status, "
			+ " edge.disable_flag as edge_entity_disable_flag, "
			+ " edge.create_user as edge_entity_create_user, "
			+ " edge.update_user as edge_entity_update_user, "
			+ " edge.create_time as edge_entity_create_time, "
			+ " edge.update_time as edge_entity_update_time, "
			+ " node.* "
			+ " FROM"
			+ " history_node node"
			+ " inner join history_flow flow"
			+ " on node.flow_id = flow.flow_id "
			+ " and node.history_id = flow.history_id"
			+ " left outer join history_edge edge " 
			+ " on edge.from_node_id = node.node_id"
			+ " and edge.flow_id = node.flow_id"
			+ " and edge.history_id = node.history_id" 
			+ " WHERE "
			+ " node.flow_id = #{param1}" 
			+ " and node.node_id = #{param2}"
			+ " and (node.node_status = " + NodeStatus.OPENNING /*+" or node.node_status = " + NodeStatus.GO*/ +")")
    @Results({
        @Result(property = "historyFlowEntity", one = @One(resultMap = "flowResultMap", columnPrefix = "flow_entity_")),
        @Result(property = "historyEdgeEntityList", many = @Many(resultMap = "edgeResultMap", columnPrefix = "edge_entity_")),
        @Result(column = "flow_id", property = "flowId"),
        @Result(column = "node_id", property = "nodeId"),
        @Result(column = "history_id", property = "historyId"),
        @Result(column = "node_name", property = "nodeName"),
        @Result(column = "ref_name", property = "refName"),
        @Result(column = "display_flag", property = "displayFlag"),
        @Result(column = "ready_check", property = "readyCheck"),
        @Result(column = "start_type", property = "startType"),
        @Result(column = "execute_type", property = "executeType"),
        @Result(column = "start_cron", property = "startCron"),
        @Result(column = "sub_flow_id", property = "subFlowId"),
        @Result(column = "sub_node_id", property = "subNodeId"),
        @Result(column = "layout_x", property = "layoutX"),
        @Result(column = "layout_y", property = "layoutY"),
        @Result(column = "skip_flag", property = "skipFlag"),
        @Result(column = "skip_value", property = "skipValue"),
        @Result(column = "node_status", property = "nodeStatus"),
        @Result(column = "node_status_detail", property = "nodeStatusDetail"),
        @Result(column = "return_value", property = "returnValue"),
        @Result(column = "start_time", property = "startTime"),
        @Result(column = "finish_time", property = "finishTime"),
        @Result(column = "node_result_message", property = "nodeResultMessage"),
        @Result(column = "disable_flag", property = "disableFlag"),
        @Result(column = "create_user", property = "createUser"),
        @Result(column = "update_user", property = "updateUser"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime"),
      })
	// @formatter:on
	public List<CustomHistoryNodeEntity> selectByFlowNodeIdOpen(String flowId, String nodeId);


	// @formatter:off
	@Select("SELECT "
			+ " flow.flow_id as flow_entity_flow_id, "
			+ " flow.history_id as flow_entity_history_id, "
			+ " flow.category_id as flow_entity_category_id, "
			+ " flow.flow_name as flow_entity_flow_name, "
			+ " flow.flow_desc as flow_entity_flow_desc, "
			+ " flow.start_param as flow_entity_start_param, "
			+ " flow.flow_status as flow_entity_flow_status, "
			+ " flow.flow_result as flow_entity_flow_result, "
			+ " flow.start_time as flow_entity_start_time, "
			+ " flow.finish_time as flow_entity_finish_time, "
			+ " flow.error_time as flow_entity_error_time, "
			+ " flow.disable_flag as flow_entity_disable_flag, "
			+ " flow.create_user as flow_entity_create_user, "
			+ " flow.update_user as flow_entity_update_user, "
			+ " flow.create_time as flow_entity_create_time, "
			+ " flow.update_time as flow_entity_update_time, "
			+ " edge.flow_id as edge_entity_flow_id, "
			+ " edge.edge_id as edge_entity_edge_id, "
			+ " edge.history_id as edge_entity_history_id, "
			+ " edge.from_node_id as edge_entity_from_node_id, "
			+ " edge.to_node_id as edge_entity_to_node_id, "
			+ " edge.edge_name as edge_entity_edge_name, "
			+ " edge.ref_name as edge_entity_ref_name, "
			+ " edge.display_flag as edge_entity_display_flag, "
			+ " edge.edge_condition as edge_entity_edge_condition, "
			+ " edge.edge_exception_return_type as edge_entity_edge_exception_return_type, "
			+ " edge.skip_flag as edge_entity_skip_flag, "
			+ " edge.skip_value as edge_entity_skip_value, "
			+ " edge.edge_status as edge_entity_edge_status, "
			+ " edge.disable_flag as edge_entity_disable_flag, "
			+ " edge.create_user as edge_entity_create_user, "
			+ " edge.update_user as edge_entity_update_user, "
			+ " edge.create_time as edge_entity_create_time, "
			+ " edge.update_time as edge_entity_update_time, "
			+ " node.* "
			+ " FROM"
			+ " history_node node"
			+ " inner join history_flow flow"
			+ " on node.flow_id = flow.flow_id "
			+ " and node.history_id = flow.history_id"
			+ " left outer join history_edge edge " 
			+ " on edge.from_node_id = node.node_id"
			+ " and edge.flow_id = node.flow_id"
			+ " and edge.history_id = node.history_id" 
			+ " WHERE "
			+ " node.flow_id = #{param1}" 
			+ " and node.node_id = #{param2}"
			+ " and (node.node_status = " + NodeStatus.OPENNING +" or node.node_status = " + NodeStatus.GO +")")
    @Results({
        @Result(property = "historyFlowEntity", one = @One(resultMap = "flowResultMap", columnPrefix = "flow_entity_")),
        @Result(property = "historyEdgeEntityList", many = @Many(resultMap = "edgeResultMap", columnPrefix = "edge_entity_")),
        @Result(column = "flow_id", property = "flowId"),
        @Result(column = "node_id", property = "nodeId"),
        @Result(column = "history_id", property = "historyId"),
        @Result(column = "node_name", property = "nodeName"),
        @Result(column = "ref_name", property = "refName"),
        @Result(column = "display_flag", property = "displayFlag"),
        @Result(column = "ready_check", property = "readyCheck"),
        @Result(column = "start_type", property = "startType"),
        @Result(column = "execute_type", property = "executeType"),
        @Result(column = "start_cron", property = "startCron"),
        @Result(column = "sub_flow_id", property = "subFlowId"),
        @Result(column = "sub_node_id", property = "subNodeId"),
        @Result(column = "layout_x", property = "layoutX"),
        @Result(column = "layout_y", property = "layoutY"),
        @Result(column = "skip_flag", property = "skipFlag"),
        @Result(column = "skip_value", property = "skipValue"),
        @Result(column = "node_status", property = "nodeStatus"),
        @Result(column = "node_status_detail", property = "nodeStatusDetail"),
        @Result(column = "return_value", property = "returnValue"),
        @Result(column = "start_time", property = "startTime"),
        @Result(column = "finish_time", property = "finishTime"),
        @Result(column = "node_result_message", property = "nodeResultMessage"),
        @Result(column = "disable_flag", property = "disableFlag"),
        @Result(column = "create_user", property = "createUser"),
        @Result(column = "update_user", property = "updateUser"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime"),
      })
	// @formatter:on
	public List<CustomHistoryNodeEntity> selectByFlowNodeIdAll(String flowId, String nodeId);


	// @formatter:off
	@Select("SELECT "
			+ " flow.flow_id as flow_entity_flow_id, "
			+ " flow.history_id as flow_entity_history_id, "
			+ " flow.category_id as flow_entity_category_id, "
			+ " flow.flow_name as flow_entity_flow_name, "
			+ " flow.flow_desc as flow_entity_flow_desc, "
			+ " flow.start_param as flow_entity_start_param, "
			+ " flow.flow_status as flow_entity_flow_status, "
			+ " flow.flow_result as flow_entity_flow_result, "
			+ " flow.start_time as flow_entity_start_time, "
			+ " flow.finish_time as flow_entity_finish_time, "
			+ " flow.error_time as flow_entity_error_time, "
			+ " flow.disable_flag as flow_entity_disable_flag, "
			+ " flow.create_user as flow_entity_create_user, "
			+ " flow.update_user as flow_entity_update_user, "
			+ " flow.create_time as flow_entity_create_time, "
			+ " flow.update_time as flow_entity_update_time, "
			+ " edge.flow_id as edge_entity_flow_id, "
			+ " edge.edge_id as edge_entity_edge_id, "
			+ " edge.history_id as edge_entity_history_id, "
			+ " edge.from_node_id as edge_entity_from_node_id, "
			+ " edge.to_node_id as edge_entity_to_node_id, "
			+ " edge.edge_name as edge_entity_edge_name, "
			+ " edge.ref_name as edge_entity_ref_name, "
			+ " edge.display_flag as edge_entity_display_flag, "
			+ " edge.edge_condition as edge_entity_edge_condition, "
			+ " edge.edge_exception_return_type as edge_entity_edge_exception_return_type, "
			+ " edge.skip_flag as edge_entity_skip_flag, "
			+ " edge.skip_value as edge_entity_skip_value, "
			+ " edge.edge_status as edge_entity_edge_status, "
			+ " edge.disable_flag as edge_entity_disable_flag, "
			+ " edge.create_user as edge_entity_create_user, "
			+ " edge.update_user as edge_entity_update_user, "
			+ " edge.create_time as edge_entity_create_time, "
			+ " edge.update_time as edge_entity_update_time, "
			+ " node.* "
			+ " FROM"
			+ " history_node node"
			+ " inner join history_flow flow"
			+ " on node.flow_id = flow.flow_id "
			+ " and node.history_id = flow.history_id"
			+ " left outer join history_edge edge " 
			+ " on edge.from_node_id = node.node_id"
			+ " and edge.flow_id = node.flow_id"
			+ " and edge.history_id = node.history_id" 
			+ " WHERE "
			+ " node.flow_id = #{param1}" 
			+ " and node.node_id = #{param2}"
			+ " and node.create_user = #{param3}"
			+ " and (node.node_status = " + NodeStatus.OPENNING +" or node.node_status = " + NodeStatus.GO +")")
    @Results({
        @Result(property = "historyFlowEntity", one = @One(resultMap = "flowResultMap", columnPrefix = "flow_entity_")),
        @Result(property = "historyEdgeEntityList", many = @Many(resultMap = "edgeResultMap", columnPrefix = "edge_entity_")),
        @Result(column = "flow_id", property = "flowId"),
        @Result(column = "node_id", property = "nodeId"),
        @Result(column = "history_id", property = "historyId"),
        @Result(column = "node_name", property = "nodeName"),
        @Result(column = "ref_name", property = "refName"),
        @Result(column = "display_flag", property = "displayFlag"),
        @Result(column = "ready_check", property = "readyCheck"),
        @Result(column = "start_type", property = "startType"),
        @Result(column = "execute_type", property = "executeType"),
        @Result(column = "start_cron", property = "startCron"),
        @Result(column = "sub_flow_id", property = "subFlowId"),
        @Result(column = "sub_node_id", property = "subNodeId"),
        @Result(column = "layout_x", property = "layoutX"),
        @Result(column = "layout_y", property = "layoutY"),
        @Result(column = "skip_flag", property = "skipFlag"),
        @Result(column = "skip_value", property = "skipValue"),
        @Result(column = "node_status", property = "nodeStatus"),
        @Result(column = "node_status_detail", property = "nodeStatusDetail"),
        @Result(column = "return_value", property = "returnValue"),
        @Result(column = "start_time", property = "startTime"),
        @Result(column = "finish_time", property = "finishTime"),
        @Result(column = "node_result_message", property = "nodeResultMessage"),
        @Result(column = "disable_flag", property = "disableFlag"),
        @Result(column = "create_user", property = "createUser"),
        @Result(column = "update_user", property = "updateUser"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime"),
      })
	// @formatter:on
	public List<CustomHistoryNodeEntity> selectByFlowNodeIdAllByUser(String flowId, String nodeId, String userId);

	
    @Select("SELECT '1'")
    @Results(id = "flowResultMap", value = {
            @Result(column = "flow_id", property = "flowId"),
            @Result(column = "history_id", property = "historyId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "flow_name", property = "flowName"),
            @Result(column = "flow_desc", property = "flowDesc"),
            @Result(column = "start_param", property = "startParam"),
            @Result(column = "flow_status", property = "flowStatus"),
            @Result(column = "flow_result", property = "flowResult"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "error_time", property = "errorTime"),
            @Result(column = "disable_flag", property = "disableFlag"),
            @Result(column = "create_user", property = "createUser"),
            @Result(column = "update_user", property = "updateUser"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    HistoryFlowEntity __flowResultMap();
    
    @Select("SELECT '1'")
    @Results(id = "edgeResultMap", value = {
    		@Result(column = "flow_id", property = "flowId"),
    		@Result(column = "edge_id", property = "edgeId"),
    		@Result(column = "history_id", property = "historyId"),
    		@Result(column = "from_node_id", property = "fromNodeId"),
    		@Result(column = "to_node_id", property = "toNodeId"),
    		@Result(column = "edge_name", property = "edgeName"),
    		@Result(column = "ref_name", property = "refName"),
    		@Result(column = "display_flag", property = "displayFlag"),
    		@Result(column = "edge_condition", property = "edgeCondition"),
    		@Result(column = "edge_exception_return_type", property = "edgeExceptionReturnType"),
    		@Result(column = "skip_flag", property = "skipFlag"),
    		@Result(column = "skip_value", property = "skipValue"),
    		@Result(column = "edge_status", property = "edgeStatus"),
    		@Result(column = "disable_flag", property = "disableFlag"),
    		@Result(column = "create_user", property = "createUser"),
    		@Result(column = "update_user", property = "updateUser"),
    		@Result(column = "create_time", property = "createTime"),
    		@Result(column = "update_time", property = "updateTime"),
    })
    HistoryEdgeEntity __edgeResultMap();
    
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
	@Update("update history_node " 
			+ "  set start_time = now(), " 
			+ "  update_user = #{param4}, " 
			+ "  update_time = now() " 
			+ " where" 
			+ " flow_id = #{param1} "
			+ " and history_id = #{param2} " 
			+ " and node_id = #{param3} ")
	// @formatter:on
	public void updateNodeStartTime(String flowId, String historyId, String nodeId, String userId);

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