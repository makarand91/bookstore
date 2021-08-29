package com.sample.bookstore.utils;

import org.apache.commons.lang3.StringUtils;

import com.sample.bookstore.model.BookEntry;

public class ValidationUtils {

	public static boolean isValidISBN(BookEntry book) {
		if (book.getIsbn() != null && book.getIsbn().length() == 13 && StringUtils.isNumeric(book.getIsbn()))

			return true;

		return false;
	}

}
