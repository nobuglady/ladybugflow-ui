/*
 * Copyright (c) 2021-present, NoBugLady Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.github.nobuglady.network.ui.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author NoBugLady
 *
 */
public class DateUtil {

	public static final String FMT_YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String dateToString(Date date, String fmt) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}
}
