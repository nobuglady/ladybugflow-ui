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
package io.github.nobuglady.network.ui.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.AdminRoleBusiness;
import io.github.nobuglady.network.ui.service.dto.admin.RoleDeleteRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleDeleteResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleLoadListRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleLoadListResponseDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleSaveRequestDto;
import io.github.nobuglady.network.ui.service.dto.admin.RoleSaveResponseDto;

/**
 * Admin controller
 * 
 * @author NoBugLady
 *
 */
@Controller
public class AdminRoleController {

	@Autowired
	private AdminRoleBusiness adminRoleBusiness;
	
	/**
	 * delete role
	 * 
	 * @param id id
	 * @return response dto
	 */
	@RequestMapping(value = "/admin/request_api_role_delete", method = RequestMethod.POST)
	@ResponseBody
	public RoleDeleteResponseDto requestApiRoleDelete(@RequestParam(value = "id") String id) {

		RoleDeleteRequestDto requestDto = new RoleDeleteRequestDto();
		RoleDeleteResponseDto responseDto = new RoleDeleteResponseDto();

		requestDto.id = id;
		
		adminRoleBusiness.requestRoleDelete(requestDto, responseDto);
		
		return responseDto;

	}

	/**
	 * select all role
	 * 
	 * @return roles
	 */
	@RequestMapping(value = "/admin/request_role_load_list", method = RequestMethod.POST)
	@ResponseBody
	public RoleLoadListResponseDto requestRoleLoadList() {

		RoleLoadListRequestDto requestDto = new RoleLoadListRequestDto();
		RoleLoadListResponseDto responseDto = new RoleLoadListResponseDto();

		adminRoleBusiness.requestRoleList(requestDto, responseDto);
		return responseDto;

	}

	/**
	 * save role
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/admin/request_role_save", method = RequestMethod.POST)
	@ResponseBody
	public RoleSaveResponseDto requestRoleSave(@RequestBody RoleSaveRequestDto requestDto) {

//		RoleSaveRequestDto requestDto = new RoleSaveRequestDto();
		RoleSaveResponseDto responseDto = new RoleSaveResponseDto();

		adminRoleBusiness.saveRole(requestDto, responseDto);
		return responseDto;

	}

}
