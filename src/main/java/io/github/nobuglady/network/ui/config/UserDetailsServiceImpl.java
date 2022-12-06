package io.github.nobuglady.network.ui.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.nobuglady.network.ui.dao.UserCategoryDao;
import io.github.nobuglady.network.ui.dao.UserDao;
import io.github.nobuglady.network.ui.dao.UserRoleDao;
import io.github.nobuglady.network.ui.dao.entity.CustomUserCategoryEntity;
import io.github.nobuglady.network.ui.dao.entity.CustomUserRoleEntity;
import io.github.nobuglady.network.ui.dao.entity.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private UserCategoryDao userCategoryDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userDao.getByEmail(username);
		
		if(userEntity != null) {
			UserDetailDto userDto = new UserDetailDto(userEntity.getUserId(), userEntity.getEmail(), userEntity.getPasswd());
			Integer adminFlag = userEntity.getAdminFlag();

			if(adminFlag != null && adminFlag == 1) {
				GrantedAuthorityDto authDto = new GrantedAuthorityDto("ADMIN");
				userDto.getAuthorities().add(authDto);
			}
			
			List<CustomUserRoleEntity> roleList = userRoleDao.selectByUserId(userEntity.getUserId());
			List<CustomUserCategoryEntity> categoryList = userCategoryDao.selectByUserId(userEntity.getUserId());
			
			for(CustomUserRoleEntity roleEntity:roleList) {
				GrantedAuthorityDto authDto = new GrantedAuthorityDto("ROLE:"+roleEntity.getRoleId());
				userDto.getAuthorities().add(authDto);
			}
			
			for(CustomUserCategoryEntity categoryEntity:categoryList) {
				GrantedAuthorityDto authDto = new GrantedAuthorityDto("CATEGORY:"+categoryEntity.getCategoryId());
				userDto.getAuthorities().add(authDto);
			}
			
			return userDto;
		}
		
		throw new UsernameNotFoundException("username or password is not correct.");
	}

}
