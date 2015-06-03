package com.librarymanagementsystem.exceptions;



public class BookNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException() {
	}

	public BookNotFoundException(String message) {
		super(message);
	}
}
