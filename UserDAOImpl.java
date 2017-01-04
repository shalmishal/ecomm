package com.niit.DAOImpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.UserDAO;
import com.niit.Model.UserDetails;


@EnableTransactionManagement
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory;
	}
	
@Transactional
	public boolean save(UserDetails userDetails)
	{
	try {
		// Session session = sessionFactory.getCurrentSession();
	
		sessionFactory.getCurrentSession().save(userDetails);
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}


@Transactional
public boolean update(UserDetails userDetails)
{
try {

	sessionFactory.getCurrentSession().update(userDetails);
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
		
		
			UserDetails CategoryToDelete = new UserDetails();
			CategoryToDelete.setUserid(id);
			sessionFactory.getCurrentSession().delete(CategoryToDelete);
		
		return true;
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
		return false;
	}
	}

@Transactional
public UserDetails get(String userid)
{
	String hql = "from UserDetails where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<UserDetails> list = query.list();
	if(list == null || list.isEmpty())
	{
		return null;
	}
	else
	{
		return list.get(0);
	}
}

@Transactional
public UserDetails authenticate(String userid, String password) {
	System.out.println("dao impl");
	String hql = "from UserDetails where userid= '" + userid + "' and " + " password ='" + password + "'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);

	@SuppressWarnings("unchecked")
	List<UserDetails> list = (List<UserDetails>) query.list();

	if (list != null && !list.isEmpty()) {
		return list.get(0);
	}

	return null ;
}

@SuppressWarnings("unchecked")
@Transactional
public List<UserDetails> list()
{
	String hql = "from UserDetails";
	Query query =sessionFactory.openSession().createQuery(hql);
	return query.list();
}

@Transactional
public void setOnLine(String userid)
{
	String hql ="update UserDetails SET is_online='Y' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
}

@Transactional
public void setOffLine(String userid)
{
	String hql ="update UserDetails SET is_online='N' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
	
}
}








