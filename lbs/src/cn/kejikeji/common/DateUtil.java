package cn.kejikeji.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Date utilities.
 * 
 */
public class DateUtil {
	private static List<DateFormat> formats = new ArrayList<DateFormat>();
	
	private static SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	static {
		// alternative formats
		formats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		formats.add(df);

		formats.add(new SimpleDateFormat("yyyy-MM-dd"));
		// ISO formats
		formats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
		formats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
		formats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
		formats.add(DateFormat.getDateTimeInstance());

		// XPDL examples format
		formats.add(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US));

		// Only date, no time
		formats.add(DateFormat.getDateInstance());
	}

	/**
	 * can't be instantiated.
	 * 
	 */
	private DateUtil() {

	}

	/**
	 * Parse the specified date String.
	 * 
	 * @param dateString
	 *            The date String
	 * @return The Date object
	 */
	public synchronized static Date parse(String dateString) {
		if (dateString == null) {
			return null;
		}

		for (DateFormat format : formats) {
			try {
				return format.parse(dateString);
			} catch (ParseException e) {
				// do nothing
			}
		}
		return null;
	}

	/**
	 * 将某个时间范围按天进行切分，未满一天的按一天算
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> splitByDay(Date startDate, Date endDate) {
		List<Date> dayList = new ArrayList<Date>();
		String startDateStr = DateFormatUtils.format(startDate, "yyyy-MM-dd");
		Date startDate1 = DateUtil.parse(startDateStr);

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate1);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(startDate1);
		tempCal.add(Calendar.DAY_OF_MONTH, 1);

		while (tempCal.before(endCal)) {
			dayList.add(startCal.getTime());
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			tempCal.add(Calendar.DAY_OF_MONTH, 1);
		}

		dayList.add(startCal.getTime());

		return dayList;
	}
	
	/**
	 * 将某个时间范围按月进行切分，未满一月的按一月算
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> splitByMonth(Date startDate, Date endDate) {
		List<Date> dayList = new ArrayList<Date>();
		String startDateStr = DateFormatUtils.format(startDate, "yyyy-MM-01");
		Date startDate1 = DateUtil.parse(startDateStr);

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate1);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(startDate1);
		tempCal.add(Calendar.MONTH, 1);

		while (tempCal.before(endCal)) {
			dayList.add(startCal.getTime());
			startCal.add(Calendar.MONTH, 1);
			tempCal.add(Calendar.MONTH, 1);
		}

		dayList.add(startCal.getTime());

		return dayList;
	}

	/**
	 * 判断两个时间是否在同一天内
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		String date1Str = DateFormatUtils.format(date1, "yyyy-MM-dd");
		String date2Str = DateFormatUtils.format(date2, "yyyy-MM-dd");
		if (date1Str.equals(date2Str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个时间是否在同一个月内
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个时间是否在同一季度里
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameQuarter(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		if (((month1 >= Calendar.JANUARY && month1 <= Calendar.MARCH) && (month2 >= Calendar.JANUARY && month2 <= Calendar.MARCH))
				|| ((month1 >= Calendar.APRIL && month1 <= Calendar.JUNE) && (month2 >= Calendar.APRIL && month2 <= Calendar.JUNE))
				|| ((month1 >= Calendar.JULY && month1 <= Calendar.SEPTEMBER) && (month2 >= Calendar.JULY && month2 <= Calendar.SEPTEMBER))
				|| ((month1 >= Calendar.OCTOBER && month1 <= Calendar.DECEMBER) && (month2 >= Calendar.OCTOBER && month2 <= Calendar.DECEMBER))) {
			return true;
		}
		return false;
	}

	public static String format(Date time) {

		return df.format(time);
	}
}