package com.tpg.tmjug.aop.entity;

public class User {

	private long id;
	private String name;
	private int age;
	
	public User(final String name, final int age) {
		this.name = name;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User: [ id = " + id + ", name = " + name + ", age = " + age  + " ]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof User && ((User) obj).getId() == id;
	}
}
