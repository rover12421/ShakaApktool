package com.rover12421.shaka.apktool.AspjectJ.apktool_util;

import com.rover12421.shaka.apktool.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.Writer;

/**
 * Created by rover12421 on 4/10/15.
 */
@Aspect
public class StringUtilsAj {
    @Around("execution(* org.jf.util.StringUtils.writeEscapedChar(..))" +
            "&& args(writer, c)")
    public void writeEscapedChar(ProceedingJoinPoint joinPoint, Writer writer, char c) throws Throwable {

        if (!ShakaDecodeOption.getInstance().isShowMoreRecognizableCharacters()) {
            joinPoint.proceed(joinPoint.getArgs());
            return;
        }

        if (Character.isJavaIdentifierPart(c)) {
            writer.write(c);
            return;
        }

        if ((c >= ' ') && (c < 0x7f)) {
            if ((c == '\'') || (c == '\"') || (c == '\\')) {
                writer.write('\\');
            }
            writer.write(c);
            return;
        } else if (c <= 0x7f) {
            switch (c) {
                case '\n': writer.write("\\n"); return;
                case '\r': writer.write("\\r"); return;
                case '\t': writer.write("\\t"); return;
            }
        }

        writer.write("\\u");
        writer.write(Character.forDigit(c >> 12, 16));
        writer.write(Character.forDigit((c >> 8) & 0x0f, 16));
        writer.write(Character.forDigit((c >> 4) & 0x0f, 16));
        writer.write(Character.forDigit(c & 0x0f, 16));
    }

    @Around("execution(* org.jf.util.StringUtils.writeEscapedString(..))" +
            "&& args(writer, value)")
    public void writeEscapedString(ProceedingJoinPoint joinPoint, Writer writer, String value) throws Throwable {
        if (!ShakaDecodeOption.getInstance().isShowMoreRecognizableCharacters()) {
            joinPoint.proceed(joinPoint.getArgs());
            return;
        }

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            if (Character.isJavaIdentifierPart(c)) {
                writer.write(c);
                continue;
            }

            if ((c >= ' ') && (c < 0x7f)) {
                if ((c == '\'') || (c == '\"') || (c == '\\')) {
                    writer.write('\\');
                }
                writer.write(c);
                continue;
            } else if (c <= 0x7f) {
                switch (c) {
                    case '\n': writer.write("\\n"); continue;
                    case '\r': writer.write("\\r"); continue;
                    case '\t': writer.write("\\t"); continue;
                }
            }

            writer.write("\\u");
            writer.write(Character.forDigit(c >> 12, 16));
            writer.write(Character.forDigit((c >> 8) & 0x0f, 16));
            writer.write(Character.forDigit((c >> 4) & 0x0f, 16));
            writer.write(Character.forDigit(c & 0x0f, 16));
        }
    }

    @Around("execution(* org.jf.util.StringUtils.escapeString(..))" +
            "&& args(value)")
    public String escapeString(ProceedingJoinPoint joinPoint, String value) throws Throwable {

        if (!ShakaDecodeOption.getInstance().isShowMoreRecognizableCharacters()) {
            return (String) joinPoint.proceed(joinPoint.getArgs());
        }

        int len = value.length();
        StringBuilder sb = new StringBuilder(len * 3 / 2);

        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);

            if (Character.isJavaIdentifierPart(c)) {
                sb.append(c);
                continue;
            }

            if ((c >= ' ') && (c < 0x7f)) {
                if ((c == '\'') || (c == '\"') || (c == '\\')) {
                    sb.append('\\');
                }
                sb.append(c);
                continue;
            } else if (c <= 0x7f) {
                switch (c) {
                    case '\n': sb.append("\\n"); continue;
                    case '\r': sb.append("\\r"); continue;
                    case '\t': sb.append("\\t"); continue;
                }
            }

            sb.append("\\u");
            sb.append(Character.forDigit(c >> 12, 16));
            sb.append(Character.forDigit((c >> 8) & 0x0f, 16));
            sb.append(Character.forDigit((c >> 4) & 0x0f, 16));
            sb.append(Character.forDigit(c & 0x0f, 16));
        }

        return sb.toString();
    }
}
