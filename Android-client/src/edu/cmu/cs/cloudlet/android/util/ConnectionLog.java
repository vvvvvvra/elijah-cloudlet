package edu.cmu.cs.cloudlet.android.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

public class ConnectionLog {

	private String mPath;
	private Writer mWriter;

	private static final String TAG = "krha";
	private static final SimpleDateFormat TIMESTAMP_FMT = new SimpleDateFormat("[HH:mm:ss] ");

	public ConnectionLog(String logPath) throws IOException {
		open(logPath);
	}

	protected void open(String logPath) throws IOException {
		File f = new File(logPath+ "." + getTodayString());
		mPath = f.getAbsolutePath();
		mWriter = new BufferedWriter(new FileWriter(mPath), 2048);

		println("Opened log.");
	}

	private static String getTodayString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmssSSS");
		return df.format(new Date());
	}

	public String getPath() {
		return mPath;
	}

	public void println(String message) throws IOException {
		mWriter.write(TIMESTAMP_FMT.format(new Date()));
		mWriter.write(message);
		mWriter.write('\n');
		mWriter.flush();
		Log.d(TAG, message);
	}

	public void close() throws IOException {
		mWriter.close();
	}

}
