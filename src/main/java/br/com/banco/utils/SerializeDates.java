package br.com.banco.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class SerializeDates {
	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

	public static OffsetDateTime stringToOffSetDateTime(String date) {
		return OffsetDateTime.parse(date + "T00:00:00+03:00", SerializeDates.formatter);
	}
}
