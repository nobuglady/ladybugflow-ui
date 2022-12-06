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
 * the License for the specific language governing users and limitations under the License.
 */
package io.github.nobuglady.network.ui.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.ui.dao.entity.UserEntity;
import io.github.nobuglady.network.ui.dao.mapper.UserMapper;

/**
 * User table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class UserDao {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * save entity
	 * 
	 * @param entity entity
	 */
	public void save(UserEntity entity) {
		userMapper.save(entity);
	}

	/**
	 * select all user
	 * 
	 * @return user list
	 */
	public List<UserEntity> selectAll() {

		return userMapper.selectAll();
	}

	/**
	 * select user by id
	 * 
	 * @param userId user id
	 * @return user
	 */
	public UserEntity get(String userId) {

		return userMapper.get(userId);
	}

	/**
	 * update user
	 * 
	 * @param userEntity user
	 */
	public void update(UserEntity userEntity) {

		userMapper.update(userEntity);
	}

	/**
	 * delete user
	 * 
	 * @param id user id
	 */
	public void delete(String id) {

		userMapper.delete(id);
	}

	/**
	 * get by email and password
	 * 
	 * @param email email
	 * @param password password
	 * @return user entity
	 */
	public UserEntity getByEmailPassword(String email, String password) {

		return userMapper.getByEmailPassword(email, password);
	}
	
	public UserEntity getByEmail(String email) {

		return userMapper.getByEmail(email);
	}
}
