package com.wyh.heap;

import java.util.Random;
/**
 * 使用heapify和不使用heapify的结果
 * 运行时间比较
 * */
public class Main {
	private static double testHeap(Integer[] testData, boolean isHeapify) {
		long startTime=System.nanoTime();
		MaxHeap<Integer>  maxheap;
		
		if(isHeapify)
			maxheap=new MaxHeap<>(testData);
		else {
			maxheap=new MaxHeap<>();
			for(int num:testData)
				maxheap.add(num);
		}
		
		int [] arr=new int[testData.length];
		for(int i=0;i<testData.length;i++) {
			arr[i]=maxheap.extractMax();
		}
		for(int i=1;i<testData.length;i++) {
			if(arr[i-1]-arr[i]<0)
				throw new IllegalArgumentException("error.");
		}
		System.out.println("Test maxheap completed.");
		long endTime=System.nanoTime();
		return (endTime-startTime)/1000000000.0;
	}
	public static void main(String[] args) {
		int n=1000000;
		
		
		Random random=new Random();
		Integer [] testData=new Integer[n];
		for(int i=0;i<n;i++) {
			testData[i]=random.nextInt(Integer.MAX_VALUE);
		}
		double time1=testHeap(testData,false);
		System.out.println("whitout heapify: "+time1+"s");
		
		double time2=testHeap(testData,true);
		System.out.println("whit heapify: "+time2+"s");
		
	}
}
