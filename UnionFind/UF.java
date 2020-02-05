package com.wyh.unionfind;

public interface UF {
	boolean isConnected(int p,int q);
	int getSize();
	void unionElements(int p,int q);
}
