package tw.com.anz.seminar.model;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {

	private static final long serialVersionUID = 6675708849923787520L;

	private long id;
	private long eId;
	private String userId;
	private Date openTime;
	private long watchTime;
	
	public Report() {
	}

	public Report(long id, long eId, String userId, Date openTime, long watchTime) {
		this.id = id;
		this.eId = eId;
		this.userId = userId;
		this.openTime = openTime;
		this.watchTime = watchTime;
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

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public long getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(long watchTime) {
		this.watchTime = watchTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eId ^ (eId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((openTime == null) ? 0 : openTime.hashCode());
		result = prime * result + (int) (watchTime ^ (watchTime >>> 32));
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
		Report other = (Report) obj;
		if (eId != other.eId)
			return false;
		if (id != other.id)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (openTime == null) {
			if (other.openTime != null)
				return false;
		} else if (!openTime.equals(other.openTime))
			return false;
		if (watchTime != other.watchTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", eId=" + eId + ", userId=" + userId
				+ ", openTime=" + openTime + ", watchTime=" + watchTime + "]";
	}
	
}
