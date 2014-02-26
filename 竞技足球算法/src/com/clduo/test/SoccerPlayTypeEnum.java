package com.clduo.test;

public enum SoccerPlayTypeEnum {
	/** 单关 */
	WF_DG("WF_DG", "单关", "101", new String[] { "WF_DG" }),

	/** 2串1 */
	WF_2_1("WF_2_1", "2串1", "102", new String[] { "WF_2_1" }),

	/** 3串1 */
	WF_3_1("WF_3_1", "3串1", "103", new String[] { "WF_3_1" }),
	/** 3串3 */
	WF_3_3("WF_3_3", "3串3", "102", new String[] { "WF_2_1" }),
	/** 3串4 */
	WF_3_4("WF_3_4", "3串4", "102|103", new String[] { "WF_2_1", "WF_3_1" }),

	/** 4串1 */
	WF_4_1("WF_4_1", "4串1", "104", new String[] { "WF_4_1" }),
	/** 4串4 */
	WF_4_4("WF_4_4", "4串4", "103", new String[] { "WF_3_1" }),
	/** 4串5 */
	WF_4_5("WF_4_5", "4串5", "103|104", new String[] { "WF_3_1", "WF_4_1" }),
	/** 4串6 */
	WF_4_6("WF_4_6", "4串6", "102", new String[] { "WF_2_1" }),
	/** 4串11 */
	WF_4_11("WF_4_11", "4串11", "102|103|104", new String[] { "WF_2_1", "WF_3_1", "WF_4_1" }),

	/** 5串1 */
	WF_5_1("WF_5_1", "5串1", "105", new String[] { "WF_5_1" }),
	/** 5串5 */
	WF_5_5("WF_5_5", "5串5", "104", new String[] { "WF_4_1" }),
	/** 5串6 */
	WF_5_6("WF_5_6", "5串6", "104|105", new String[] { "WF_4_1", "WF_5_1" }),
	/** 5串10 */
	WF_5_10("WF_5_10", "5串10", "102", new String[] { "WF_2_1" }),
	/** 5串16 */
	WF_5_16("WF_5_16", "5串16", "103|104|105", new String[] { "WF_3_1", "WF_4_1", "WF_5_1" }),
	/** 5串20 */
	WF_5_20("WF_5_20", "5串20", "102|103", new String[] { "WF_2_1", "WF_3_1" }),
	/** 5串26 */
	WF_5_26("WF_5_26", "5串26", "102|103|104|105", new String[] { "WF_2_1", "WF_3_1", "WF_4_1", "WF_5_1" }),

	/** 6串1 */
	WF_6_1("WF_6_1", "6串1", "106", new String[] { "WF_6_1" }),
	/** 6串6 */
	WF_6_6("WF_6_6", "6串6", "105", new String[] { "WF_5_1" }),
	/** 6串7 */
	WF_6_7("WF_6_7", "6串7", "105|106", new String[] { "WF_5_1", "WF_6_1" }),
	/** 6串15 */
	WF_6_15("WF_6_15", "6串15", "102", new String[] { "WF_2_1" }),
	/** 6串20 */
	WF_6_20("WF_6_20", "6串20", "103", new String[] { "WF_3_1" }),
	/** 6串22 */
	WF_6_22("WF_6_22", "6串22", "104|105|106", new String[] { "WF_4_1", "WF_5_1", "WF_6_1" }),
	/** 6串35 */
	WF_6_35("WF_6_35", "6串35", "102|103", new String[] { "WF_2_1", "WF_3_1" }),
	/** 6串42 */
	WF_6_42("WF_6_42", "6串42", "103|104|105|106", new String[] { "WF_3_1", "WF_4_1", "WF_5_1", "WF_6_1" }),
	/** 6串50 */
	WF_6_50("WF_6_50", "6串50", "102|103|104", new String[] { "WF_2_1", "WF_3_1", "WF_4_1" }),
	/** 6串6 */
	WF_6_57("WF_6_57", "6串57", "102|103|104|105|106", new String[] { "WF_2_1", "WF_3_1", "WF_4_1", "WF_5_1", "WF_6_1" }),

	/** 7串1 */
	WF_7_1("WF_7_1", "7串1", "107", new String[] { "WF_7_1" }),
	/** 7串7 */
	WF_7_7("WF_7_7", "7串7", "106", new String[] { "WF_6_1" }),
	/** 7串8 */
	WF_7_8("WF_7_8", "7串8", "106|107", new String[] { "WF_6_1", "WF_7_1" }),
	/** 7串21 */
	WF_7_21("WF_7_21", "7串21", "105", new String[] { "WF_5_1" }),
	/** 7串35 */
	WF_7_35("WF_7_35", "7串35", "104", new String[] { "WF_4_1" }),
	/** 7串120 */
	WF_7_120("WF_7_120", "7串120", "102|103|104|105|106|107", new String[] { "WF_2_1", "WF_3_1", "WF_4_1", "WF_5_1", "WF_6_1", "WF_7_1" }),

	/** 8串1 */
	WF_8_1("WF_8_1", "8串1", "108", new String[] { "WF_8_1" }),
	/** 8串8 */
	WF_8_8("WF_8_8", "8串8", "107", new String[] { "WF_7_1" }),
	/** 8串9 */
	WF_8_9("WF_8_9", "8串9", "107|108", new String[] { "WF_7_1", "WF_8_1" }),
	/** 8串28 */
	WF_8_28("WF_8_28", "8串28", "106", new String[] { "WF_6_1" }),
	/** 8串56 */
	WF_8_56("WF_8_56", "8串56", "105", new String[] { "WF_5_1" }),
	/** 8串70 */
	WF_8_70("WF_8_70", "8串70", "104", new String[] { "WF_4_1" }),
	/** 8串247 */
	WF_8_247("WF_8_247", "8串247", "102|103|104|105|106|107|108", new String[] { "WF_2_1", "WF_3_1", "WF_4_1", "WF_5_1", "WF_6_1", "WF_7_1", "WF_8_1" });

	private String value;
	private String code;
	private String name;
	private String[] playTypes;

	private SoccerPlayTypeEnum(String value, String name, String code, String[] playTypes) {
		this.value = value;
		this.code = code;
		this.name = name;
		this.playTypes = playTypes;
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

	public String[] getPlayTypes() {
		return playTypes;
	}

}
