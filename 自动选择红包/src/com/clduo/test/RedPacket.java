package com.clduo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedPacket {

	public static void main(String[] args) {
		List<UserRedPacket> packets = new ArrayList<UserRedPacket>();
		packets.add(packet(6));
		packets.add(packet(3));
		packets.add(packet(2));

		int i = 5;
		List<UserRedPacket> packetsTemp = new ArrayList<UserRedPacket>(packets);
		System.out.println("金额：" + i);
		System.out.print("红包列表：");
		for (int j = 0; j < packetsTemp.size(); j++) {
			System.out.print(packetsTemp.get(j).getAmount() + ",");
		}
		System.out.println();
		Map<String, Object> result = getSelectPackets(i, packetsTemp);
		Long payAmount = (Long) result.get("payAmount");
		List<UserRedPacket> packetList = (List<UserRedPacket>) result.get("packets");

		System.out.println("应付金额：" + payAmount);
		double selectAmountAll = 0;
		for (int k = 0; k < packetList.size(); k++) {
			System.out.print("用到的红包：");
			System.out.print(packetList.get(k).getAmount() + " + " + selectAmountAll + " = ");
			selectAmountAll = selectAmountAll + packetList.get(k).getAmount();
			System.out.println(selectAmountAll);
		}
		System.out.println();
		System.out.println();

	}

	/** 获取选中的红包
	 * @param PayAmount 要支付的金额
	 * @param packets 红包列表
	 * @return map.get("payAmount"):除去红包的支付金额；map.get("packets"):选中的红包集合 */
	private static Map<String, Object> getSelectPackets(long payAmount, List<UserRedPacket> packets) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserRedPacket> selectPackets = new ArrayList<UserRedPacket>();// 存放选取的红包集合
		long amount = payAmount;// 要支付的金额
		long cha = 0, cha1 = 0, cha2 = 0, chane1 = 0, chane2 = 0;
		while (packets.size() > 0 && amount > 0) {
			int flag = 0, flag1 = 0, flag2 = 0;
			for (int i = 0, size = packets.size(); i < size; i++) {
				UserRedPacket packet = packets.get(i);
				if (i == 0) {
					cha = packet.getAmount();
					cha1 = packet.getAmount();
					cha2 = packet.getAmount();
				}
				long chane = amount - packet.getAmount();
				System.out.println("支付金额：" + amount + "红包金额：" + packet.getAmount() + "=" + chane);
				long chaneABS = Math.abs(chane);
				if (cha > chaneABS) {
					System.out.println("收到的红包金额：" + packet.getAmount());
					cha = chaneABS;
					flag = i;
				}
				if (chane >= 0 && cha1 > chaneABS) {
					cha1 = chaneABS;
					chane1 = chane;
					flag1 = i;
				}
				if (chane < 0 && cha2 > chaneABS) {
					cha2 = chaneABS;
					chane2 = chane;
					flag2 = i;
				}
			}
			// 如果出现金额是5元红包有4元和6元，优先使用6元红包
			if (Math.abs(chane1) == Math.abs(chane2)) {
				if (chane2 < chane1) {
					flag = flag2;
				} else {
					flag = flag1;
				}
			}
			UserRedPacket bigPacket = packets.get(flag);// 本次选中的红包
			amount = amount - bigPacket.getAmount();// 支付金额减红包的金额
			selectPackets.add(bigPacket);// 放到选中的红包集合里
			packets.remove(flag);// 把红包从红包堆里拿出来
		}

		RedPacketSortList comparator = new RedPacketSortList();
		Collections.sort(selectPackets, comparator);//排序
		List<UserRedPacket> selectPackets2 = new ArrayList<UserRedPacket>();
		long amount2 = payAmount;amount = payAmount;
		for (int i = 0, size = selectPackets.size(); i < size; i++) {
			UserRedPacket bigPacket = selectPackets.get(i);
			selectPackets2.add(bigPacket);
			amount = amount - bigPacket.getAmount();// 支付金额减红包的金额
			if (amount2 - bigPacket.getAmount() <= 0) {
				break;
			}
			amount2 = amount2 - bigPacket.getAmount();
		}
		map.put("packets", selectPackets2);
		map.put("payAmount", amount);
		return map;
	}

	private static UserRedPacket packet(long amount) {
		UserRedPacket packet = new UserRedPacket();
		packet.setAmount(amount);
		packet.setId(amount);
		return packet;
	}
}
