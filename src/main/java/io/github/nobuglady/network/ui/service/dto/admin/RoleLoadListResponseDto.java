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
package io.github.nobuglady.network.ui.service.dto.admin;

import java.util.ArrayList;
import java.util.List;

import io.github.nobuglady.network.ui.dao.entity.RoleEntity;

/**
 * Role load list response dto class
 * 
 * @author NoBugLady
 *
 */
public class RoleLoadListResponseDto {

	public List<RoleEntity> roleList = new ArrayList<>();

}
