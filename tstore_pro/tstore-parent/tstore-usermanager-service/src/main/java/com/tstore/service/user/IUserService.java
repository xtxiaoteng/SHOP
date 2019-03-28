package com.tstore.service.user;

import com.tstore.vo.ServerResponse;
import com.tstore.vo.UserVo;

public interface IUserService {

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：saveUser<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月26日-上午4:25:36 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param userVo
	 * @return ServerResponse<br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public ServerResponse saveUser(UserVo userVo);

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：updateUser<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月26日-上午4:25:46 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param userVo
	 * @return ServerResponse<br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public ServerResponse updateUser(UserVo userVo);

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：deleteUserById<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月26日-上午4:25:54 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param id
	 * @return ServerResponse<br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public ServerResponse deleteUserById(Long id);

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：getUserById<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月26日-上午4:26:20 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param id
	 * @return ServerResponse<br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public ServerResponse getUserById(Long id);

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：queryUserAll<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月26日-上午4:26:24 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param userVo
	 * @return ServerResponse<br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public ServerResponse queryUserAll(UserVo userVo);
}
