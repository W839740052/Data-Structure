package com.wyh.hashtable;

import java.util.TreeMap;

public class HashTable<K,V> {
	//M素数表
	private final int[] capacity
    = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
    49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
    12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	
	private static final int upperTol=10;//哈希冲突的上界
	private static final int lowerTol=2;//下界
	private int capacityIndex=0;//初始容量为在capacity数组中索引为0对应的值
	private TreeMap<K,V> [] hashtable;
	private int M;
	private int size;
	
	public HashTable() {
		this.M=capacity[capacityIndex];
		size=0;
		hashtable=new TreeMap[M];
		for(int i=0;i<M;i++)
			hashtable[i]=new TreeMap<>();//对于TreeMap数组中每一个元素进行实例化
	}
	/*
	//这里采用了上面的给定的容量数组，因此弃用
	public HashTable() {
		this(initCapacity);
	}
	*/
	
	//将键转化为当前hashtable中索引的过程
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff)%M;//按位与，消除key.hashcode对应整型的符号
	}
	public int getSize() {
		return size;
	}
	public void add(K key,V value) {
		TreeMap<K,V> map=hashtable[hash(key)];//用treemap暂存一下，减少下面多次计算hashtable的值的时间
		if(map.containsKey(key))
			map.put(key, value);//利用treemap的方法，修改对应键中的值
		else {
			map.put(key, value);//此时为添加数据
			size++;
			if(size>=upperTol*M && capacityIndex+1<capacity.length) {//N/M >=upperTol,这里的size即为N，哈希表一共添加的元素个数&&防止越界
				capacityIndex++;
				resize(capacity[capacityIndex]);
			}
		}
	}
	public V remove(K key) {
		TreeMap<K,V> map=hashtable[hash(key)];//用treemap暂存一下，减少下面多次计算hashtable的值的时间
		V ret=null;
		if(map.containsKey(key)) {
			ret=map.remove(key);
			size--;
			if(size<lowerTol*M && capacityIndex-1>0)
				capacityIndex--;
				resize(capacity[capacityIndex]);
		}
		return ret;
	}
	
	public void set(K key,V value) {
		TreeMap<K,V> map=hashtable[hash(key)];
		if(!map.containsKey(key))
			throw new IllegalArgumentException(key+"dosen't exist!");
		map.put(key, value);
	}
	
	public boolean contains(K key) {
		return hashtable[hash(key)].containsKey(key);
	}
	
	public V get(K key) {
		return hashtable[hash(key)].get(key);
	}
	
	private void resize(int newM) {
		TreeMap<K,V>[] newHashTable=new TreeMap[newM];
		for(int i=0;i<newM;i++)
			newHashTable[i]=new TreeMap<>();
		
		int oldM=M;//保存扩容前的M，方便下面的遍历
		this.M=newM;
		for(int i=0;i<oldM;i++) {
			TreeMap<K,V> map=hashtable[i];//遍历hashtable数组中的每一个元素(TreeMap)
			for(K key:map.keySet())//将每一个元素中的键遍历
				newHashTable[hash(key)].put(key,map.get(key));//对新的数组进行一次填充，获取原来数组中每个元素键的值，存入
		}
		this.hashtable=newHashTable;
	}
	
}
