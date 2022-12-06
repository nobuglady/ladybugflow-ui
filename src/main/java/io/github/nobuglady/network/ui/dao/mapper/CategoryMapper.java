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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.nobuglady.network.ui.dao.entity.CategoryEntity;


/**
 * Category table mapper class
 * 
 * @author NoBugLady
 *
 */
@Mapper
public interface CategoryMapper {

	// @formatter:off
	@Insert("insert into category"
			+ " ("
			+ "id,"
			+ "category_name,"
			+ "category_type,"
			+ "parent,"
			+ "disabled,"
			+ "create_time,"
			+ "update_time"
			+ " )"
			+ " values"
			+ " ("
			+ "#{id},"
			+ "#{categoryName},"
			+ "#{categoryType},"
			+ "#{parent},"
			+ "#{disabled},"
			+ "now(),"
			+ "now()"
			+ " ) ")
	// @formatter:on
	public void save(CategoryEntity entity);
	
	// @formatter:off
	@Select("select * from category where parent = '1'")
	// @formatter:on
	public List<CategoryEntity> selectAllRootDir();
	
	// @formatter:off
	@Select("select * from category")
	// @formatter:on
	public List<CategoryEntity> selectAll();
	
	// @formatter:off
	@Update("update category set category_name = #{param2} where id = #{param1}")
	// @formatter:on
	public void rename(String id, String text);

	// @formatter:off
	@Update("update category set parent = #{param2} where id = #{param1}")
	// @formatter:on
	public void move(String id, String parent);
}
