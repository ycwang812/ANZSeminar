package tw.com.anz.seminar.dao;

import java.util.List;

import tw.com.anz.seminar.model.Question;

public interface QuestionDao extends BaseDao {
	
	public List<Question> findByEId(long eId);
	
	public void deleteByEId(long eId);

}
