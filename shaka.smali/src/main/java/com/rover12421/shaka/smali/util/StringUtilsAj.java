/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rover12421.shaka.smali.util;

import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.ShakaStringUtil;
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

        writer.write(ShakaStringUtil.escaped(c));
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
            writer.write(ShakaStringUtil.escaped(c));
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
            sb.append(ShakaStringUtil.escaped(c));
        }

        return sb.toString();
    }
}
