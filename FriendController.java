package com.niit.controller;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendDAO;
import com.niit.Model.Friend;
import com.niit.Model.UserDetails;

@RestController
public class FriendController {
	
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	Friend friend;
	
	

	private static final Logger log=LoggerFactory.getLogger(Friend.class);

	@RequestMapping(value="/myFriends", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> listAllFriend(HttpSession session){
		System.out.println("calling method listAllFriends");
		log.debug("-->Calling method listAllFriends");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		List<Friend> myfriends = friendDAO.getmyfriends(loggedInUserid);
		return new ResponseEntity<List<Friend>> (myfriends,HttpStatus.OK);
				
	}
	@RequestMapping(value="/addfriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendid")String friendid,HttpSession session,UserDetails userDetails)
	{
		log.debug("-->Calling method send friend request");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserid);
		friend.setFriendid(friendid);
		friend.setStatus("N");
		friendDAO.setOnLine(userDetails.getUserid());
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/unfriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> unfriend(@PathVariable("friendid")String friendid,HttpSession session)
	{
		log.debug("-->Calling method send friend request");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");	
		friend.setUserid(loggedInUser.getUserid());
		friend.setFriendid(friendid);
		friend.setStatus("U");
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectFriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("friendid")String friendid,HttpSession session)
	{
		log.debug("-->Calling method send friend request");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");	
		friend.setUserid(loggedInUser.getUserid());
		friend.setFriendid(friendid);
		friend.setStatus("R");
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getmyfriendRequest", method=RequestMethod.GET)
	public ResponseEntity<Friend> getFriendRequest(HttpSession session){
		log.debug("-->Calling method listAllFriends");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");

		friendDAO.getNewFriendrequest(loggedInUserid);
		return new ResponseEntity<Friend> (friend,HttpStatus.OK);
	
}
	@RequestMapping(value="/acceptFriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("friendid")String friendid,HttpSession session,UserDetails userDetails)
	{
		log.debug("-->Calling method send friend request");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserId);
		friend.setFriendid(friendid);
		friend.setStatus("A");
		friendDAO.setOnLine(userDetails.getUserid());
		friendDAO.update(friend);
		//updateRequest(friendid,"A",session);
		return new ResponseEntity<Friend> (friend,HttpStatus.OK);
	}
	
	
	/*private void updateRequest(String friendid,String status,HttpSession session)
	{
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserId);
		friend.setFriendid(friendid);
		friend
		friendDAO.update(friend);
	}*/
	
	@RequestMapping(value="/myfriends/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getmyFriendsTemp(@PathVariable("id")String id){
		List<Friend> myfriends = friendDAO.getmyfriends(id);
		return new ResponseEntity<List<Friend>> (myfriends,HttpStatus.OK);
	}
}