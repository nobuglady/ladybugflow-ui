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
package io.github.nobuglady.network.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.nobuglady.network.ui.dao.RoleDao;
import io.github.nobuglady.network.ui.dao.entity.RoleEntity;
import io.github.nobuglady.network.ui.service.dto.admin.RoleDeleteRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleDeleteResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleLoadListRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleLoadListResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleSaveRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleSaveResponseDto;

/**
 * Home business class
 * 
 * @author NoBugLady
 *
 */
@Service
@Transactional
public class AdminRoleBusiness {

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * request role delete
	 * 
	 * @param requestDto request dto
	 * @param responseDto response dto
	 */
	public void requestRoleDelete(RoleDeleteRequestDto requestDto,
			RoleDeleteResponseDto responseDto) {
		roleDao.delete(requestDto.id);
	}

	/**
	 * request role list
	 * 
	 * @param requestDto request dto
	 * @param responseDto response dto
	 */
	public void requestRoleList(RoleLoadListRequestDto requestDto,
			RoleLoadListResponseDto responseDto) {

		List<RoleEntity> roleList = roleDao.selectAll();
		
		responseDto.roleList.addAll(roleList);
	}

	/**
	 * save role
	 * 
	 * @param requestDto request dto
	 * @param responseDto response dto
	 */
	public void saveRole(RoleSaveRequestDto requestDto, RoleSaveResponseDto responseDto) {
		
		RoleEntity roleEntity = null;
		
		if(requestDto.roleId != null && !requestDto.roleId.equals("") && !requestDto.roleId.equals("null")) {

			roleEntity = roleDao.get(requestDto.roleId);
			roleEntity.setRoleName(requestDto.roleName);
			roleEntity.setRemarks(requestDto.roleRemarks);
			roleDao.update(roleEntity);
			
		}else {

			roleEntity = new RoleEntity();
			roleEntity.setRoleName(requestDto.roleName);
			roleEntity.setRemarks(requestDto.roleRemarks);
			roleDao.save(roleEntity);
			
		}
		
	}

}
