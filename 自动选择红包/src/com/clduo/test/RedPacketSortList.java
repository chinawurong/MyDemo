package com.clduo.test;

import java.util.Comparator;

/** List排序工具类
 * @author wanglei
 * 
 * @param <E> */
public class RedPacketSortList implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		UserRedPacket redPacket1 = (UserRedPacket) o1;
		UserRedPacket redPacket2 = (UserRedPacket) o2;

		if (redPacket1.getAmount() > redPacket2.getAmount()) {
			return 0;
		} else {
			return 1;
		}
	}

}
