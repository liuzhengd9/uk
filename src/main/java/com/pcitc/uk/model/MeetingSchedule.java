package com.pcitc.uk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonDateSerializer;

@Entity
@Table(name = "meeting_schedule")
public class MeetingSchedule extends ParentEntity {

	private static final long serialVersionUID = -6148339670851399070L;

	@Column(name = "room_id")
	private Integer roomId;

	@Column(name = "name")
	private String name;

	@Column(name = "sponsor")
	private String sponsor;

	@Column(name = "participant")
	private String participant;

	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "schedule_date")
	private Date scheduleDate;

	@Column(name = "time_index")
	private Integer timeIndex;

	public Integer getRoomId() {

		return roomId;
	}

	public void setRoomId(Integer roomId) {

		this.roomId = roomId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getSponsor() {

		return sponsor;
	}

	public void setSponsor(String sponsor) {

		this.sponsor = sponsor;
	}

	public String getParticipant() {

		return participant;
	}

	public void setParticipant(String participant) {

		this.participant = participant;
	}

	public Date getScheduleDate() {

		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {

		this.scheduleDate = scheduleDate;
	}

	public Integer getTimeIndex() {

		return timeIndex;
	}

	public void setTimeIndex(Integer timeIndex) {

		this.timeIndex = timeIndex;
	}

}
