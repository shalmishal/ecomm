package com.niit.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendDAO;
import com.niit.DAO.UserDAO;
import com.niit.Model.UserDetails;

@RestController
public class UserController {
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired(required=true)
	UserDAO userDAO;
	@Autowired(required=true)
	UserDetails userDetails;
	@Autowired
	FriendDAO friendDAO;
	
	//for list
		@RequestMapping(value="/users", method=RequestMethod.GET)
		public ResponseEntity<List<UserDetails>> listAllUsers(){
			log.debug("-->Calling method listAllUsers");
			List<UserDetails> user=userDAO.list();
			if(user.isEmpty()){
				return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<UserDetails>>(user,HttpStatus.OK);
			
		}
	//to create users
	@RequestMapping(value="/createusers/", method=RequestMethod.POST)
	public ResponseEntity<UserDetails> createusers(@RequestBody UserDetails userDetails){
		log.debug("-->Calling method createUsers");
		if(userDAO.get(userDetails.getUserid())==null){
			userDAO.save(userDetails);
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
		}
		log.debug("-->User already exist"+userDetails.getUserid());
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
		}

	//to get user by user id
	@RequestMapping(value="/user/{userid}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails> getuser(@PathVariable("userid")String id)
	{
	log.debug("-->calling get method");
	UserDetails userDetails=userDAO.get(id);
	if(userDetails==null)
	{
		log.debug("-->User does not exist");
		userDetails = new UserDetails();
		userDetails.setErrorcode("404");
		userDetails.setErrormessage("User not found");
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.NOT_FOUND);
	}
	log.debug("-->User exist");
	return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
	
	//update user by user id
	@RequestMapping(value="/user/{userid}",method=RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateuser(@PathVariable("userid")String id)
	{
	log.debug("-->calling update method");
	if(userDAO.get(id)==null)
	{
		log.debug("-->User does not exist");
		userDetails = new UserDetails();
		userDetails.setErrorcode("404");
		userDetails.setErrormessage("User not found");
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.NOT_FOUND);
	}
	userDAO.update(userDetails);
	log.debug("-->User updated successfully");
	return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	
}
	//delete user
	@RequestMapping(value="/user/{userid}",method=RequestMethod.DELETE)
	public ResponseEntity<UserDetails> deleteuser(@PathVariable("userid")String id)
	{
		log.debug("-->calling delete method");
		UserDetails userDetails=userDAO.get(id);
		if(userDetails==null)
		{
			log.debug("-->User does not exist");
			userDetails = new UserDetails();
			userDetails.setErrorcode("404");
			userDetails.setErrormessage("Blog not found");
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.NOT_FOUND);
		}
		userDAO.delete(id);
		log.debug("-->User deleted successfully");
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
		}
	
	
	//authentication
	@RequestMapping(value="/user/authenticate",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> authenticateuser(@RequestBody UserDetails userdetails,HttpSession session)
	{
		log.debug("-->calling authenticate method");
		userDetails=userDAO.authenticate(userDetails.getUserid(), userDetails.getPassword());
		if(userDetails==null)
		{
			log.debug("-->User does not exist");
			userDetails = new UserDetails();
			userDetails.setErrorcode("404");
			userDetails.setErrormessage("user does not exist");
	}
		else
		{
			userDetails.setErrorcode("200");
			log.debug("-->User exist with above credentials");
			session.setAttribute("loggegInUser",userDetails);
			session.setAttribute("loggedInUserId", userDetails.getUserid());
			friendDAO.setOnLine(userDetails.getUserid());
			userDAO.setOnLine(userDetails.getUserid());
		}
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout/",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> logout(HttpSession session)
	{
		userDetails= userDAO.authenticate(userDetails.getUserid(), userDetails.getPassword());
		friendDAO.setOffLine(userDetails.getUserid());
		userDAO.setOffLine(userDetails.getUserid());
		session.invalidate();
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
}