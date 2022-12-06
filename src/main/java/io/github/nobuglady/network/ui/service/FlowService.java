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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.FlowRunner;
import io.github.nobuglady.network.fw.component.FlowComponentFactory;
import io.github.nobuglady.network.fw.component.IFlowAccessor;
import io.github.nobuglady.network.fw.constant.FlowStatus;
import io.github.nobuglady.network.fw.constant.NodeStatus;
import io.github.nobuglady.network.fw.constant.NodeStatusDetail;
import io.github.nobuglady.network.fw.model.EdgeDto;
import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.model.NodeDto;
import io.github.nobuglady.network.fw.persistance.entity.HistoryEdgeEntity;
import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;
import io.github.nobuglady.network.fw.util.FlowUtil;
import io.github.nobuglady.network.fw.util.model.Convert2JsonEdgeDto;
import io.github.nobuglady.network.fw.util.model.Convert2JsonFlowDto;
import io.github.nobuglady.network.fw.util.model.Convert2JsonNodeDto;
import io.github.nobuglady.network.ui.controller.dto.FlowEntityVo;
import io.github.nobuglady.network.ui.dao.FlowAccessorUI;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryFlowEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomHistoryNodeStatusEntity;
import io.github.nobuglady.network.ui.dao.entity.FlowInfoEntity;

/**
 * 
 * @author NoBugLady
 *
 */
@Service
public class FlowService {

	private IFlowAccessor flowAccessor = FlowComponentFactory.getFlowAccessor();
	private FlowAccessorUI flowAccessorUI = new FlowAccessorUI();

	/**
	 * 
	 * @return
	 */
	public List<CustomHistoryFlowEntity> getAllFlowHistory() {
		return flowAccessorUI.selectAllHistory();
	}

	/**
	 * 
	 * @return
	 */
	public List<CustomHistoryFlowEntity> getFlowHistoryByIdUser(String flowId, int userId) {
		return flowAccessorUI.selectHistoryByIdUser(flowId, userId);
	}

	/**
	 * 
	 * @return
	 */
	public List<CustomHistoryNodeStatusEntity> selectByFlowHistoryId(String flowId, String historyId) {
		return flowAccessorUI.selectByFlowHistoryId(flowId, historyId);
	}

	/**
	 * 
	 * @return
	 */
	public List<FlowEntityVo> getAllFlow() {

		List<FlowEntityVo> flowEntityList = new ArrayList<>();

		Map<String, FlowEntityVo> flowMap = new HashMap<>();
		List<FlowInfoEntity> flowInfoList = flowAccessorUI.selectAllFlow();
		for(FlowInfoEntity entity: flowInfoList) {
			FlowEntityVo flowEntityVo = flowMap.get(entity.getFlowId());
			if (flowEntityVo == null) {
				flowEntityVo = new FlowEntityVo();
				flowEntityVo.setFlowId(entity.getFlowId());
				flowEntityVo.setFlowName(entity.getFlowName());
				flowMap.put(entity.getFlowId(), flowEntityVo);
				flowEntityList.add(flowEntityVo);
			}
		}
		
		List<CustomHistoryFlowEntity> historyList = flowAccessorUI.selectAllHistory();
		for (CustomHistoryFlowEntity historyEntity : historyList) {
			FlowEntityVo flowEntityVo = flowMap.get(historyEntity.getFlowId());
			if (flowEntityVo == null) {
				flowEntityVo = new FlowEntityVo();
				flowEntityVo.setFlowId(historyEntity.getFlowId());
				flowEntityVo.setFlowName(historyEntity.getFlowName());
				flowMap.put(historyEntity.getFlowId(), flowEntityVo);
				flowEntityList.add(flowEntityVo);
			}

			flowEntityVo.setHistoryCount(flowEntityVo.getHistoryCount() + 1);

			if (FlowStatus.PROCESSING == historyEntity.getFlowStatus()
					|| FlowStatus.READY == historyEntity.getFlowStatus()) {
				flowEntityVo.setProcessingCount(flowEntityVo.getProcessingCount() + 1);
			} else if (FlowStatus.COMPLETE == historyEntity.getFlowStatus()) {
				flowEntityVo.setCompleteCount(flowEntityVo.getCompleteCount() + 1);
			} else if (FlowStatus.ERROR == historyEntity.getFlowStatus()
					|| FlowStatus.CANCEL == historyEntity.getFlowStatus()) {
				flowEntityVo.setErrorCount(flowEntityVo.getErrorCount() + 1);
			}
		}

		return flowEntityList;
	}
	
	/**
	 * 
	 * @param flowId
	 * @return
	 */
	public FlowInfoEntity getFlowByKey(String flowId) {
		return flowAccessorUI.selectFlowByKey(flowId);
	}

