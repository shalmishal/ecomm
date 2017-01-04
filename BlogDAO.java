package com.niit.DAO;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.niit.Model.Blog;
@Repository
public interface BlogDAO {
public boolean save(Blog blog);
	
	public boolean update(Blog blog);
		
		public boolean delete(String id);
		
		
		public List<Blog> list();
				
		
		
		public Blog get(String id);
}
