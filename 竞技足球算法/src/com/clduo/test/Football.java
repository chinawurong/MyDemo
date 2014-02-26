package com.clduo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.clduo.test.SoccerMatchInfo.SoccerContent;

public class Football {

	private List<Data> datas;
	private String lotteryType;

	public static void main(String[] args) {
		Football football = new Football();
		football.lotteryType = SoccerEnum.JC_ZQ_SPF.getValue();
		football.datas = new ArrayList<Data>();
		Data data = new Data();
	}

	/** 获取玩法注数和赔率
	 * @return { 注数, 最小赔率, 最大赔率 } */
	public double[] playType(List<String> plays) {
		if (SoccerEnum.JC_ZQ_HHGG.getValue().equals(lotteryType)) {
			//获取混合投注注数
			List<Data> initDatas = new ArrayList<Data>();
			for (Data data : datas) {
				Data newData = new Data();
				for (String key : data.getListMap().keySet()) {
					newData.getData(lotteryType).getListMap().putAll(data.getData(key).getListMap());
				}
				initDatas.add(newData);
			}
			double[] countAmount = getPlayType(plays, initDatas);
			//获取混合投注最小赔率和最大赔率
			initDatas.clear();
			for (Data data : datas) {
				for (String key : data.getListMap().keySet()) {
					Data newData = new Data();
					newData.getData(lotteryType).getListMap().putAll(data.getData(key).getListMap());
					initDatas.add(newData);
				}
			}
			double[] initCountAmount = getPlayType(plays, initDatas);
			return new double[] { countAmount[0], initCountAmount[1], initCountAmount[2] };
		} else {
			return getPlayType(plays, datas);
		}
	}

