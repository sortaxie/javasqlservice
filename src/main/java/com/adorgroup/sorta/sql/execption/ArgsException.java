package com.adorgroup.sorta.sql.execption;

/**
 * 参数错误异常
 */
public class ArgsException extends RuntimeException {

	public ArgsException(String message) {
		super(message);
	}

	public ArgsException(String message, Throwable cause) {
		super(message, cause);
	}

}
