package tw.com.anz.seminar.model;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {

	private static final long serialVersionUID = 537530308259614819L;
	
	private long id;
	private long eId;
	private String userId;
	private Date time;
	private String question;
	
	public Question() {
	}

	public Question(long id, long eId, String userId, Date time, String question) {
		this.id = id;
		this.eId = eId;
		this.userId = userId;
		this.time = time;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eId ^ (eId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (eId != other.eId)
			return false;
		if (id != other.id)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", eId=" + eId + ", userId=" + userId
				+ ", time=" + time + ", question=" + question + "]";
	}

}
