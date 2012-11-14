package com.pcitc.uk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ParentEntity implements Serializable {

	private static final long serialVersionUID = 5580306039086344285L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public int hashCode() {

		return 0;
	}

	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (!(o instanceof ParentEntity) || this.id == null || ((ParentEntity) o).getId() == null || this.id.equals(0) || ((ParentEntity) o).getId().equals(0)) {
			return false;
		}
		return this.id.equals(((ParentEntity) o).getId());
	}

}
