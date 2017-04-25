package tw.com.anz.seminar.model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

	private static final long serialVersionUID = -5855485897191144286L;

	private long id;
	private String eventId;
	private String eventUrl;
	private String summary;
	private String location;
	private Date startTime;
	private Date endTime;
	private String timeZone;
	private String introduction;
	private String speaker;
	private String legal;
	private String seminarKind;
	private String youtubeUrl;
	private String youtubeId;
	private String privacyStatus;
	private String question;
	private String url;
	private String uploadImage;
	private String uploadFile;
	private String duration;
	private String headline;
	private String top;
	private String login;
	private String username;
	private Date createTime;
	private Date updateTime;
	
	public Event() {
	}

	public Event(long id, String eventId, String eventUrl, String summary,
			String location, Date startTime, Date endTime, String timeZone,
			String introduction, String speaker, String legal,
			String seminarKind, String youtubeUrl, String youtubeId,
			String privacyStatus, String question, String url,
			String uploadImage, String uploadFile, String duration,
			String headline, String top, String login, String username,
			Date createTime, Date updateTime) {
		this.id = id;
		this.eventId = eventId;
		this.eventUrl = eventUrl;
		this.summary = summary;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeZone = timeZone;
		this.introduction = introduction;
		this.speaker = speaker;
		this.legal = legal;
		this.seminarKind = seminarKind;
		this.youtubeUrl = youtubeUrl;
		this.youtubeId = youtubeId;
		this.privacyStatus = privacyStatus;
		this.question = question;
		this.url = url;
		this.uploadImage = uploadImage;
		this.uploadFile = uploadFile;
		this.duration = duration;
		this.headline = headline;
		this.top = top;
		this.login = login;
		this.username = username;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getSeminarKind() {
		return seminarKind;
	}

	public void setSeminarKind(String seminarKind) {
		this.seminarKind = seminarKind;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public String getPrivacyStatus() {
		return privacyStatus;
	}

	public void setPrivacyStatus(String privacyStatus) {
		this.privacyStatus = privacyStatus;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result
				+ ((eventUrl == null) ? 0 : eventUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + ((legal == null) ? 0 : legal.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((headline == null) ? 0 : headline.hashCode());
		result = prime * result
				+ ((privacyStatus == null) ? 0 : privacyStatus.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((seminarKind == null) ? 0 : seminarKind.hashCode());
		result = prime * result + ((speaker == null) ? 0 : speaker.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result
				+ ((timeZone == null) ? 0 : timeZone.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((uploadFile == null) ? 0 : uploadFile.hashCode());
		result = prime * result
				+ ((uploadImage == null) ? 0 : uploadImage.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result
				+ ((youtubeId == null) ? 0 : youtubeId.hashCode());
		result = prime * result
				+ ((youtubeUrl == null) ? 0 : youtubeUrl.hashCode());
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
		Event other = (Event) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventUrl == null) {
			if (other.eventUrl != null)
				return false;
		} else if (!eventUrl.equals(other.eventUrl))
			return false;
		if (id != other.id)
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (legal == null) {
			if (other.legal != null)
				return false;
		} else if (!legal.equals(other.legal))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (headline == null) {
			if (other.headline != null)
				return false;
		} else if (!headline.equals(other.headline))
			return false;
		if (privacyStatus == null) {
			if (other.privacyStatus != null)
				return false;
		} else if (!privacyStatus.equals(other.privacyStatus))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (seminarKind == null) {
			if (other.seminarKind != null)
				return false;
		} else if (!seminarKind.equals(other.seminarKind))
			return false;
		if (speaker == null) {
			if (other.speaker != null)
				return false;
		} else if (!speaker.equals(other.speaker))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		if (top == null) {
			if (other.top != null)
				return false;
		} else if (!top.equals(other.top))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (uploadFile == null) {
			if (other.uploadFile != null)
				return false;
		} else if (!uploadFile.equals(other.uploadFile))
			return false;
		if (uploadImage == null) {
			if (other.uploadImage != null)
				return false;
		} else if (!uploadImage.equals(other.uploadImage))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (youtubeId == null) {
			if (other.youtubeId != null)
				return false;
		} else if (!youtubeId.equals(other.youtubeId))
			return false;
		if (youtubeUrl == null) {
			if (other.youtubeUrl != null)
				return false;
		} else if (!youtubeUrl.equals(other.youtubeUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventId=" + eventId + ", eventUrl="
				+ eventUrl + ", summary=" + summary + ", location=" + location
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeZone=" + timeZone + ", introduction=" + introduction
				+ ", speaker=" + speaker + ", legal=" + legal
				+ ", seminarKind=" + seminarKind + ", youtubeUrl=" + youtubeUrl
				+ ", youtubeId=" + youtubeId + ", privacyStatus="
				+ privacyStatus + ", question=" + question + ", url=" + url
				+ ", uploadImage=" + uploadImage + ", uploadFile=" + uploadFile
				+ ", duration=" + duration + ", headline=" + headline + ", top=" + top
				+ ", login=" + login + ", username=" + username
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	
}
