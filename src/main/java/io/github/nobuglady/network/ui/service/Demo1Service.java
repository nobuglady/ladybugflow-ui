/*
 * Copyright (c) 2021-present, NoBugLady Contributors.
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nobuglady.network.ui.flow.Demo1Flow;

/**
 * 
 * @author NoBugLady
 *
 */
@Service
public class Demo1Service {

	@Autowired
	private Demo1Flow demo1Flow;

	/**
	 * Start flow
	 */
	public void bookingHotel(String username) {
		demo1Flow.startFlow(true, username);
	}
}