	/** 获取玩法 */
	private double[] getPlayType(List<String> plays, List<Data> dataList) {
		double count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		double[] wf;
		for (String value : plays) {
			SoccerPlayTypeEnum playType = SoccerPlayTypeEnum.valueOf(value);
			for (String type : playType.getPlayTypes()) {
				if (SoccerPlayTypeEnum.WF_DG.getValue().equals(type)) {
					wf = wf_dg(dataList);
				} else if (SoccerPlayTypeEnum.WF_2_1.getValue().equals(type)) {
					wf = wf_2_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_3_1.getValue().equals(type)) {
					wf = wf_3_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_4_1.getValue().equals(type)) {
					wf = wf_4_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_5_1.getValue().equals(type)) {
					wf = wf_5_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_6_1.getValue().equals(type)) {
					wf = wf_6_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_7_1.getValue().equals(type)) {
					wf = wf_7_1(dataList);
				} else if (SoccerPlayTypeEnum.WF_8_1.getValue().equals(type)) {
					wf = wf_8_1(dataList);
				} else {
					wf = new double[] { 0, 0, 0 };
				}
				count += wf[0];
				if (wf[1] < minAmout) {
					minAmout = wf[1];
				}
				maxAmout += wf[2];
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 获取计数 */
	private int count(int index, List<Data> dataList) {
		return dataList.get(index).getData(lotteryType).getListMap().size();
	}

	/** 获取map */
	private Map<String, Object> map(int index, List<Data> dataList) {
		return dataList.get(index).getData(lotteryType).getListMap();
	}

	/** 玩法单关 */
	private double[] wf_dg(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			count += count(c, dataList);
			double[] dc = getDouble(map(c, dataList));
			maxAmout += dc[1];
			if (dc[0] < minAmout) {
				minAmout = dc[0];
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法2串1 */
	private double[] wf_2_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				count += count(c, dataList) * count(c2, dataList);
				double[] dc = getDouble(map(c, dataList));
				double[] dc2 = getDouble(map(c2, dataList));
				maxAmout += dc[1] * dc2[1];
				double min = dc[0] * dc2[0];
				if (min < minAmout) {
					minAmout = min;
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法3串1 */
	private double[] wf_3_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					count += count(c, dataList) * count(c2, dataList) * count(c3, dataList);
					double[] dc = getDouble(map(c, dataList));
					double[] dc2 = getDouble(map(c2, dataList));
					double[] dc3 = getDouble(map(c3, dataList));
					maxAmout += dc[1] * dc2[1] * dc3[1];
					double min = dc[0] * dc2[0] * dc3[0];
					if (min < minAmout) {
						minAmout = min;
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法4串1 */
	private double[] wf_4_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					for (int c4 = c3 - 1; c4 >= 0; c4--) {
						count += count(c, dataList) * count(c2, dataList) * count(c3, dataList) * count(c4, dataList);
						double[] dc = getDouble(map(c, dataList));
						double[] dc2 = getDouble(map(c2, dataList));
						double[] dc3 = getDouble(map(c3, dataList));
						double[] dc4 = getDouble(map(c4, dataList));
						maxAmout += dc[1] * dc2[1] * dc3[1] * dc4[1];
						double min = dc[0] * dc2[0] * dc3[0] * dc4[0];
						if (min < minAmout) {
							minAmout = min;
						}
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法5串1 */
	private double[] wf_5_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					for (int c4 = c3 - 1; c4 >= 0; c4--) {
						for (int c5 = c4 - 1; c5 >= 0; c5--) {
							count += count(c, dataList) * count(c2, dataList) * count(c3, dataList) * count(c4, dataList) * count(c5, dataList);
							double[] dc = getDouble(map(c, dataList));
							double[] dc2 = getDouble(map(c2, dataList));
							double[] dc3 = getDouble(map(c3, dataList));
							double[] dc4 = getDouble(map(c4, dataList));
							double[] dc5 = getDouble(map(c5, dataList));
							maxAmout += dc[1] * dc2[1] * dc3[1] * dc4[1] * dc5[1];
							double min = dc[0] * dc2[0] * dc3[0] * dc4[0] * dc5[0];
							if (min < minAmout) {
								minAmout = min;
							}
						}
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法6串1 */
	private double[] wf_6_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					for (int c4 = c3 - 1; c4 >= 0; c4--) {
						for (int c5 = c4 - 1; c5 >= 0; c5--) {
							for (int c6 = c5 - 1; c6 >= 0; c6--) {
								count += count(c, dataList) * count(c2, dataList) * count(c3, dataList) * count(c4, dataList) * count(c5, dataList) * count(c6, dataList);
								double[] dc = getDouble(map(c, dataList));
								double[] dc2 = getDouble(map(c2, dataList));
								double[] dc3 = getDouble(map(c3, dataList));
								double[] dc4 = getDouble(map(c4, dataList));
								double[] dc5 = getDouble(map(c5, dataList));
								double[] dc6 = getDouble(map(c6, dataList));
								maxAmout += dc[1] * dc2[1] * dc3[1] * dc4[1] * dc5[1] * dc6[1];
								double min = dc[0] * dc2[0] * dc3[0] * dc4[0] * dc5[0] * dc6[0];
								if (min < minAmout) {
									minAmout = min;
								}
							}
						}
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法7串1 */
	private double[] wf_7_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					for (int c4 = c3 - 1; c4 >= 0; c4--) {
						for (int c5 = c4 - 1; c5 >= 0; c5--) {
							for (int c6 = c5 - 1; c6 >= 0; c6--) {
								for (int c7 = c6 - 1; c7 >= 0; c7--) {
									count += count(c, dataList) * count(c2, dataList) * count(c3, dataList) * count(c4, dataList) * count(c5, dataList) * count(c6, dataList) * count(c7, dataList);
									double[] dc = getDouble(map(c, dataList));
									double[] dc2 = getDouble(map(c2, dataList));
									double[] dc3 = getDouble(map(c3, dataList));
									double[] dc4 = getDouble(map(c4, dataList));
									double[] dc5 = getDouble(map(c5, dataList));
									double[] dc6 = getDouble(map(c6, dataList));
									double[] dc7 = getDouble(map(c7, dataList));
									maxAmout += dc[1] * dc2[1] * dc3[1] * dc4[1] * dc5[1] * dc6[1] * dc7[1];
									double min = dc[0] * dc2[0] * dc3[0] * dc4[0] * dc5[0] * dc6[0] * dc7[0];
									if (min < minAmout) {
										minAmout = min;
									}
								}
							}
						}
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 玩法8串1 */
	private double[] wf_8_1(List<Data> dataList) {
		int count = 0;
		double minAmout = Double.MAX_VALUE;
		double maxAmout = 0;
		for (int c = 0; c < dataList.size(); c++) {
			for (int c2 = c - 1; c2 >= 0; c2--) {
				for (int c3 = c2 - 1; c3 >= 0; c3--) {
					for (int c4 = c3 - 1; c4 >= 0; c4--) {
						for (int c5 = c4 - 1; c5 >= 0; c5--) {
							for (int c6 = c5 - 1; c6 >= 0; c6--) {
								for (int c7 = c6 - 1; c7 >= 0; c7--) {
									for (int c8 = c7 - 1; c8 >= 0; c8--) {
										count += count(c, dataList) * count(c2, dataList) * count(c3, dataList) * count(c4, dataList) * count(c5, dataList) * count(c6, dataList) * count(c7, dataList) * count(c8, dataList);
//										double[] dc = getDouble(map(c, dataList));
//										double[] dc2 = getDouble(map(c2, dataList));
//										double[] dc3 = getDouble(map(c3, dataList));
//										double[] dc4 = getDouble(map(c4, dataList));
//										double[] dc5 = getDouble(map(c5, dataList));
//										double[] dc6 = getDouble(map(c6, dataList));
//										double[] dc7 = getDouble(map(c7, dataList));
//										double[] dc8 = getDouble(map(c8, dataList));
//										maxAmout += dc[1] * dc2[1] * dc3[1] * dc4[1] * dc5[1] * dc6[1] * dc7[1] * dc8[0];
//										double min = dc[0] * dc2[0] * dc3[0] * dc4[0] * dc5[0] * dc6[0] * dc7[0] * dc8[1];
//										if (min < minAmout) {
//											minAmout = min;
//										}
									}
								}
							}
						}
					}
				}
			}
		}
		return new double[] { count, minAmout, maxAmout };
	}

	/** 获得最大赔率和最小赔率
	 * @param map
	 * @return 最小赔率,最大赔率 */
	private double[] getDouble(Map<String, Object> map) {
		List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				SoccerContent sc1 = (SoccerContent) o1.getValue();
				SoccerContent sc2 = (SoccerContent) o2.getValue();
				return Double.parseDouble(sc1.getSpValue()) > Double.parseDouble(sc2.getSpValue()) ? 1 : -1;
			}
		});
		SoccerContent minSc = (SoccerContent) infoIds.get(0).getValue();
		SoccerContent maxSc = (SoccerContent) infoIds.get(infoIds.size() - 1).getValue();
		return new double[] { Double.parseDouble(minSc.getSpValue()), Double.parseDouble(maxSc.getSpValue()), };
	}
}
