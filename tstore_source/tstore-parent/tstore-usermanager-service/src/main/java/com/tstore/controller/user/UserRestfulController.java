package com.tstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tstore.mapper.UserMapper;
import com.tstore.pojo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * UserRestfulController<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月27日-下午7:52:58 <br/>
 * 
 * @version 1.0.0<br/>
 *
 */
@RestController
@Api(description = "用户管理")
public class UserRestfulController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/user/getuser/{id}")
	@ApiOperation(value = "查询当前用户信息 ", notes = "查询当前用户信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		User user = userMapper.getUserById(id);
		if (null == user) {
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// return ResponseEntity.ok(user);
		// return ResponseEntity.status(200).body(user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping("/user/save")
	@ApiOperation(value = "保存用户", notes = "保存用户")
	public ResponseEntity<User> getUser(User user) {
		try {
			int count = userMapper.saveUser(user);
			if (count > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body(user);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
    
	@PutMapping("/user/update")
	@ApiOperation(value = "修改用户", notes = "修改用户")
	public ResponseEntity<User> updateUser(User user) {
		try {
			int count = userMapper.updateUser(user);
			if (count > 0) {
				return ResponseEntity.ok(user);
				// return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/user/delete/{id}")
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") Long id) {
		try {
			int count = userMapper.deleteUserById(id);
			if (count > 0) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(1);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
}
