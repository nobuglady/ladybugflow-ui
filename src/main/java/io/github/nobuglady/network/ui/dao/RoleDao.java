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
 * the License for the specific language governing roles and limitations under the License.
 */
package io.github.nobuglady.network.ui.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.ui.dao.entity.RoleEntity;
import io.github.nobuglady.network.ui.dao.mapper.RoleMapper;

/**
 * Role table operation class
 * 
 * @author NoBugLady
 *
 */
@Component
public class RoleDao {

	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * save entity
	 * 
	 * @param entity entity
	 */
	public void save(RoleEntity entity) {
		roleMapper.save(entity);
	}

	/**
	 * select all role
	 * 
	 * @return role list
	 */
	public List<RoleEntity> selectAll() {

		return roleMapper.selectAll();
	}

	/**
	 * select role by id
	 * 
	 * @param roleId role id
	 * @return role
	 */
	public RoleEntity get(String roleId) {

		return roleMapper.get(roleId);
	}

	/**
	 * update role
	 * 
	 * @param roleEntity role
	 */
	public void update(RoleEntity roleEntity) {

		roleMapper.update(roleEntity);
	}

	/**
	 * delete role
	 * 
	 * @param id role id
	 */
	public void delete(String id) {

		roleMapper.delete(id);
	}
}
