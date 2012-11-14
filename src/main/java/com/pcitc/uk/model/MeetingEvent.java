package com.pcitc.uk.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonDateSerializer;

public class MeetingEvent extends ParentEntity {

	private static final long serialVersionUID = 557855617970750509L;

	private Integer calendarId;

	private String title;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;

	private String recurRule;

	private String location;

	private String notes;

	private String url;

	private Boolean isAllDay;

	private String reminder;

	public Integer getCalendarId() {

		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {

		this.calendarId = calendarId;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	public String getRecurRule() {

		return recurRule;
	}

	public void setRecurRule(String recurRule) {

		this.recurRule = recurRule;
	}

	public String getLocation() {

		return location;
	}

	public void setLocation(String location) {

		this.location = location;
	}

	public String getNotes() {

		return notes;
	}

	public void setNotes(String notes) {

		this.notes = notes;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public Boolean getIsAllDay() {

		return isAllDay;
	}

	public void setIsAllDay(Boolean isAllDay) {

		this.isAllDay = isAllDay;
	}

	public String getReminder() {

		return reminder;
	}

	public void setReminder(String reminder) {

		this.reminder = reminder;
	}

}
