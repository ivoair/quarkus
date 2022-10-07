package com.ivoair.quarkus.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 *
 * Parent exception. It supports message revolver using property files.
 * 
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 8821424673353222955L;

	/** Code to resolve the message */
	@Getter
	private final String code;

	/** Array of arguments for the message */
	private final Object[] arguments;

	/** Default message */
	private final String defaultMessage;
	
	public static final AppException from(Throwable t) {
		return new AppException(AppErrorCode.ARQ_0001.getCode(), t);
	}

	/**
	 * Create an instance of AppException without arguments, default message and
	 * cause
	 * 
	 * @param code code to resolve the message
	 */
	public AppException(String code) {
		this(code, (Object[]) null);
	}
	
	/**
	 * Create and instance of AppException without default message and cause
	 * 
	 * @param code      code to resolve the message
	 * @param arguments array of arguments used to resolve the message
	 */
	public AppException(String code, Object[] arguments) {
		super();

		this.code = code;
		this.arguments = ArrayUtils.nullToEmpty(arguments);
		this.defaultMessage = null;
	}

	/**
	 * Create an instance of AppException without arguments and default message
	 * 
	 * @param code  code to resolve the message
	 * @param cause cause
	 */
	public AppException(String code, Throwable cause) {
		this(code, (Object[]) null, cause);
	}

	/**
	 * Create an instance of AppException without default message
	 * 
	 * @param code      code to resolve the message
	 * @param arguments array of arguments used to resolve the message
	 * @param cause     cause
	 */
	public AppException(String code, Object[] arguments, Throwable cause) {
		super(cause);

		this.code = code;
		this.arguments = ArrayUtils.nullToEmpty(arguments);
		this.defaultMessage = null;
	}

	/**
	 * Create an instance of AppException without arguments and cause
	 * 
	 * @param code           code to resolve the message
	 * @param defaultMessage default message included the detailed message of the
	 *                       exception
	 * @param arguments      array of arguments used to resolve the message
	 */
	public AppException(String code, String defaultMessage) {
		this(code, (Object[]) null, defaultMessage);
	}

	/**
	 * Create an instance of AppException without cause
	 * 
	 * @param code           code to resolve the message
	 * @param arguments      array of arguments used to resolve the message
	 * @param defaultMessage default message included the detailed message of the
	 *                       exception
	 */
	public AppException(String code, Object[] arguments, String defaultMessage) {
		super(defaultMessage);

		this.code = code;
		this.arguments = ArrayUtils.nullToEmpty(arguments);
		this.defaultMessage = StringUtils.defaultString(defaultMessage);
	}

	/**
	 * Create an instance of AppException without arguments
	 * 
	 * @param code           code to resolve the message
	 * @param defaultMessage default message included the detailed message of the
	 *                       exception
	 * @param cause          cause
	 */
	public AppException(String code, String defaultMessage, Throwable cause) {
		this(code, (Object[]) null, defaultMessage, cause);
	}

	/**
	 * Create an instance of AppException
	 * 
	 * @param code           code to resolve the message
	 * @param defaultMessage default message included the detailed message of the
	 *                       exception
	 * @param cause          cause
	 * @param arguments      array of arguments used to resolve the message
	 */
	public AppException(String code, Object[] arguments, String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);

		this.code = code;
		this.arguments = ArrayUtils.nullToEmpty(arguments);
		this.defaultMessage = StringUtils.defaultString(defaultMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {

		final String message = this.getLocalizedMessage();
		return (message != null) ? (this.getClass().getName() + ": " + message)
				: (this.getClass().getName() + ": " + this.resolvableToString());
	}

	/**
	 * Create a representation in String format for AppException that includes code,
	 * arguments and default message.
	 */
	protected final String resolvableToString() {

		StringBuilder sb = new StringBuilder().append("code [").append(this.code).append("]");

		if (ArrayUtils.isNotEmpty(this.arguments)) {
			sb.append("; arguments [").append(StringUtils.join(this.arguments, ",")).append("]");
		}
		if (StringUtils.isNotBlank(this.defaultMessage)) {
			sb.append("; default message [").append(this.defaultMessage).append("]");
		}
		return sb.toString();
	}

}
