
package com.tstore.service.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tstore.mapper.UserMapper;
import com.tstore.vo.UserVo;
import com.tstore.pojo.User;
import com.tstore.vo.ServerResponse;


/**
 * 
 * 
 * UserServiceImpl<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月26日-上午4:27:16 <br/>
 * @version 1.0.0<br/>
 *
 */
@Service
public class UserServiceImpl implements IUserService  {

	@Autowired
	private UserMapper userMapper;
	
	public ServerResponse queryUserAll(UserVo userVo){
		PageHelper.startPage(userVo.getPageNo(), userVo.getPageSize());
		List<User> users = userMapper.queryUserAll(userVo);
		PageInfo<User> pageInfo = new PageInfo<>(users);
		return ServerResponse.createBySuccess(pageInfo);
	}
	
	@Override
	public ServerResponse saveUser(UserVo userVo) {
		User user = new User();
		BeanUtils.copyProperties(userVo, user);
		int count = userMapper.saveUser(user);
		return count>0?ServerResponse.createBySuccess(user):ServerResponse.createByError();
	}

	
	@Override
	public ServerResponse updateUser(UserVo userVo) {
		User user = new User();
		BeanUtils.copyProperties(userVo, user);
		int count = userMapper.updateUser(user);
		return count>0?ServerResponse.createBySuccess(user):ServerResponse.createByError();
	}

	
	@Override
	public ServerResponse deleteUserById(Long id) {
		int count = userMapper.deleteUserById(id);
		return count>0?ServerResponse.createBySuccess():ServerResponse.createByError();
	}

	
	@Override
	public ServerResponse getUserById(Long id) {
		User user = userMapper.getUserById(id);
		return user!=null?ServerResponse.createBySuccess(user):ServerResponse.createByError();
	}
	
	
}
