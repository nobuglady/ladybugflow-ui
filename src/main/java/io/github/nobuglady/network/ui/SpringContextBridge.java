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
package io.github.nobuglady.network.ui;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.github.nobuglady.network.ui.dao.HistoryEdgeDao;
import io.github.nobuglady.network.ui.dao.HistoryFlowDao;
import io.github.nobuglady.network.ui.dao.HistoryNodeDao;

/**
 * 
 * @author NoBugLady
 *
 */
@Component
public class SpringContextBridge implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Autowired
	private HistoryFlowDao historyFlowDao;
	@Autowired
	private HistoryNodeDao historyNodeDao;
	@Autowired
	private HistoryEdgeDao historyEdgeDao;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextBridge.applicationContext = applicationContext;
	}

	public static SpringContextBridge getInstance() {
		return applicationContext.getBean(SpringContextBridge.class);
	}

	public HistoryFlowDao getHistoryFlowDao() {
		return historyFlowDao;
	}

	public HistoryNodeDao getHistoryNodeDao() {
		return historyNodeDao;
	}

	public HistoryEdgeDao getHistoryEdgeDao() {
		return historyEdgeDao;
	}

}