	/**
	 * 
	 * @param flowId
	 * @param historyId
	 * @return
	 */
	public String getJsonHistory(String flowId, String historyId) {
		return FlowUtil.dumpJson(flowId, historyId);
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> getNodeHistoryListOpen(String flowId, String nodeId) {
		return flowAccessorUI.selectNodeHistoryListOpen(flowId, nodeId);
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> getNodeHistoryListAll(String flowId, String nodeId) {
		return flowAccessorUI.selectNodeHistoryListAll(flowId, nodeId);
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	public List<CustomHistoryNodeEntity> getNodeHistoryListAllByUser(String flowId, String nodeId, int userId) {
		return flowAccessorUI.getNodeHistoryListAllByUser(flowId, nodeId, userId);
	}

	/**
	 * 
	 * @param flowId
	 * @return
	 */
	public String getJsonFlow(String flowId) {
		try {
			return dumpJsonFlow(flowId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param flowId
	 * @return
	 */
	public String getJsonString(String flowId) {
		FlowInfoEntity flowInfoEntity = flowAccessorUI.selectFlowByKey(flowId);
		return flowInfoEntity.getFlowJson();
	}

	/**
	 * 
	 */
	public void clearComplete() {

		flowAccessorUI.removeAllComplete();
	}

	/**
	 * 
	 */
	public void clearError() {

		flowAccessorUI.removeAllError();
	}
	
	/**
	 * 
	 * @param json
	 */
	public void saveOrUpdateFlow(String json) {
		flowAccessorUI.saveOrUpdateFlowInfo(json);
	}

	/**
	 * 
	 * @param flowId
	 * @param username
	 */
	public void startFlow(String flowId, String username) {
		
		FlowInfoEntity flowInfoEntity = flowAccessorUI.selectFlowByKey(flowId);
		
		FlowRunner flowRunner = new FlowRunner();
		flowRunner.startFlowFromJson(null, flowInfoEntity.getFlowJson(), username);
		
	}

	/**
	 * 
	 * @param flowId
	 * @param nodeId
	 * @param edgeId
	 * @param historyId
	 */
	public void submitNodeToEdge(String flowId, String nodeId, String edgeId, String historyId) {
		HistoryEdgeEntity edge = flowAccessor.selectEdgeByKey(flowId, edgeId, historyId);
		
		FlowComponentFactory.getCompleteQueueSender().putCompleteNode(
				flowId, 
				historyId, 
				nodeId,
				NodeStatusDetail.COMPLETE_SUCCESS, 
				edge.getEdgeCondition());
	}
	

	/**
	 * dumpJsonFlow
	 * 
	 * @param flowId flowId
	 * @return json
	 * @throws IOException 
	 * @throws DatabindException 
	 * @throws StreamReadException 
	 */
	private String dumpJsonFlow(String flowId) throws Exception {
		
		Convert2JsonFlowDto nodeFlowDto = new Convert2JsonFlowDto();
		
		/*
		 * flow
		 */
		FlowInfoEntity flowInfoEntity = flowAccessorUI.selectFlowByKey(flowId);
		Reader reader = new StringReader(flowInfoEntity.getFlowJson());
		ObjectMapper mapper = new ObjectMapper();
		FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
		
		/*
		 * node
		 */
		List<NodeDto> nodeEntityList = flowDto.nodes;

		List<HistoryNodeEntity> nodeEntityListAll = flowAccessor.selectNodeByFlowId(flowId);
		Map<String, Integer> nodeCountMap = new HashMap<>();

		for (HistoryNodeEntity nodeHistoryEntity : nodeEntityListAll) {
			if (NodeStatus.GO == nodeHistoryEntity.getNodeStatus()) {

				Integer count = nodeCountMap.get(nodeHistoryEntity.getNodeId());
				if (count == null) {
					count = 0;
				}

				count = count + 1;

				nodeCountMap.put(nodeHistoryEntity.getNodeId(), count);
			}
		}

		for (NodeDto item : nodeEntityList) {

			Convert2JsonNodeDto nodeNodeDto = new Convert2JsonNodeDto();
			nodeNodeDto.id = item.id;
			nodeNodeDto.label = item.label;
			nodeNodeDto.status = NodeStatus.INIT;
			nodeNodeDto.status_detail = NodeStatusDetail.NONE;

			int nodeCount = 0;
			if(nodeCountMap.get(item.id) != null) {
				nodeCount = nodeCountMap.get(item.id);
			}
			
			nodeNodeDto.label = nodeNodeDto.label + "(" + nodeCount + ")";

			nodeFlowDto.nodes.add(nodeNodeDto);
		}

		/*
		 * edge
		 */
		List<EdgeDto> edgeEntityList = flowDto.edges;
		for (EdgeDto item : edgeEntityList) {

			Convert2JsonEdgeDto nodeEdgeDto = new Convert2JsonEdgeDto();
			nodeEdgeDto.id = item.id;
			nodeEdgeDto.from = item.from;
			nodeEdgeDto.to = item.to;

			nodeFlowDto.edges.add(nodeEdgeDto);
		}

		/*
		 * convert flow to json
		 */
		return nodeFlowDto.toJson();

	}

}
