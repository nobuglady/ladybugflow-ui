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
import org.apache.ibatis.annotations.Select;

import io.github.nobuglady.network.ui.dao.entity.CustomUserCategoryEntity;
import io.github.nobuglady.network.ui.dao.entity.UserCategoryEntity;

/**
 * UserCategory table mapper class
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface UserCategoryMapper {

	// @formatter:off
	@Insert("insert into user_category"
			+ " ("
			+ "user_id,"
			+ "category_id,"
			+ "create_time,"
			+ "update_time"
			+ " )"
			+ " values"
			+ " ("
			+ "#{userId},"
			+ "#{categoryId},"
			+ "now(),"
			+ "now()"
			+ " ) ")
	// @formatter:on
	public void save(UserCategoryEntity entity);

	// @formatter:off
	@Select("select "
			+ " a.*,"
			+ " b.category_name as category_name"
			+ " from"
			+ " user_category a,"
			+ " category b"
			+ " where"
			+ " a.category_id = b.id"
			+ " ")
	// @formatter:on
	public List<CustomUserCategoryEntity> selectAll();

	// @formatter:off
	@Select("select "
			+ " a.*,"
			+ " b.category_name as category_name"
			+ " from"
			+ " user_category a,"
			+ " category b"
			+ " where"
			+ " a.category_id = b.id"
			+ " and a.user_id = #{param1}"
			+ " ")
	// @formatter:on
	public List<CustomUserCategoryEntity> selectByUserId(Integer userId);
	
	// @formatter:off
	@Delete("delete "
			+ " from "
			+ " user_category"
			+ " where"
			+ " user_id = #{param1}")
	// @formatter:on
	public void deleteByUser(Integer user_id);
}
