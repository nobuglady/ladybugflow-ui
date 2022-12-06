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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.ui.dao.entity.CustomUserCategoryEntity;
import io.github.nobuglady.network.ui.dao.entity.UserCategoryEntity;
import io.github.nobuglady.network.ui.dao.mapper.UserCategoryMapper;

/**
 * UserCategory table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class UserCategoryDao {

	@Autowired
	private UserCategoryMapper userCategoryMapper;
	
	/**
	 * save list
	 * 
	 * @param entityList entity list
	 */
	public void saveList(List<UserCategoryEntity> entityList) {
		if(entityList != null) {
			for(UserCategoryEntity entity:entityList) {
				userCategoryMapper.save(entity);
			}
		}
	}

	/**
	 * select all permission
	 * 
	 * @return permission list
	 */
	public List<CustomUserCategoryEntity> selectAll() {

		return userCategoryMapper.selectAll();
	}

	/**
	 * selectByUserId
	 * 
	 * @param userId userId
	 * @return CustomUserCategoryEntity
	 */
	public List<CustomUserCategoryEntity> selectByUserId(Integer userId) {

		return userCategoryMapper.selectByUserId(userId);
	}
	
	/**
	 * delete user_role by user_id
	 * 
	 * @param user_id user id
	 */
	public void deleteByUser(Integer user_id) {

		userCategoryMapper.deleteByUser(user_id);
	}
}
