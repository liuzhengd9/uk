package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.pcitc.uk.model.MeetingRoom;
import com.pcitc.uk.service.MeetingRoomService;
import com.pcitc.uk.test.BaseTest;

public class MeetingRoomServiceImplTest extends BaseTest {

	@Resource(name = "meetingRoomService", type = MeetingRoomService.class)
	private MeetingRoomService meetingRoomService;

	@Test
	public void add() {

		for (int i = 0; i < 10; i++) {
			meetingRoomService.save(new MeetingRoom());
		}
	}

}
