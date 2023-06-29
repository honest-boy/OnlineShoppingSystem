package com.online.shopping.global;

import java.util.ArrayList;
import java.util.List;

// creating this class for manage the total amount in whole session

public class GlobalData {

	public static List<Integer> amounts;

	static {
		amounts = new ArrayList<>(1);
	}

	public int total;

	public int getTotal() {
		return total;
	}

	public int setTotal(int total) {
		return this.total = total;
	}
}
