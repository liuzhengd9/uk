package com.pcitc.uk.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.uk.model.MeetingCalendar;
import com.pcitc.uk.model.MeetingEvent;

@Controller
public class MeetingController {

	@RequestMapping(value = "/meeting/index.html")
	public String index() {

		return "meeting/meeting";
	}

	@RequestMapping(value = "/meeting/color.html")
	@ResponseBody
	public ModelMap color() {

		ModelMap model = new ModelMap();

		List<MeetingCalendar> calendars = new ArrayList<MeetingCalendar>();

		MeetingCalendar c1 = new MeetingCalendar();
		c1.setId(1);
		c1.setTitle("红色");
		c1.setColorId(2);
		c1.setIsHidden(false);

		calendars.add(c1);

		MeetingCalendar c2 = new MeetingCalendar();
		c2.setId(2);
		c2.setTitle("蓝色");
		c2.setColorId(22);
		c2.setIsHidden(false);

		calendars.add(c2);

		MeetingCalendar c3 = new MeetingCalendar();
		c3.setId(3);
		c3.setTitle("黄色");
		c3.setColorId(7);
		c3.setIsHidden(false);

		calendars.add(c3);

		MeetingCalendar c4 = new MeetingCalendar();
		c4.setId(4);
		c4.setTitle("绿色");
		c4.setColorId(26);
		c4.setIsHidden(false);

		calendars.add(c4);

		model.addAttribute("calendars", calendars);

		return model;
	}

	@RequestMapping(value = "/meeting/events.html")
	@ResponseBody
	public ModelMap events() {

		ModelMap model = new ModelMap();
		List<MeetingEvent> evts = new ArrayList<MeetingEvent>();
		model.addAttribute("evts", evts);
		return model;
	}
}
