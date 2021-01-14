package com.company.entity;

public class Country {

	private int id;
	private String name;
	private String nationality;
	
	
	public Country() {

	}

	public Country( String name, String nationality) {
		this.name = name;
		this.nationality = nationality;
	}
	
	public Country(int id, String name, String nationality) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name +"("+ nationality+")";
	}
	
}
