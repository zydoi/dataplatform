package com.bimoku.dataplatform.resource;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.service.UserService;

@Path(value = "/")
public class UserResource {

	private static final Logger logger = Logger.getLogger(UserResource.class);
	
	@Autowired
	private UserService service;
	
	@Path(value = "/user/{ID}")
	public UserDTO getUser(@PathParam("ID") int id) {
		logger.info("Get User with ID: " + id);
		return null;
	}
	
	@Path(value = "/user/{ID}/followers")
	public List<UserDTO> getFollowers() {
		return null;
	}

	@Path(value = "/user/{ID}/followings")
	public List<UserDTO> getFollowings() {
		return null;
	}
}
