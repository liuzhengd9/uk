package com.pcitc.uk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonNumberSerializer;

@Entity
@Table(name = "meeting_room")
public class MeetingRoom extends ParentEntity {

	private static final long serialVersionUID = -115088900494776978L;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "manager")
	private String manager;

	@Column(name = "manager_phone")
	private String managerPhone;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "is_valid")
	private Boolean isValid;

	@Column(name = "description")
	private String description;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getManager() {

		return manager;
	}

	public void setManager(String manager) {

		this.manager = manager;
	}

	public String getManagerPhone() {

		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {

		this.managerPhone = managerPhone;
	}

	public Boolean getIsValid() {

		return isValid;
	}

	public void setIsValid(Boolean isValid) {

		this.isValid = isValid;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}
}
