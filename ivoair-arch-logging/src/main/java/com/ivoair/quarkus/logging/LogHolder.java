package com.ivoair.quarkus.logging;

public final class LogHolder {

	private final static InheritableThreadLocal<LogBean> logBeanTL = new InheritableThreadLocal<LogBean>();

	public static String getAllLog() {
		validate();
		return logBeanTL.get().getAll();
	}

	public static String getField(String key) {
		validate();
		return logBeanTL.get().getLogFields().get(key);
	}

	public static void set(LogBean logbean) {
		validate();
		logBeanTL.set(logbean);
	}

	public static LogBean get() {
		validate();
		return logBeanTL.get();
	}

	public static void remove() {
		validate();
		logBeanTL.remove();
	}

	private static void validate() {
		if (logBeanTL.get() == null) {
			logBeanTL.set(new LogBean());
		}
	}

	private LogHolder() {
	}
}
