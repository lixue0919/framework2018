package com.syfri.digitalplan.model.basicinfo.organization;

import com.syfri.baseapi.model.ValueObject;

import java.io.Serializable;
import java.util.List;

public class OrganizationTree extends ValueObject implements Serializable{

	private static final long serialVersionUID = 1L;

	private String uuid;	//主键
	private String jgjc;	//机构简称
	private List<OrganizationTree> children;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getJgjc() {
		return jgjc;
	}

	public void setJgjc(String jgjc) {
		this.jgjc = jgjc;
	}

	public List<OrganizationTree> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationTree> children) {
		this.children = children;
	}
}