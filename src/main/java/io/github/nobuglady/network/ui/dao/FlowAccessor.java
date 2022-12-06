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
package io.github.nobuglady.network.ui.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.github.nobuglady.network.fw.component.IFlowAccessor;
import io.github.nobuglady.network.fw.constant.NodeStatusDetail;
import io.github.nobuglady.network.fw.persistance.entity.FlowEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;
import io.github.nobuglady.network.ui.SpringContextBridge;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;
import io.github.nobuglady.network.ui.dao.entity.HistoryNodeStatusEntity;

/**
 * 
 * @author NoBugLady
 *
 */
public class FlowAccessor implements IFlowAccessor {

	/** key: flowId, historyId */
	public static Map<String, FlowEntity> flowMap = new HashMap<>();

	private HistoryFlowDao historyFlowDao;
	private HistoryNodeDao historyNodeDao;
	private HistoryEdgeDao historyEdgeDao;
	private HistoryNodeStatusDao historyNodeStatusDao;
	private FlowInfoDao flowInfoDao;

	public FlowAccessor() {
		historyFlowDao = SpringContextBridge.getInstance().getHistoryFlowDao();
		historyNodeDao = SpringContextBridge.getInstance().getHistoryNodeDao();
		historyEdgeDao = SpringContextBridge.getInstance().getHistoryEdgeDao();
		historyNodeStatusDao = SpringContextBridge.getInstance().getHistoryNodeStatusDao();
		flowInfoDao = SpringContextBridge.getInstance().getFlowInfoDao();
	}

	/**
	 * createHistoryId
	 * 
	 * @return HistoryId
	 */
	public String createHistoryId() {
		return UUID.randomUUID().toString();

	}

	/**
	 * selectFlowByKey
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryFlowEntity
	 */
	public HistoryFlowEntity selectFlowByKey(String flowId, String historyId) {
		return historyFlowDao.selectByKey(flowId, historyId);
	}

	/**
	 * selectFlowHistoryLast
	 * 
	 * @param flowId flowId
	 * @return last flowHistoryId
	 */
	public String selectFlowHistoryLast(String flowId) {
		return historyFlowDao.selectFlowHistoryLast(flowId);
	}

	/**
	 * selectNodeByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeByFlowHistoryId(String flowId, String historyId) {
		return historyNodeDao.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * selectNodeByFlowId
	 * 
	 * @param flowId flowId
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeByFlowId(String flowId) {
		return historyNodeDao.selectByFlowId(flowId);
	}

	/**
	 * selectNodeByKey
	 * 
	 * @param flowId    flowId
	 * @param nodeId    nodeId
	 * @param historyId historyId
	 * @return HistoryNodeEntity
	 */
	public HistoryNodeEntity selectNodeByKey(String flowId, String nodeId, String historyId) {
		return historyNodeDao.selectByKey(flowId, nodeId, historyId);
	}

	/**
	 * updateNodeStatusByNodeId
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param nodeId     nodeId
	 * @param nodeStatus nodeStatus
	 */
	public void updateNodeStatusByNodeId(String flowId, String historyId, String nodeId, int nodeStatus) {
		historyNodeDao.updateStatusByNodeId(flowId, historyId, nodeId, nodeStatus);
		HistoryNodeStatusEntity entity = new HistoryNodeStatusEntity();
		entity.setFlowId(flowId);
		entity.setNodeId(nodeId);
		entity.setHistoryId(historyId);
		entity.setNodeStatus(nodeStatus);
		entity.setNodeStatusDetail(NodeStatusDetail.NONE);
		historyNodeStatusDao.save(entity);
	}

	/**
	 * selectNodeListByStatus
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param nodeStatus nodeStatus
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeListByStatus(String flowId, String historyId, int nodeStatus) {
		return historyNodeDao.selectNodeListByStatus(flowId, historyId, nodeStatus);
	}

	/**
	 * selectNodeListByStatusDetail
	 * 
	 * @param flowId           flowId
	 * @param historyId        historyId
	 * @param nodeStatus       nodeStatus
	 * @param nodeStatusDetail nodeStatusDetail
	 * @return HistoryNodeEntity
	 */
	public List<HistoryNodeEntity> selectNodeListByStatusDetail(String flowId, String historyId, int nodeStatus,
			int nodeStatusDetail) {
		return historyNodeDao.selectNodeListByStatusDetail(flowId, historyId, nodeStatus, nodeStatusDetail);
	}

