package com.pcitc.uk.model;

public class MeetingCalendar extends ParentEntity {

	private static final long serialVersionUID = 7539401855797478583L;

	private String title;

	private String description;

	private Integer colorId;

	private Boolean isHidden;

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Integer getColorId() {

		return colorId;
	}

	public void setColorId(Integer colorId) {

		this.colorId = colorId;
	}

	public Boolean getIsHidden() {

		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {

		this.isHidden = isHidden;
	}
}
