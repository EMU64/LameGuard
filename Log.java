package com.lameguard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static final Writer LOG = getWriter();
    private static final Date NOW = new Date();

    static Writer getWriter() {
        try {
            return new BufferedWriter(new FileWriter(Config.LAMEGUARD_LOG_FILE, true));
        } catch (IOException e) {
            e.printStackTrace();
            return new PrintWriter(System.out);
        }
    }

    static void error(String msg, Throwable t) {
        error(msg);
        t.printStackTrace();
    }

    static void error(String msg) {
        System.err.println("LAMEGUARD - " + msg);
    }

    static void info(String msg) {
        System.out.println("LAMEGUARD - " + msg);
    }

    static void warn(String msg) {
        log(msg);
    }

    static void log(String msg) {
        StringBuilder output = new StringBuilder();
        synchronized (NOW) {
            NOW.setTime(System.currentTimeMillis());
            output.append('[');
            output.append(DATE_FORMAT.format(NOW));
            output.append(']');
            output.append(' ');
            output.append(msg);
            output.append("\n");
            try {
                LOG.append(output);
                LOG.flush();
            } catch (IOException e) {
            }
        }
    }
}
