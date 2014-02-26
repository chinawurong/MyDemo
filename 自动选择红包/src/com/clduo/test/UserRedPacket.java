package com.clduo.test;

import java.util.Date;

/** 用户红包
 * 
 * @author xiangyong 2013-6-24 下午6:46:38 */
public class UserRedPacket implements Comparable<UserRedPacket> {

	private Long id;
	private Long userId;
	private Long amount;
	private Long orderId;
	private Date expiryTime;
	private Date createdTime;
	private Date modifiedTime;
	private String redPacketName;
	private Long totalCount;
	private Long totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRedPacketName() {
		return redPacketName;
	}

	public void setRedPacketName(String redPacketName) {
		this.redPacketName = redPacketName;
	}

	@Override
	public String toString() {
		return "UserRedPacket [id=" + id + ", userId=" + userId + ", amount=" + amount + ", orderId=" + orderId + ", expiryTime=" + expiryTime + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", redPacketName=" + redPacketName + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}

	@Override
	public int compareTo(UserRedPacket o) {

		return (int) (this.amount - o.getAmount());
	}

}
