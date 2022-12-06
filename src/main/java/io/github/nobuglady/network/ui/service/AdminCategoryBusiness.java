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
package io.github.nobuglady.network.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.nobuglady.network.ui.dao.CategoryDao;
import io.github.nobuglady.network.ui.dao.entity.CategoryEntity;
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

/**
 * Home business class
 * 
 * @author NoBugLady
 *
 */
@Service
@Transactional
public class AdminCategoryBusiness {

	@Autowired
	private CategoryDao catagoryDao;
	
	/**
	 * select all categories
	 * 
	 * @param requestDto  request dto
	 * @param responseDto response dto
	 * @param sessionId session id
	 */
	public void requestCatagorySelect(CatagorySelectRequestDto requestDto,
			CatagorySelectResponseDto responseDto, String sessionId) {

		List<CategoryEntity> catagoryEntityList = catagoryDao.selectAll();
		List<CatagoryRes> resList = new ArrayList<CatagoryRes>();

		Map<String, List<CatagoryRes>> childrenMap = new HashMap<String, List<CatagoryRes>>();
		for (CategoryEntity entity : catagoryEntityList) {
			CatagoryRes res = new CatagoryRes();
			res.id = entity.getId();
			res.text = entity.getCategoryName();
			res.type = entity.getCategoryType();

			resList.add(res);

			List<CatagoryRes> childList = childrenMap.get(entity.getParent());
			if (childList == null) {
				childList = new ArrayList<CatagoryRes>();
				childrenMap.put(entity.getParent(), childList);
			}
			childList.add(res);

			if (entity.getParent().equals("#")) {
				responseDto.catagoryRes = res;
			}
		}

		for (CatagoryRes res : resList) {
			res.children = childrenMap.get(res.id);
		}

	}

	/**
	 * delete categories
	 * 
	 * @param requestDto  request dto
	 * @param responseDto response dto
	 */
	public void requestCatagoryDelete(CatagoryDeleteRequestDto requestDto,
			CatagoryDeleteResponseDto responseDto) {

		if(requestDto.deleteIdList != null && requestDto.deleteIdList.size() > 0) {
			catagoryDao.delete(requestDto.deleteIdList);
		}
		
	}

	/**
	 * rename categories
	 * 
	 * @param requestDto  request dto
	 * @param responseDto response dto
	 */
	public void requestCatagoryRename(CatagoryRenameRequestDto requestDto,
			CatagoryRenameResponseDto responseDto) {

		catagoryDao.rename(requestDto.id,requestDto.text);
	}

	/**
	 * create categories
	 * 
	 * @param requestDto  request dto
	 * @param responseDto response dto
	 */
	public void requestCatagoryCreate(CatagoryCreateRequestDto requestDto,
			CatagoryCreateResponseDto responseDto) {

		CategoryEntity entity = new CategoryEntity();
		entity.setId(requestDto.id);
		entity.setCategoryName(requestDto.text);
		entity.setParent(requestDto.parent);
		entity.setCategoryType(requestDto.type);
		entity.setDisabled("false");
		
		catagoryDao.save(entity);
	}

	/**
	 * move categories
	 * 
	 * @param requestDto  request dto
	 * @param responseDto response dto
	 */
	public void requestCatagoryMove(CatagoryMoveRequestDto requestDto,
			CatagoryMoveResponseDto responseDto) {

		catagoryDao.move(requestDto.id,requestDto.parent);
	}

}
