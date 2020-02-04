package com.wyh.leetcode349;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;



public class Solution {
	public int[] intersection(int[] nums1,int[] nums2) {
		TreeSet<Integer> set=new TreeSet<>();
		for(int num:nums1)
			set.add(num);
		
		ArrayList<Integer> list=new ArrayList<>();
		for(int num:nums2) {
			if(set.contains(num)) {
				list.add(num);
				set.remove(num);
			}
		}
		int[] res=new int[list.size()];
		/*
		Iterator<Integer> ite=list.iterator();
		int k=0;
		while(ite.hasNext()) {
			res[k]=ite.next();
			k++;
		}
		return res;
		*/
		for(int i=0;i<list.size();i++)
			res[i]=list.get(i);
		return res;
	}
}
