package com.pcitc.uk.dao.impl;

import org.springframework.stereotype.Repository;

import com.pcitc.uk.dao.MeetingRoomDao;
import com.pcitc.uk.dao.base.BaseDaoImpl;
import com.pcitc.uk.model.MeetingRoom;

@Repository(value = "meetingRoomDao")
public class MeetingRoomDaoImpl extends BaseDaoImpl<MeetingRoom, Integer> implements MeetingRoomDao {

	@Override
	public Class<MeetingRoom> getPersistenceClass() {

		return MeetingRoom.class;
	}

}
