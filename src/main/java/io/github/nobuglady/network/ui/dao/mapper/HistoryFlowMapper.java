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

import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryFlowEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface HistoryFlowMapper {

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM history_flow " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public HistoryFlowEntity selectByKey(String flowId, String historyId);

	// @formatter:off
	@Insert("insert into history_flow" 
			+ " (" 
			+ "flow_id," 
			+ "history_id," 
			+ "category_id," 
			+ "flow_name,"
			+ "flow_desc," 
			+ "start_param," 
			+ "flow_status," 
			+ "flow_result," 
			+ "start_time," 
			+ "finish_time," 
			+ "error_time,"
			+ "disable_flag," 
			+ "create_user," 
			+ "update_user," 
			+ "create_time," 
			+ "update_time" 
			+ " )" 
			+ " values"
			+ " (" 
			+ "#{flowId}," 
			+ "#{historyId}," 
			+ "#{categoryId}," 
			+ "#{flowName}," 
			+ "#{flowDesc},"
			+ "#{startParam},"
			+ "#{flowStatus}," 
			+ "#{flowResult}," 
			+ "#{startTime}," 
			+ "#{finishTime}," 
			+ "#{errorTime},"
			+ "#{disableFlag}," 
			+ "#{createUser}," 
			+ "#{updateUser}," 
			+ "now()," 
			+ "now()" 
			+ " ) ")
	// @formatter:on
	public void save(HistoryFlowEntity entity);

	// @formatter:off
	@Delete("DELETE FROM history_flow " 
			+ " WHERE" 
			+ " flow_id = #{param1}" 
			+ " and history_id = #{param2}")
	// @formatter:on
	public int deleteByKey(String flowId, String historyId);

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT t1.flow_name, t2.* FROM flow_info t1 inner join history_flow t2 on t1.flow_id = t2.flow_id ")
	// @formatter:on
	public List<CustomHistoryFlowEntity> selectAll();
	
	// @formatter:off
	@Select("SELECT t1.flow_name, t2.* FROM flow_info t1 inner join history_flow t2 on t1.flow_id = t2.flow_id and t1.flow_id = #{param1} and t2.create_user = #{param2}")
	// @formatter:on
	public List<CustomHistoryFlowEntity> selectHistoryByIdUser(String flowId, String userId);

	// @formatter:off
	@Select("SELECT * FROM history_flow where flow_id = #{param1} order by create_time desc LIMIT 1 ")
	// @formatter:on
	public HistoryFlowEntity selectFlowHistoryLast(String flowId);

	// @formatter:off
	@Update("update history_flow" 
			+ " set finish_time=now()," 
			+ " flow_status=#{param3}, " 
			+ " update_user=#{param4}, "
			+ " update_time=now() " 
			+ " where" 
			+ " flow_id=#{param1} " 
			+ " and history_id = #{param2} ")
	// @formatter:on
	public void updateStatusAndFinishTime(String flowId, String historyId, int status, String userId);

	// @formatter:off
	@Update("update history_flow" 
			+ " set start_time=now()," 
			+ " flow_status=#{param3}, " 
			+ " update_user=#{param4}, "
			+ " update_time=now() " 
			+ " where" 
			+ " flow_id=#{param1} " 
			+ " and history_id = #{param2} ")
	// @formatter:on
	public void updateStatusAndStartTime(String flowId, String historyId, int status, String userId);

	// @formatter:off
	@Delete("DELETE FROM history_flow " 
			+ " WHERE" 
			+ " flow_status = #{param1}")
	// @formatter:on
	public void deleteAllByStatus(int flowStatus);

}