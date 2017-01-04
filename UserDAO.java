package com.niit.DAO;
import java.util.List;
import com.niit.Model.UserDetails;
    public interface UserDAO {
	public boolean save(UserDetails userDetails);
	public boolean update(UserDetails userDetails);
    public boolean delete(String id);
    public void setOnLine(String userid);
    public void setOffLine(String userid);
    public List<UserDetails> list();
	public UserDetails authenticate(String userid, String password);
    public UserDetails get(String userid);
		

	
			

}
