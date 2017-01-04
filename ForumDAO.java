package com.niit.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.Model.Forum;



@Repository
public interface ForumDAO {
	public boolean update(Forum forum);
	public boolean save(Forum forum);
	public boolean delete(String id);
	
	
	public List<Forum> list();
			
	
	
	public Forum get(String id);

}
