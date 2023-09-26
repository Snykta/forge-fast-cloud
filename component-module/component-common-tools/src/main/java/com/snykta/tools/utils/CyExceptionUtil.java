package com.snykta.tools.utils;

import cn.hutool.core.exceptions.ExceptionUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CyExceptionUtil extends ExceptionUtil {

    public static String toString(Exception ex) {
        String ret = "";
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PrintStream pout = new PrintStream(out);
            ex.printStackTrace(pout);
            ret = new String(out.toByteArray(), "UTF-8");
            pout.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        return ret;
    }


    public static String toString(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    public static String getStackMsg(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        int length = stackArray.length > 80 ? 80 : stackArray.length;
        for (int i = 0; i < length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

}
