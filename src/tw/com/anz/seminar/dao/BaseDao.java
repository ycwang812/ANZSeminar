package tw.com.anz.seminar.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {
	
	public void evict(T entity);
	
	public void flush();

	public void save(T obj);
	
	public void update(T obj);
	
	public void saveOrUpdate(T obj);
	
	public void saveOrUpdateAll(Collection<T> objs);
	
	public int bulkUpdate(String queryString);
	
	public int bulkUpdate(String queryString, Object[] values);
	
	public void delete(T obj);

    public void deleteAll(List<T> objs);
	
	public T findById(Serializable id);
	
	public List<T> find(String queryString);
	
	public List<T> find(String queryString, Object[] values);
	
	public List<T> findByMaxResults(String queryString, Object[] values, int maxResults);
	
	public List<T> findByExample(String entityName,T entityClass);
	
	public List<T> getAll();
	
}
