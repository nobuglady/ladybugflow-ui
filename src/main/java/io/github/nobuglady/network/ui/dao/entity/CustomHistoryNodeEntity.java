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
package io.github.nobuglady.network.ui.dao.entity;

import java.util.List;

import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;

/**
 * 
 * @author NoBugLady
 *
 */
public class CustomHistoryNodeEntity extends HistoryNodeEntity{

	private HistoryFlowEntity historyFlowEntity;
	private List<HistoryEdgeEntity> historyEdgeEntityList;
	
	public HistoryFlowEntity getHistoryFlowEntity() {
		return historyFlowEntity;
	}
	public void setHistoryFlowEntity(HistoryFlowEntity historyFlowEntity) {
		this.historyFlowEntity = historyFlowEntity;
	}
	public List<HistoryEdgeEntity> getHistoryEdgeEntityList() {
		return historyEdgeEntityList;
	}
	public void setHistoryEdgeEntityList(List<HistoryEdgeEntity> historyEdgeEntityList) {
		this.historyEdgeEntityList = historyEdgeEntityList;
	}
	
}
