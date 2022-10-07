package com.ivoair.quarkus.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 *
 * Enum with all available errors. <br/>
 *
 * Format: [3 letters]-[4 numbers] <br/>
 *
 * <ul>
 * <li>ARQ: General errors </li>
 * <li>PER: Persistence errors </li>
 * </ul>
 *
 */
public enum AppErrorCode {

	/********* GENERIC EXCEPTIONS ************/
	ARQ_0001("ARQ_0001", "Unexpected error"),
	ARQ_0002("ARQ_0002", "Unexpected Feign error"),

	/********* PERSISTENCE EXCEPTIONS ************/
	PER_0001("PER_0001", "Generic Persistence error"),
	PER_0002("PER_0002", "Referential Integrity error"),
	PER_0003("PER_0003", "Duplicated Unique Constraint error"),
	PER_0004("PER_0004", "Violated Constraint error")	
	;


	@Getter
	private final String code;
	@Getter
	private final String description;

	private static final Map<String, AppErrorCode> map = new HashMap<>(values().length, 1);

	static {
		for (final AppErrorCode pocErrorCode : values()) {
			map.put(pocErrorCode.code, pocErrorCode);
		}
	}

	public static AppErrorCode of(final String code) {
		final AppErrorCode result = map.get(code);
		if (result == null) {
			throw new IllegalArgumentException("Invalid error code: " + code);
		}
		return result;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	private AppErrorCode(final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return this.code + ": " + this.description;
	}
}
