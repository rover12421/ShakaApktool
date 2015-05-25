package com.rover12421.shaka.lib;

/**
 * Created by rover12421 on 5/25/15.
 */
public class ShakaStringUtil {
    public static String escaped(char c) throws Throwable {
        String ret;
        int type = Character.getType(c);
        if (Character.isJavaIdentifierPart(c)
                || ((c >= ' ') && (c < 0x7f))
                || (type >= 20 && type <= 28)   //[20-24]是所有标点PUNCTUATION,[25-28]是所有符号SYMBOL
                ) {
            ret = String.valueOf(c);

            if ((c == '\'') || (c == '\"') || (c == '\\')) {
                ret = "\\" + ret;
            }
        } else {
            switch (c) {
                case '\n':
                    ret = "\\n";
                    break;
                case '\r':
                    ret = "\\r";
                    break;
                case '\t':
                    ret = "\\t";
                    break;
                default:
                    ret = "\\u"
                            + Character.forDigit(c >> 12, 16)
                            + Character.forDigit((c >> 8) & 0x0f, 16)
                            + Character.forDigit((c >> 4) & 0x0f, 16)
                            + Character.forDigit(c & 0x0f, 16);
                    break;
            }
        }

        return ret;
    }

    public static String escaped(String str) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(escaped(str.charAt(i)));
        }
        return sb.toString();
    }
}
