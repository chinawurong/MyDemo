package com.clduo.test;

import java.util.List;

/** 比赛信息
 * @author wanglei 2013-12-27 下午3:23:06 */
public class SoccerMatchInfo {
	private String id;
	private String hostRank; //主场排名
	private String visitRank; //客场排名
	private String historyScore; //历史交锋
	private String odds0; //平均赔率
	private String odds1; //平均赔率
	private String odds3; //平均赔率
	private String league; //赛事类别
	private String hostTeam; //主队
	private String visitTeam; //客队
	private String deadlineTime; //截止时间
	private String realStartTime; //开奖时间
	private String matchNo; //场的序列
	private String weekDay;

	private List<SoccerDetail> detail;

	public List<SoccerDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SoccerDetail> detail) {
		this.detail = detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostRank() {
		return hostRank;
	}

	public void setHostRank(String hostRank) {
		this.hostRank = hostRank;
	}

	public String getVisitRank() {
		return visitRank;
	}

	public void setVisitRank(String visitRank) {
		this.visitRank = visitRank;
	}

	public String getHistoryScore() {
		return historyScore;
	}

	public void setHistoryScore(String historyScore) {
		this.historyScore = historyScore;
	}

	public String getOdds0() {
		return odds0;
	}

	public void setOdds0(String odds0) {
		this.odds0 = odds0;
	}

	public String getOdds1() {
		return odds1;
	}

	public void setOdds1(String odds1) {
		this.odds1 = odds1;
	}

	public String getOdds3() {
		return odds3;
	}

	public void setOdds3(String odds3) {
		this.odds3 = odds3;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(String hostTeam) {
		this.hostTeam = hostTeam;
	}

	public String getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(String visitTeam) {
		this.visitTeam = visitTeam;
	}

	public String getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(String deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public String getRealStartTime() {
		return realStartTime;
	}

	public void setRealStartTime(String realStartTime) {
		this.realStartTime = realStartTime;
	}

	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	/** 比赛玩法
	 * @author wanglei */
	public static class SoccerDetail {
		private String lotteryType;
		private String concede;
		private List<SoccerContent> content;

		public List<SoccerContent> getContent() {
			return content;
		}

		public void setContent(List<SoccerContent> content) {
			this.content = content;
		}

		public String getLotteryType() {
			return lotteryType;
		}

		public void setLotteryType(String lotteryType) {
			this.lotteryType = lotteryType;
		}

		public String getConcede() {
			return concede;
		}

		public void setConcede(String concede) {
			this.concede = concede;
		}

	}

	/** 比赛赔率
	 * @author wanglei */
	public static class SoccerContent {
		private String resultName;
		private String spName;
		private String spValue;

		public String getResultName() {
			return resultName;
		}

		public void setResultName(String resultName) {
			this.resultName = resultName;
		}

		public String getSpName() {
			return spName;
		}

		public void setSpName(String spName) {
			this.spName = spName;
		}

		public String getSpValue() {
			return spValue;
		}

		public void setSpValue(String spValue) {
			this.spValue = spValue;
		}

	}

}
