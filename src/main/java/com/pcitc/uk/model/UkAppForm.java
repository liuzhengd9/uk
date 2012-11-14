package com.pcitc.uk.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonDateSerializer;

@Entity
@Table(name = "uk_app_form")
@JsonIgnoreProperties(value = { "ukFormItemSet" })
public class UkAppForm extends ParentEntity {

	private static final long serialVersionUID = 3992061699332505787L;

	@Column(name = "name")
	private String name;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_type")
	private Integer companyType;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "linkman")
	private String linkman;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "create_time")
	private Date createTime;

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "app_form_id")
	private Set<UkFormItem> ukFormItemSet;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getCompanyName() {

		return companyName;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public Integer getCompanyType() {

		return companyType;
	}

	public void setCompanyType(Integer companyType) {

		this.companyType = companyType;
	}

	public String getCompanyAddress() {

		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {

		this.companyAddress = companyAddress;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) {

		this.postcode = postcode;
	}

	public String getLinkman() {

		return linkman;
	}

	public void setLinkman(String linkman) {

		this.linkman = linkman;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	public Set<UkFormItem> getUkFormItemSet() {

		return ukFormItemSet;
	}

	public void setUkFormItemSet(Set<UkFormItem> ukFormItemSet) {

		this.ukFormItemSet = ukFormItemSet;
	}

}
