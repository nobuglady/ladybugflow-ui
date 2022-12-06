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
package io.github.nobuglady.network.ui.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.dao.entity.RoleEntity;
import io.github.nobuglady.network.ui.service.AdminUserBusiness;
import io.github.nobuglady.network.ui.service.dto.admin.UserDeleteRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserDeleteResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserLoadListRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserLoadListResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserLoadRoleRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserLoadRoleResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserSaveRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.UserSaveResponseDto;

/**
 * Admin controller
 * 
 * @author NoBugLady
 *
 */
@Controller
public class AdminUserController {

	@Autowired
	private AdminUserBusiness adminUserBusiness;
	
	/**
	 * delete user
	 * 
	 * @param id id
	 * @return response dto
	 */
	@RequestMapping(value = "/admin/request_api_user_delete", method = RequestMethod.POST)
	@ResponseBody
	public UserDeleteResponseDto requestApiUserDelete(@RequestParam(value = "id") String id) {

		UserDeleteRequestDto requestDto = new UserDeleteRequestDto();
		UserDeleteResponseDto responseDto = new UserDeleteResponseDto();

		requestDto.id = id;
		
		adminUserBusiness.requestUserDelete(requestDto, responseDto);
		return responseDto;

	}

	/**
	 * select all user
	 * 
	 * @return users
	 */
	@RequestMapping(value = "/admin/request_user_load_list", method = RequestMethod.POST)
	@ResponseBody
	public UserLoadListResponseDto requestUserLoadList() {

		UserLoadListRequestDto requestDto = new UserLoadListRequestDto();
		UserLoadListResponseDto responseDto = new UserLoadListResponseDto();

		adminUserBusiness.requestUserList(requestDto, responseDto);
		return responseDto;

	}


	/**
	 * select all role
	 * 
	 * @return roles
	 */
	@RequestMapping(value = "/admin/request_user_load_role", method = RequestMethod.POST)
	@ResponseBody
	public List<RoleEntity> requestUserLoadRole() {

		UserLoadRoleRequestDto requestDto = new UserLoadRoleRequestDto();
		UserLoadRoleResponseDto responseDto = new UserLoadRoleResponseDto();

		adminUserBusiness.requestRoleList(requestDto, responseDto);
		return responseDto.roleList;

	}


	/**
	 * save user
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/admin/request_user_save", method = RequestMethod.POST)
	@ResponseBody
	public UserSaveResponseDto requestUserSave(@RequestBody UserSaveRequestDto requestDto) {

//		UserSaveRequestDto requestDto = new UserSaveRequestDto();
		UserSaveResponseDto responseDto = new UserSaveResponseDto();

		adminUserBusiness.saveUser(requestDto, responseDto);
		return responseDto;

	}

}
