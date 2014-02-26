package com.clduo.test;

public enum SoccerEnum {
	/** 竞彩足球胜平负 */
	JC_ZQ_SPF("JC_ZQ_SPF", "77", "胜平负"),
	/** 竞彩足球让球胜平负 */
	JC_ZQ_RQSPF("JC_ZQ_RQSPF", "70", "让球胜平负"),
	/** 竞彩足球比分 */
	JC_ZQ_BF("JC_ZQ_BF", "71", "比分"),
	/** 竞彩足球总进球 */
	JC_ZQ_ZJQ("JC_ZQ_ZJQ", "72", "总进球 "),
	/** 竞彩足球半全场 */
	JC_ZQ_BQC("JC_ZQ_BQC", "73", "半全场"),
	/** 竞彩足球混合过关 */
	JC_ZQ_HHGG("JC_ZQ_HHGG", "76", "混合过关");

	private String value;
	private String code;
	private String name;

	private SoccerEnum(String value, String code, String name) {
		this.value = value;
		this.code = code;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
