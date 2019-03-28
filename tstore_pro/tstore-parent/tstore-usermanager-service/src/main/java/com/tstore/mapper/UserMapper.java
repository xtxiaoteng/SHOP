/**
 * 电子商城系统平台<br/>
 * com.tstore.mapper<br/>
 * UserMapper.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月26日-上午4:17:24 <br/>
 * 2019个人版权所有<br/>
 */
package com.tstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tstore.pojo.User;
import com.tstore.vo.UserVo;

/**
 * 
 * UserMapper<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月26日-上午4:17:24 <br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
public interface UserMapper {
   
	//添加
	public int saveUser(User user);

	// 修改
	public int updateUser(User user);

	// 删除
	public int deleteUserById(@Param("id") Long id);

	// 查询单个
	public User getUserById(@Param("id") Long id);

	// 查询所有
	public List<User> queryUserAll(UserVo userVo);
	
	


}
