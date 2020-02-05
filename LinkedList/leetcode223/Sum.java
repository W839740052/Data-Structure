package com.wyh.leetcode223;
/**
 * 小试牛刀，尝试用递归来写数组的和
 * */
public class Sum {
	public static int sum(int arr[]) {
		//递归的初始调用是从0～n-1所有数的和
		return sum(arr,0);
	}
	
	//这里对用户是屏蔽的，用户不需要传进来一个左边界的数组
	//为了增加数组求和的效率，如果从索引为0开始往后求和，那么从索引为1开始求和肯定更快，因为少了一个元素的求和过程
	//计算arr[l.....n)这个区间内所有数字的和
	private static int sum(int[] arr, int l) {
		if(l==arr.length)//初始传入的值为l=0;即数组为空
			return 0;
		return arr[l]+sum(arr,l+1);//这里，我们就行l到n区间的和，变为了从计算l+1到n这个区间的和，解决问题的规模变小了，直到最终l和数组超度相等时。
	}
	public static void main(String[] args) {
		int[] nums= {1,2,3,4,5,6,7,8};
		System.out.println(sum(nums));
	}
}
