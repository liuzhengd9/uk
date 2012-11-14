package com.pcitc.uk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonNumberSerializer;

@Entity
@Table(name = "uk_form_item")
public class UkFormItem extends ParentEntity {

	private static final long serialVersionUID = 717557223975744336L;

	@Column(name = "app_form_id")
	private Integer appFormId;

	@Column(name = "applicant")
	private String applicant;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "is_uk")
	private Boolean isUk;

	@Column(name = "ad_name")
	private String adName;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "is_tms")
	private Boolean isTms;

	@Column(name = "tms_account")
	private String tmsAccount;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "is_new")
	private Boolean isNew;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "is_special")
	private Boolean isSpecial;

	@Column(name = "three_level_dept")
	private String threeLevelDept;

	@Column(name = "four_level_dept")
	private String fourLevelDept;

	@Column(name = "postal_address")
	private String postalAddress;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "position")
	private String position;

	@Column(name = "identity_card_no")
	private String identityCardNo;

	@Column(name = "phone")
	private String phone;

	@Column(name = "login_mode")
	private Integer loginMode;

	@Column(name = "remark")
	private String remark;

	public Integer getAppFormId() {

		return appFormId;
	}

	public void setAppFormId(Integer appFormId) {

		this.appFormId = appFormId;
	}

	public String getApplicant() {

		return applicant;
	}

	public void setApplicant(String applicant) {

		this.applicant = applicant;
	}

	public Boolean getIsUk() {

		return isUk;
	}

	public void setIsUk(Boolean isUk) {

		this.isUk = isUk;
	}

	public String getAdName() {

		return adName;
	}

	public void setAdName(String adName) {

		this.adName = adName;
	}

	public Boolean getIsTms() {

		return isTms;
	}

	public void setIsTms(Boolean isTms) {

		this.isTms = isTms;
	}

	public String getTmsAccount() {

		return tmsAccount;
	}

	public void setTmsAccount(String tmsAccount) {

		this.tmsAccount = tmsAccount;
	}

	public Boolean getIsNew() {

		return isNew;
	}

	public void setIsNew(Boolean isNew) {

		this.isNew = isNew;
	}

	public Boolean getIsSpecial() {

		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {

		this.isSpecial = isSpecial;
	}

	public String getThreeLevelDept() {

		return threeLevelDept;
	}

	public void setThreeLevelDept(String threeLevelDept) {

		this.threeLevelDept = threeLevelDept;
	}

	public String getFourLevelDept() {

		return fourLevelDept;
	}

	public void setFourLevelDept(String fourLevelDept) {

		this.fourLevelDept = fourLevelDept;
	}

	public String getPostalAddress() {

		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {

		this.postalAddress = postalAddress;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) {

		this.postcode = postcode;
	}

	public String getPosition() {

		return position;
	}

	public void setPosition(String position) {

		this.position = position;
	}

	public String getIdentityCardNo() {

		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {

		this.identityCardNo = identityCardNo;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	public Integer getLoginMode() {

		return loginMode;
	}

	public void setLoginMode(Integer loginMode) {

		this.loginMode = loginMode;
	}

	public String getRemark() {

		return remark;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}

}
