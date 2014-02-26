package com.clduo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SetTest {

	public static void main(String[] args) throws Exception {
		List<Integer> qiudui = new ArrayList<Integer>();
		qiudui.add(1);
		qiudui.add(2);
		qiudui.add(3);
		qiudui.add(4);
		qiudui.add(5);
		qiudui.add(6);
		qiudui.add(7);
		qiudui.add(8);
		qiudui.add(9);
		qiudui.add(10);
		qiudui.add(11);
		qiudui.add(12);
		qiudui.add(13);
		qiudui.add(14);
		qiudui.add(15);

		SetTest test = new SetTest();
		long time1 = new Date().getTime();
		test.getCount(qiudui, 2);
		test.getCount(qiudui, 3);
		test.getCount(qiudui, 4);
		test.getCount(qiudui, 5);
		test.getCount(qiudui, 6);
		test.getCount(qiudui, 7);
		test.getCount(qiudui, 8);
		long time2 = new Date().getTime();
		System.out.println();
		System.out.println(time1);
		System.out.println(time2);
		System.out.println(time2-time1);
		
	}

	private void getCount(List<Integer> qiudui, int count) {
		int nCnt = qiudui.size();
		int nBit = 1 << nCnt;
		for (int i = 1; i <= nBit; i++) {
			int[] indexs = new int[] {};
			for (int j = 0; j < nCnt; j++) {
				if ((1 << j & i) != 0) {
					indexs = Arrays.copyOf(indexs, indexs.length + 1);
					indexs[indexs.length - 1] = j;
				}
			}
			if (indexs.length == count) {
				for (int j : indexs) {
//					System.out.print(qiudui.get(j) + "---");
				}
//				System.out.print(" | ");
			}
		}
	}
}
