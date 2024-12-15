package com.neomaxer.neospend.utils;

public class LogoUtils {
	
	
	private static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];

	public static Byte[] toByteArray(byte[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_BYTE_OBJECT_ARRAY;
		} else {
			Byte[] result = new Byte[array.length];

			for (int i = 0; i < array.length; ++i) {
				result[i] = Byte.valueOf(array[i]);
			}

			return result;
		}

	}

}
