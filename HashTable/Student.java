package com.wyh.hashcode;

import java.util.HashMap;
import java.util.HashSet;

public class Student {
	int grade;
	int cls;
	String firstName;
	String lastName;
	public Student(int grade, int cls, String firstName, String lastName) {
		super();
		this.grade = grade;
		this.cls = cls;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public int hashCode() {
		int B=10;
		
		int hash=0;
		hash=hash*B+grade;
		hash=hash*B+cls;
		hash=hash*B+firstName.toLowerCase().hashCode();
		hash=hash*B+lastName.toLowerCase().hashCode();
		return hash;
	}
	@Override
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(o==null)
			return false;
		if(getClass() !=o.getClass())
			return false;
		
		Student another=(Student)o;
		return this.grade==another.grade && 
				this.cls==another.cls && 
				this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) && 
				this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
		
	}
	public static void main(String[] args) {
		Student std=new Student(3,4,"yinghao","wang");
		System.out.println(std.hashCode());
		
		HashSet<Student> set=new HashSet<>();
		set.add(std);
		
		HashMap<Student,Integer> map=new HashMap<>();
		map.put(std, 100);
	}
}
