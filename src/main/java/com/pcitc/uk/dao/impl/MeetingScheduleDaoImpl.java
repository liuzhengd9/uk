package com.pcitc.uk.dao.impl;

import org.springframework.stereotype.Repository;

import com.pcitc.uk.dao.MeetingScheduleDao;
import com.pcitc.uk.dao.base.BaseDaoImpl;
import com.pcitc.uk.model.MeetingSchedule;

@Repository(value = "meetingScheduleDao")
public class MeetingScheduleDaoImpl extends BaseDaoImpl<MeetingSchedule, Integer> implements MeetingScheduleDao {

	@Override
	public Class<MeetingSchedule> getPersistenceClass() {

		return MeetingSchedule.class;
	}

}
