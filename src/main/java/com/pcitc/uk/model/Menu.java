package com.pcitc.uk.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pcitc.uk.json.JsonNumberSerializer;

@Entity
@Table(name = "menu")
public class Menu extends ParentEntity {

	private static final long serialVersionUID = 6785258184439246540L;

	@Column(name = "text")
	private String text;

	@Column(name = "url")
	private String url;

	@Column(name = "cls")
	private String cls;

	@JsonSerialize(using = JsonNumberSerializer.class)
	@Column(name = "leaf")
	private Boolean leaf;

	@Column(name = "parent_id")
	private Integer parentId;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Set<Menu> children;

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getCls() {

		return cls;
	}

	public void setCls(String cls) {

		this.cls = cls;
	}

	public Boolean getLeaf() {

		return leaf;
	}

	public void setLeaf(Boolean leaf) {

		this.leaf = leaf;
	}

	public Integer getParentId() {

		return parentId;
	}

	public void setParentId(Integer parentId) {

		this.parentId = parentId;
	}

	public Set<Menu> getChildren() {

		return children;
	}

	public void setChildren(Set<Menu> children) {

		this.children = children;
	}

}
