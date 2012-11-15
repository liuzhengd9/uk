package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.dao.MeetingScheduleDao;
import com.pcitc.uk.model.MeetingSchedule;
import com.pcitc.uk.service.MeetingScheduleService;
import com.pcitc.uk.service.base.BaseServiceImpl;

@Service(value = "meetingScheduleService")
@Transactional
public class MeetingScheduleServiceImpl extends BaseServiceImpl<MeetingSchedule, Integer, MeetingScheduleDao> implements MeetingScheduleService {

	@Resource(name = "meetingScheduleDao", type = MeetingScheduleDao.class)
	private MeetingScheduleDao meetingScheduleDao;

	@Override
	public MeetingScheduleDao getDao() {

		return meetingScheduleDao;
	}

}
