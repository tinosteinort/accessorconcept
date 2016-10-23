package de.tse.accessorconcept;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ByteArrayHelper {

    public static void writeInteger(final Integer value, final byte[] target, final int pos, final int length) {
        try {
            final byte[] temp = new byte[length];
            Arrays.fill(temp, " ".getBytes("UTF-8")[0]);
            final byte[] bytes = String.valueOf(value).getBytes("UTF-8");
            System.arraycopy(bytes, 0, temp, 0, Math.min(length, bytes.length));
            System.arraycopy(temp, 0, target, pos, length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Integer readInteger(final byte[] source, final int pos, final int length) {
        try {
            final String s = new String(source, pos, length, "UTF-8");
            return Integer.valueOf(s.trim());
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeString(final String value, final byte[] target, final int pos, final int length) {
        try {
            final byte[] temp = new byte[length];
            Arrays.fill(temp, " ".getBytes("UTF-8")[0]);
            final byte[] bytes = value.getBytes("UTF-8");
            System.arraycopy(bytes, 0, temp, 0, Math.min(length, bytes.length));
            System.arraycopy(temp, 0, target, pos, length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String readString(final byte[] source, final int pos, final int length) {
        try {
            return new String(source, pos, length, "UTF-8").trim();
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
