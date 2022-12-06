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

import io.github.nobuglady.network.ui.dao.entity.UserEntity;

/**
 * User table mapper class
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface UserMapper {

	// @formatter:off
	@Insert("insert into tuser"
			+ " ("
			+ "user_id,"
			+ "user_name,"
			+ "email,"
			+ "passwd,"
			+ "remarks,"
			+ "admin_flag,"
			+ "create_time,"
			+ "update_time"
			+ " )"
			+ " values"
			+ " ("
			+ "#{userId},"
			+ "#{userName},"
			+ "#{email},"
			+ "#{passwd},"
			+ "#{remarks},"
			+ "#{adminFlag},"
			+ "now(),"
			+ "now()"
			+ " ) ")
	// @formatter:on
	@Options(useGeneratedKeys=true, keyProperty="userId")
	public void save(UserEntity entity);
	
	// @formatter:off
	@Select("select * from tuser")
	// @formatter:on
	public List<UserEntity> selectAll();
	
	// @formatter:off
	@Select("select * from tuser where user_id = #{param1}")
	// @formatter:on
	public UserEntity get(String userId);
	
	// @formatter:off
	@Update("update tuser "
			+ " set user_name=#{userName}, "
			+ " email=#{email}, "
			+ " passwd=#{passwd}, "
			+ " remarks=#{remarks}, "
			+ " update_time=now() "
			+ " where "
			+ " user_id = #{userId}"
			+ " ")
	// @formatter:on
	public void update(UserEntity userEntity);
	
	// @formatter:off
	@Delete("delete from tuser where user_id = #{param1}")
	// @formatter:on
	public void delete(String id);
	
	// @formatter:off
	@Select("select "
			+ " * "
			+ " from "
			+ " tuser"
			+ " where"
			+ " email = #{param1}"
			+ " and passwd = #{param2}"
			+ " ")
	// @formatter:on
	public UserEntity getByEmailPassword(String email, String password);
	
	// @formatter:off
	@Select("select "
			+ " * "
			+ " from "
			+ " tuser"
			+ " where"
			+ " email = #{param1}"
			+ " ")
	// @formatter:on
	public UserEntity getByEmail(String email);
}
