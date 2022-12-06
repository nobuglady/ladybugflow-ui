package io.github.nobuglady.network.ui.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nobuglady.network.fw.model.EdgeDto;
import io.github.nobuglady.network.fw.model.FlowDto;
import io.github.nobuglady.network.fw.model.NodeDto;
import io.github.nobuglady.network.ui.config.UserDetailDto;

public class AuthUtil {

	public static int getLoginUserId() {
		UserDetailDto userDetailDto = (UserDetailDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetailDto.getUserId();
	}
	
	public static boolean checkFlowAuth(String flowJson) {
		
		List<String> userRoles = new ArrayList<String>();
		List<String> userCategorys = new ArrayList<String>();
		
		UserDetailDto userDetailDto = (UserDetailDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(GrantedAuthority authDto:userDetailDto.getAuthorities()) {
			if("ADMIN".equals(authDto.getAuthority())) {
				return true;
			}
			
			if(authDto.getAuthority().startsWith("ROLE:")) {
				userRoles.add(authDto.getAuthority().substring("ROLE:".length()));
			}else if(authDto.getAuthority().startsWith("CATEGORY:")) {
				userCategorys.add(authDto.getAuthority().substring("CATEGORY:".length()));
			}
		}
		
		try {
			Reader reader = new StringReader(flowJson);
			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			boolean roleOkFlag = false;
			boolean categoryOkFlag = false;
			
			String[] checkRoles = flowDto.roles;
			String[] checkCategorys = flowDto.categorys;
			
			if(checkRoles == null || checkRoles.length == 0) {
				roleOkFlag = true;
			}else {

				for(String checkRole:checkRoles) {
					if(userRoles.contains(checkRole)) {
						roleOkFlag = true;
						break;
					}
				}
				
			}
			
			if(checkCategorys == null || checkCategorys.length == 0) {
				categoryOkFlag = true;
			}else {
				for(String checkCategory:checkCategorys) {
					if(userCategorys.contains(checkCategory)) {
						categoryOkFlag = true;
						break;
					}
				}
			}
			
			return roleOkFlag && categoryOkFlag;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkFlowNodeAuth(String flowJson, String nodeId) {
		
		List<String> userRoles = new ArrayList<String>();
		List<String> userCategorys = new ArrayList<String>();
		
		UserDetailDto userDetailDto = (UserDetailDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(GrantedAuthority authDto:userDetailDto.getAuthorities()) {
			if("ADMIN".equals(authDto.getAuthority())) {
				return true;
			}
			
			if(authDto.getAuthority().startsWith("ROLE:")) {
				userRoles.add(authDto.getAuthority().substring("ROLE:".length()));
			}else if(authDto.getAuthority().startsWith("CATEGORY:")) {
				userCategorys.add(authDto.getAuthority().substring("CATEGORY:".length()));
			}
		}
		
		try {
			Reader reader = new StringReader(flowJson);
			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			boolean roleOkFlag = false;
			boolean categoryOkFlag = false;
			
			String[] checkRoles = getFlowNodeRoles(flowDto, nodeId);
			String[] checkCategorys = getFlowNodeCategorys(flowDto, nodeId);
			
			if(checkRoles == null || checkRoles.length == 0) {
				roleOkFlag = true;
			}else {

				for(String checkRole:checkRoles) {
					if(userRoles.contains(checkRole)) {
						roleOkFlag = true;
						break;
					}
				}
				
			}
			
			if(checkCategorys == null || checkCategorys.length == 0) {
				categoryOkFlag = true;
			}else {
				for(String checkCategory:checkCategorys) {
					if(userCategorys.contains(checkCategory)) {
						categoryOkFlag = true;
						break;
					}
				}
			}
			
			return roleOkFlag && categoryOkFlag;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkFlowEdgeAuth(String flowJson, String edgeId) {
		
		List<String> userRoles = new ArrayList<String>();
		List<String> userCategorys = new ArrayList<String>();
		
		UserDetailDto userDetailDto = (UserDetailDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(GrantedAuthority authDto:userDetailDto.getAuthorities()) {
			if("ADMIN".equals(authDto.getAuthority())) {
				return true;
			}
			
			if(authDto.getAuthority().startsWith("ROLE:")) {
				userRoles.add(authDto.getAuthority().substring("ROLE:".length()));
			}else if(authDto.getAuthority().startsWith("CATEGORY:")) {
				userCategorys.add(authDto.getAuthority().substring("CATEGORY:".length()));
			}
		}
		
		try {
			Reader reader = new StringReader(flowJson);
			ObjectMapper mapper = new ObjectMapper();
			FlowDto flowDto = mapper.readValue(reader, FlowDto.class);
			
			boolean roleOkFlag = false;
			boolean categoryOkFlag = false;
			
			String[] checkRoles = getFlowEdgeRoles(flowDto, edgeId);
			String[] checkCategorys = getFlowEdgeCategorys(flowDto, edgeId);
			
			if(checkRoles == null || checkRoles.length == 0) {
				roleOkFlag = true;
			}else {

				for(String checkRole:checkRoles) {
					if(userRoles.contains(checkRole)) {
						roleOkFlag = true;
						break;
					}
				}
				
			}
			
			if(checkCategorys == null || checkCategorys.length == 0) {
				categoryOkFlag = true;
			}else {
				for(String checkCategory:checkCategorys) {
					if(userCategorys.contains(checkCategory)) {
						categoryOkFlag = true;
						break;
					}
				}
			}
			
			return roleOkFlag && categoryOkFlag;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	private static String[] getFlowNodeCategorys(FlowDto flowDto, String nodeId) {

		for(NodeDto nodeDto:flowDto.nodes) {
			if(nodeDto.id.equals(nodeId)) {
				return nodeDto.categorys;
			}
		}
		return new String[] {};
	}

	private static String[] getFlowNodeRoles(FlowDto flowDto, String nodeId) {

		for(NodeDto nodeDto:flowDto.nodes) {
			if(nodeDto.id.equals(nodeId)) {
				return nodeDto.roles;
			}
		}
		return new String[] {};
	}

	private static String[] getFlowEdgeCategorys(FlowDto flowDto, String edgeId) {

		for(EdgeDto edgeDto:flowDto.edges) {
			if(edgeDto.id.equals(edgeId)) {
				return edgeDto.categorys;
			}
		}
		return new String[] {};
	}

	private static String[] getFlowEdgeRoles(FlowDto flowDto, String edgeId) {

		for(EdgeDto edgeDto:flowDto.edges) {
			if(edgeDto.id.equals(edgeId)) {
				return edgeDto.roles;
			}
		}
		return new String[] {};
	}

}
