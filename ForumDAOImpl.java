package com.niit.DAOImpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.ForumDAO;
import com.niit.Model.Forum;


@EnableTransactionManagement
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ForumDAOImpl(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory;
	}
	
@Transactional
	public boolean save(Forum forum)
	{
	try {
		// Session session = sessionFactory.getCurrentSession();
	
		sessionFactory.getCurrentSession().save(forum);
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}


@Transactional
public boolean update(Forum forum)
{
try {

	sessionFactory.getCurrentSession().update(forum);
	return true;
}
catch(Exception e)
{
	e.printStackTrace();
	return false;
}
}



@Transactional
	public boolean delete(String id)
	{
	try {
		
		
		Forum ForumToDelete = new Forum();
		ForumToDelete.setId(id);
			sessionFactory.getCurrentSession().delete(ForumToDelete);
		
		return true;
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
		return false;
	}
	}

@Transactional
public Forum get(String id)
{
	String hql = "from Forum where id= "+" '" +id+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Forum> list = query.list();
	if(list == null || list.isEmpty())
	{
		return null;
	}
	else
	{
		return list.get(0);
	}
}



@SuppressWarnings("unchecked")
@Transactional
public List<Forum> list()
{
	String hql = "from Forum";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	return query.list();
}


}

