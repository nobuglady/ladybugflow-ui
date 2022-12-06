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
package io.github.nobuglady.network.ui.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.nobuglady.network.ui.service.dto.category.CatagoryCreateRequestDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryCreateResponseDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryDeleteRequestDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryDeleteResponseDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryMoveRequestDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryMoveResponseDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryRenameRequestDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryRenameResponseDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagoryRes;
import io.github.nobuglady.network.ui.service.dto.category.CatagorySelectRequestDto;
import io.github.nobuglady.network.ui.service.dto.category.CatagorySelectResponseDto;
import io.github.nobuglady.network.ui.service.AdminCategoryBusiness;

/**
 * Home controller
 * 
 * @author NoBugLady
 *
 */
@Controller
public class AdminCategoryController {

	@Autowired
	private AdminCategoryBusiness homeBusiness;
	
	/**
	 * select all category
	 * 
	 * @param session session
	 * @return categories
	 */
	@RequestMapping(value = "/home/request_catagory_select", method = RequestMethod.GET)
	@ResponseBody
	public CatagoryRes requestCatagorySelect(HttpSession session) {

		CatagorySelectRequestDto requestDto = new CatagorySelectRequestDto();
		CatagorySelectResponseDto responseDto = new CatagorySelectResponseDto();

		homeBusiness.requestCatagorySelect(requestDto, responseDto, session.getId());
		return responseDto.catagoryRes;

	}

	/**
	 * delete category
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/home/request_catagory_delete", method = RequestMethod.POST)
	@ResponseBody
	public CatagoryDeleteResponseDto requestCatagoryDelete(@RequestBody CatagoryDeleteRequestDto requestDto) {

//		CatagoryDeleteRequestDto requestDto = new CatagoryDeleteRequestDto();
		CatagoryDeleteResponseDto responseDto = new CatagoryDeleteResponseDto();

		homeBusiness.requestCatagoryDelete(requestDto, responseDto);
		return responseDto;

	}

	/**
	 * rename category
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/home/request_catagory_rename", method = RequestMethod.POST)
	@ResponseBody
	public CatagoryRenameResponseDto requestCatagoryRename(@RequestBody CatagoryRenameRequestDto requestDto) {

//		CatagoryRenameRequestDto requestDto = new CatagoryRenameRequestDto();
		CatagoryRenameResponseDto responseDto = new CatagoryRenameResponseDto();

		homeBusiness.requestCatagoryRename(requestDto, responseDto);
		return responseDto;

	}

	/**
	 * create category
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/home/request_catagory_create", method = RequestMethod.POST)
	@ResponseBody
	public CatagoryCreateResponseDto requestCatagoryCreate(@RequestBody CatagoryCreateRequestDto requestDto) {

//		CatagoryCreateRequestDto requestDto = new CatagoryCreateRequestDto();
		CatagoryCreateResponseDto responseDto = new CatagoryCreateResponseDto();

		homeBusiness.requestCatagoryCreate(requestDto, responseDto);
		return responseDto;

	}

	/**
	 * move category
	 * 
	 * @param requestDto request dto
	 * @return response dto
	 */
	@RequestMapping(value = "/home/request_catagory_move", method = RequestMethod.POST)
	@ResponseBody
	public CatagoryMoveResponseDto requestMoveCreate(@RequestBody CatagoryMoveRequestDto requestDto) {

//		CatagoryMoveRequestDto requestDto = new CatagoryMoveRequestDto();
		CatagoryMoveResponseDto responseDto = new CatagoryMoveResponseDto();

		homeBusiness.requestCatagoryMove(requestDto, responseDto);
		return responseDto;

	}

}
