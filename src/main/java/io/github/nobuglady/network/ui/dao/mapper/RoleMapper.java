/*
 * Copyright (c) 2021-present, NoBugLady-mockserver Contributors.
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
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.nobuglady.network.ui.dao.entity.RoleEntity;

/**
 * Role table mapper class
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface RoleMapper {
	
	// @formatter:off
	@Insert("insert into trole"
			+ " ("
			+ "role_id,"
			+ "role_name,"
			+ "remarks,"
			+ "create_time,"
			+ "update_time"
			+ " )"
			+ " values"
			+ " ("
			+ "#{roleId},"
			+ "#{roleName},"
			+ "#{remarks},"
			+ "now(),"
			+ "now()"
			+ " ) ")
	// @formatter:on
	@Options(useGeneratedKeys=true, keyProperty="roleId")
	public void save(RoleEntity entity);
	
	// @formatter:off
	@Select("select * from trole")
	// @formatter:on
	public List<RoleEntity> selectAll();
	
	// @formatter:off
	@Select("select * from trole where role_id = #{param1}")
	// @formatter:on
	public RoleEntity get(String roleId);
	
	// @formatter:off
	@Update("update "
			+ " trole"
			+ " set role_name=#{roleName}, "
			+ " remarks=#{remarks}, "
			+ " update_time=now() "
			+ " where"
			+ " role_id = #{roleId}"
			+ "")
	// @formatter:on
	public void update(RoleEntity roleEntity);
	
	// @formatter:off
	@Delete("delete "
			+ " from trole"
			+ " where role_id = #{param1}"
			+ ""
			+ ""
			+ " ")
	// @formatter:on
	public void delete(String id);
}
