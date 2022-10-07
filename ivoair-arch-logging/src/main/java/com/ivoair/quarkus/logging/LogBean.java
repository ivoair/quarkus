package com.ivoair.quarkus.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class LogBean {

	public static final String CORRELATOR_PARAM = "CORRELATOR_ID";
	public static final String SERVICE_PARAM = "SERVICE_NAME";
	public static final String DATE_PARAM = "DATE_TIME";

	public static final String SEPARATOR = "|";

	private Map<String, String> logFields;

	public LogBean() {
		this.logFields = new LinkedHashMap<String, String>();
		this.logFields.put(DATE_PARAM, new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss-SSS").format(new Date()));
	}
	
	public String getAll() {

		String returnString = getCorrelator();
		for (String key : this.logFields.keySet()) {
			if (CORRELATOR_PARAM.equals(key) || SERVICE_PARAM.equals(key) || CORRELATOR_PARAM.equals(key)) {
				continue;
            }
			returnString += SEPARATOR + key + "=" + this.logFields.get(key);
        }
		return returnString;
	}

	public String getCorrelator() {
		if (!this.logFields.containsKey(DATE_PARAM)) {
			this.logFields.put(DATE_PARAM, new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss-SSS").format(new Date()));
		}

		// Order: CORRELATOR|SERVICE|DATE|...
		return this.logFields.get(CORRELATOR_PARAM) + SEPARATOR + this.logFields.get(SERVICE_PARAM) + SEPARATOR
				+ this.logFields.get(DATE_PARAM);
	}

}
