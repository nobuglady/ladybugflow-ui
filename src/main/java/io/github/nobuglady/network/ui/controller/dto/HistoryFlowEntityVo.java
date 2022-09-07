package io.github.nobuglady.network.ui.controller.dto;

import java.util.Date;

import io.github.nobuglady.network.fw.persistance.entity.HistoryFlowEntity;
import io.github.nobuglady.network.fw.util.StringUtil;
import io.github.nobuglady.network.ui.util.DateUtil;

public class HistoryFlowEntityVo extends HistoryFlowEntity {

	public String getStartTimeVo() {
		return DateUtil.dateToString(getStartTime(), DateUtil.FMT_YYYYMMDD_HHMMSS);
	}

	public String getFinishTimeVo() {
		return DateUtil.dateToString(getFinishTime(), DateUtil.FMT_YYYYMMDD_HHMMSS);
	}

	public String getErrorTimeVo() {
		return DateUtil.dateToString(getErrorTime(), DateUtil.FMT_YYYYMMDD_HHMMSS);
	}

	public String getCreateTimeVo() {
		return DateUtil.dateToString(getCreateTime(), DateUtil.FMT_YYYYMMDD_HHMMSS);
	}

	public String getUpdateTimeVo() {
		return DateUtil.dateToString(getUpdateTime(), DateUtil.FMT_YYYYMMDD_HHMMSS);
	}

	public String getSpendTimeVo() {
		Date finishTime = getFinishTime();
		Date startTime = getStartTime();

		if (finishTime == null) {
			finishTime = new Date();
		}

		long spend = finishTime.getTime() - startTime.getTime();

		long oneDay = 1000 * 60 * 60 * 24;
		long oneHour = 1000 * 60 * 60;
		long oneMinute = 1000 * 60;
		long oneSecond = 1000;

		long days = spend / oneDay;
		long hours = (spend % oneDay) / oneHour;
		long minutes = ((spend % oneDay) % oneHour) / oneMinute;
		long seconds = (((spend % oneDay) % oneHour) % oneMinute) / oneSecond;
//		long millis  = (((spend % oneDay) % oneHour) % oneMinute) % oneSecond;

		return days + " days " + " " + StringUtil.padding(hours, "0", 2) + ":" + StringUtil.padding(minutes, "0", 2)
				+ ":" + StringUtil.padding(seconds, "0", 2);
	}

}
