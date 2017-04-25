package tw.com.anz.seminar.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractBaseDao<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Logger log = Logger.getLogger(getClass());
	
	protected abstract Class<T> getEntityClass();
	
	public void evict(T entity) {
		getHibernateTemplate().evict(entity);
	}
	
	public void flush() {
		getHibernateTemplate().flush();
	}
	
	public void save(T obj) {
		getHibernateTemplate().save(obj);
	}

	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	public void saveOrUpdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}
	
	public void saveOrUpdateAll(Collection<T> objs) {
		getHibernateTemplate().saveOrUpdateAll(objs);
	}
	
	public int bulkUpdate(String queryString) {
		return getHibernateTemplate().bulkUpdate(queryString);
	}

	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}
	
	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	public void deleteAll(List<T> objs) {
		getHibernateTemplate().deleteAll(objs);
	}
	
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(getEntityClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString) {
		List<T> lists = getHibernateTemplate().find(queryString);
		return (lists != null && lists.size() > 0) ? lists : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString, Object[] values) {
		List<T> lists = getHibernateTemplate().find(queryString, values);
		return (lists != null && lists.size() > 0) ? lists : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByMaxResults(String queryString, Object[] values, int maxResults) {
		HibernateTemplate ht = getHibernateTemplate();
		ht.setMaxResults(maxResults);
		List<T> lists = ht.find(queryString, values);
		return (lists != null && lists.size() > 0) ? lists : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(String entityName, T entityClass) {
		List<T> lists = getHibernateTemplate().findByExample(entityName, entityClass);
		return (lists != null && lists.size() > 0) ? lists : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> lists = getHibernateTemplate().loadAll(getEntityClass());
		return (lists != null && lists.size() > 0) ? lists : null;
	}
	
}
