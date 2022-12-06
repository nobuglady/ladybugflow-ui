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

import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface FlowInfoMapper {

	//////////////////////////////////////
	// Base
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM flow_info " 
			+ " WHERE" 
			+ " flow_id = #{param1}")
	// @formatter:on
	public FlowInfoEntity selectByKey(String flowId);

	// @formatter:off
	@Insert("insert into flow_info" 
			+ " (" 
			+ "flow_id," 
			+ "flow_name,"
			+ "flow_desc," 
			+ "flow_json," 
			+ "disable_flag," 
			+ "create_user," 
			+ "update_user," 
			+ "create_time," 
			+ "update_time" 
			+ " )" 
			+ " values"
			+ " (" 
			+ "#{flowId}," 
			+ "#{flowName}," 
			+ "#{flowDesc},"
			+ "#{flowJson},"
			+ "#{disableFlag}," 
			+ "#{createUser}," 
			+ "#{updateUser}," 
			+ "now()," 
			+ "now()" 
			+ " ) ")
	// @formatter:on
	public void save(FlowInfoEntity entity);

	// @formatter:off
	@Update("update flow_info " 
			+ " set" 
			+ " flow_name=#{flowName}"
			+ " ,flow_desc=#{flowDesc}" 
			+ " ,flow_json=#{flowJson}" 
			+ " ,disable_flag=#{disableFlag}"
			+ " ,update_user=#{updateUser}" 
			+ " ,update_time=now()" 
			+ " where" 
			+ " flow_id=#{flowId}")
	// @formatter:on
	public void update(FlowInfoEntity entity);

	// @formatter:off
	@Delete("DELETE FROM flow_info " 
			+ " WHERE" 
			+ " flow_id = #{param1}")
	// @formatter:on
	public int deleteByKey(String flowId);

	//////////////////////////////////////
	// Extends
	//////////////////////////////////////
	// @formatter:off
	@Select("SELECT * FROM flow_info ")
	// @formatter:on
	public List<FlowInfoEntity> selectAll();

}