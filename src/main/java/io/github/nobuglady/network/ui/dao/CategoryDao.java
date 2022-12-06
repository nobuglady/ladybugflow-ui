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
package io.github.nobuglady.network.ui.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.ui.dao.entity.CategoryEntity;
import io.github.nobuglady.network.ui.dao.mapper.CategoryMapper;

/**
 * Category table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class CategoryDao {
	
	@Autowired
	private CategoryMapper catagoryMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * select all root categories
	 * 
	 * @return categories
	 */
	public List<CategoryEntity> selectAllRootDir() {
		return catagoryMapper.selectAllRootDir();
	}

	/**
	 * select all categories
	 * 
	 * @param userId user id
	 * @return categories
	 */
	public List<CategoryEntity> selectAll() {

		return catagoryMapper.selectAll();
	}
	
	/**
	 * delete categories
	 * 
	 * @param deleteIdList delete id list
	 */
	public void delete(List<String> deleteIdList) {
		
		List<String> strList = new ArrayList<>();
		for(String str:deleteIdList) {
			strList.add("'"+str+"'");
		}
		
		jdbcTemplate.execute("delete from category where id in ("+String.join(",", strList)+")");
		
	}

	/**
	 * rename categories
	 * 
	 * @param id id
	 * @param text text
	 */
	public void rename(String id, String text) {
		catagoryMapper.rename(id, text);
		
	}

	/**
	 * save categories
	 * 
	 * @param entity entity
	 */
	public void save(CategoryEntity entity) {
		
		catagoryMapper.save(entity);
		
	}

	/**
	 * move categories
	 * 
	 * @param id id
	 * @param parent new parent
	 */
	public void move(String id, String parent) {
		catagoryMapper.move(id, parent);
	}

}
