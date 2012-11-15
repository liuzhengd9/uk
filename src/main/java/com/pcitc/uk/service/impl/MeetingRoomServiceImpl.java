package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.dao.MeetingRoomDao;
import com.pcitc.uk.model.MeetingRoom;
import com.pcitc.uk.service.MeetingRoomService;
import com.pcitc.uk.service.base.BaseServiceImpl;

@Service(value = "meetingRoomService")
@Transactional
public class MeetingRoomServiceImpl extends BaseServiceImpl<MeetingRoom, Integer, MeetingRoomDao> implements MeetingRoomService {

	@Resource(name = "meetingRoomDao", type = MeetingRoomDao.class)
	private MeetingRoomDao meetingRoomDao;

	@Override
	public MeetingRoomDao getDao() {

		return meetingRoomDao;
	}

}
