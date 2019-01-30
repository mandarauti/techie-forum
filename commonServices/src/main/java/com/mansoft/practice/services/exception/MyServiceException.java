/**
 * 
 */
package com.mansoft.practice.services.exception;

/**
 * @author mandar_auti
 *
 */
public class MyServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyServiceException(String s)
	{
		super(s);
	}
	
	public MyServiceException(Throwable exception)
	{
		super(exception);
	}

}
