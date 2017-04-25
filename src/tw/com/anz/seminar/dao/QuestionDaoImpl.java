package tw.com.anz.seminar.dao;

import java.util.List;

import tw.com.anz.seminar.model.Question;

public class QuestionDaoImpl extends AbstractBaseDao implements QuestionDao {

	@Override
	protected Class<Question> getEntityClass() {
		return Question.class;
	}
	
	public List<Question> findByEId(long eId) {
		String sql = "from Question where eId = ?";
		Object[] param = { eId };
		return find(sql, param);
	}
	
	public void deleteByEId(long eId) {
		String sql = "Delete from Question where eId = ?";
		Object[] param = { eId };
		this.bulkUpdate(sql, param);
	}

}
