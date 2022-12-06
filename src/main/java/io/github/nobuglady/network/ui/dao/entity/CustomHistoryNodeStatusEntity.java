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

import java.util.Date;

/**
 * 
 * @author NoBugLady
 *
 */
public class CustomHistoryNodeStatusEntity extends HistoryNodeStatusEntity{

	private String nodeName;
	
	private String flowId;
	private String nodeId;
	private String historyId;
	private int historyStatusSeq;
	private int nodeStatus;
	private int nodeStatusDetail;
	private String remark;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updateTime;
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public int getHistoryStatusSeq() {
		return historyStatusSeq;
	}
	public void setHistoryStatusSeq(int historyStatusSeq) {
		this.historyStatusSeq = historyStatusSeq;
	}
	public int getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(int nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	public int getNodeStatusDetail() {
		return nodeStatusDetail;
	}
	public void setNodeStatusDetail(int nodeStatusDetail) {
		this.nodeStatusDetail = nodeStatusDetail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
