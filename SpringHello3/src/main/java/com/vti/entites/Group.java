package com.vti.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "`group`")
public class Group {
	@Column(name = "GroupID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "GroupName")
	private String name;

	@Column(name = "CreatorID")
	private int CreatorID;

//	@ManyToMany(mappedBy = "listGroups")
//	private List<Account> listAccs;

	public Group() {

	}

	public Group(String name, int creatorID) {

		this.name = name;
		CreatorID = creatorID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreatorID() {
		return CreatorID;
	}

	public void setCreatorID(int creatorID) {
		CreatorID = creatorID;
	}

//	public List<Account> getListAccs() {
//		return listAccs;
//	}

//	public void setListAccs(List<Account> listAccs) {
//		this.listAccs = listAccs;
//	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", CreatorID=" + CreatorID + "]";
	}

}
