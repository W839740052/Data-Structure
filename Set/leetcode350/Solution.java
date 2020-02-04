package com.wyh.leetcode350;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
/*
int[] res=new int[list.size()];
int k=0;
Iterator<Integer> ite=list.iterator();
while(ite.hasNext()) {
	res[k]=ite.next();
	k++;
}

public class Solution350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums1){
            if(!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int num: nums2){
            if(map.containsKey(num)){
                res.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] ret = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i ++)
            ret[i] = res.get(i);

        return ret;
    }
}
*/
public class Solution {
	public int[] intersect(int[] nums1, int[] nums2){
		
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for(int num:nums1) {
			if(!map.containsKey(num))
				map.put(num, 1);
			else
				map.put(num, map.get(num)+1);
		}
		ArrayList<Integer> list=new ArrayList<>();
		for(int num:nums2) {
			if(map.containsKey(num)) {
				list.add(num);
				map.put(num, map.get(num) - 1);
				if(map.get(num)==0)
					map.remove(num);
			}
		}
		int[] res=new int[list.size()];
		for(int i=0;i<list.size();i++)
			res[i]=list.get(i);
		return res;
	}
	
}
