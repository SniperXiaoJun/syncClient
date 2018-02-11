package com.sync.common;

/**
 * TargetData
 * 
 * @author sasou <admin@php-gene.com> web:http://www.php-gene.com/
 * @version 1.0.0
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * 写日志 写logString字符串到./log目录下的文件中
 * 
 * @param logString
 *            日志字符串
 * @author tower
 */
public class WriteLog {

	public static void write(String type, String logString) {
		// 日志路径
		String current = "/logs/";
		try {
			String logFilePathName = null;
			Calendar cd = Calendar.getInstance();
			int year = cd.get(Calendar.YEAR);
			String month = addZero(cd.get(Calendar.MONTH) + 1);
			String day = addZero(cd.get(Calendar.DAY_OF_MONTH));
			String hour = addZero(cd.get(Calendar.HOUR_OF_DAY));
			String min = addZero(cd.get(Calendar.MINUTE));
			String sec = addZero(cd.get(Calendar.SECOND));
			current += year + "-" + month + "-" + day + "/";

			File fileParentDir = new File(current);
			if (!fileParentDir.exists()) {
				fileParentDir.mkdir();
			}

			logFilePathName = current + type + ".log";

			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(logFilePathName, true), "GB2312"));

			String time = "[" + year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec + "] ";
			printWriter.println(time + logString + "\n\n");
			printWriter.flush();
			printWriter.close();
			if (GetProperties.system_debug > 0) {
				System.out.println(logString);
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 整数i小于10则前面补0
	 * 
	 * @param i
	 * @return
	 * @author tower
	 */
	public static String addZero(int i) {
		if (i < 10) {
			String tmpString = "0" + i;
			return tmpString;
		} else {
			return String.valueOf(i);
		}
	}

}