	/**
	 * selectEdgeByFlowHistoryId
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @return HistoryEdgeEntity
	 */
	public List<HistoryEdgeEntity> selectEdgeByFlowHistoryId(String flowId, String historyId) {
		return historyEdgeDao.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * selectEdgeByKey
	 * 
	 * @param flowId flowId
	 * @param edgeId edgeId
	 * @param historyId historyId
	 * @return HistoryEdgeEntity
	 */
	public HistoryEdgeEntity selectEdgeByKey(String flowId, String edgeId, String historyId) {
		return historyEdgeDao.selectByKey(flowId, edgeId, historyId);
	}

	/**
	 * updateFlowStatusAndFinishTime
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param flowStatus flowStatus
	 */
	public void updateFlowStatusAndFinishTime(String flowId, String historyId, int flowStatus) {
		historyFlowDao.updateStatusAndFinishTime(flowId, historyId, flowStatus);
	}

	/**
	 * updateFlowStatusAndStartTime
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param flowStatus flowStatus
	 */
	public void updateFlowStatusAndStartTime(String flowId, String historyId, int flowStatus) {
		historyFlowDao.updateStatusAndStartTime(flowId, historyId, flowStatus);
	}

	/**
	 * updateNodeStatusDetailByNodeId
	 * 
	 * @param flowId           flowId
	 * @param historyId        historyId
	 * @param nodeId           nodeId
	 * @param nodeStatus       nodeStatus
	 * @param nodeStatusDetail nodeStatusDetail
	 */
	public void updateNodeStatusDetailByNodeId(String flowId, String historyId, String nodeId, int nodeStatus,
			int nodeStatusDetail) {
		historyNodeDao.updateStatusDetailByNodeId(flowId, historyId, nodeId, nodeStatus, nodeStatusDetail);
		HistoryNodeStatusEntity entity = new HistoryNodeStatusEntity();
		entity.setFlowId(flowId);
		entity.setNodeId(nodeId);
		entity.setHistoryId(historyId);
		entity.setNodeStatus(nodeStatus);
		entity.setNodeStatusDetail(nodeStatusDetail);
		historyNodeStatusDao.save(entity);
	}

	/**
	 * updateNodeReturnValueByNodeId
	 * 
	 * @param flowId      flowId
	 * @param historyId   historyId
	 * @param nodeId      nodeId
	 * @param returnValue returnValue
	 */
	public void updateNodeReturnValueByNodeId(String flowId, String historyId, String nodeId, String returnValue) {
		historyNodeDao.updateReturnValueByNodeId(flowId, historyId, nodeId, returnValue);
	}

	/**
	 * updateEdgeStatusByKey
	 * 
	 * @param flowId     flowId
	 * @param historyId  historyId
	 * @param edgeId     edgeId
	 * @param edgeStatus edgeStatus
	 */
	public void updateEdgeStatusByKey(String flowId, String historyId, String edgeId, int edgeStatus) {
		historyEdgeDao.updateStatusByKey(flowId, historyId, edgeId, edgeStatus);
	}

	/**
	 * saveFlow
	 * 
	 * @param flowEntity flowEntity
	 * @param json       json
	 */
	public void saveFlow(FlowEntity flowEntity, String json) {

		json = json.trim();
		
		historyFlowDao.save(flowEntity.flowEntity);

		for (HistoryEdgeEntity historyEdgeEntity : flowEntity.edgeEntityList) {
			historyEdgeDao.save(historyEdgeEntity);
		}

		for (HistoryNodeEntity historyNodeEntity : flowEntity.nodeEntityList) {
			historyNodeDao.save(historyNodeEntity);
		}

		FlowInfoEntity flowInfoEntity = flowInfoDao.selectByKey(flowEntity.flowEntity.getFlowId());

		if (flowInfoEntity != null) {
			if (!flowInfoEntity.getFlowJson().equals(json)) {
				flowInfoEntity.setFlowName(flowEntity.flowEntity.getFlowName());
				flowInfoEntity.setFlowDesc(flowEntity.flowEntity.getFlowDesc());
				flowInfoEntity.setFlowJson(json);

				flowInfoDao.update(flowInfoEntity);
			}
		} else {
			flowInfoEntity = new FlowInfoEntity();
			flowInfoEntity.setFlowId(flowEntity.flowEntity.getFlowId());
			flowInfoEntity.setFlowName(flowEntity.flowEntity.getFlowName());
			flowInfoEntity.setFlowDesc(flowEntity.flowEntity.getFlowDesc());
			flowInfoEntity.setFlowJson(json);

			flowInfoDao.save(flowInfoEntity);
		}

	}

	/**
	 * removeFlow
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 */
	public void removeFlow(String flowId, String historyId) {

		historyFlowDao.deleteAllByKey(flowId, historyId);
		historyNodeDao.deleteByFlowHistoryId(flowId, historyId);
		historyEdgeDao.deleteByFlowHistoryId(flowId, historyId);
	}

	/**
	 * updateNodeStartTime
	 * 
	 * @param flowId flowId
	 * @param nodeId nodeId
	 * @param historyId historyId
	 */
	public void updateNodeStartTime(String flowId, String nodeId, String historyId) {
		historyNodeDao.updateNodeStartTime(flowId, nodeId, historyId);
	}

}
