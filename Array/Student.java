package com.wyh.array;

public class Student {
	private String name;
	private int score;
	
	public Student(String studentName, int studentScore) {
		name=studentName;
		score=studentScore;
	}
	
	@Override
	public String toString() {
		return String.format("Student(name: %s, score: %d", name,score);
	}
	
	public static void main(String[] args) {
		Array<Student> arr=new Array<>();
		Student st1=new Student("cherry",90);
		Student st2=new Student("john",97);
		Student st3=new Student("lili",96);
		arr.addLast(st1);
		arr.addLast(st2);
		arr.addLast(st3);
		/*
		arr.addLast(new Student("marry",80));
		arr.addLast(new Student("john",88));
		arr.addLast(new Student("nina",90));
		*/
		System.out.println(arr);
	}
}
