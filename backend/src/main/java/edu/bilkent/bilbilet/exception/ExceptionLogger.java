package edu.bilkent.bilbilet.exception;

public class ExceptionLogger {
    public static String log(Exception e) {
        String msg = "[LOG] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        // msg += "\n";
        return msg;
    }

    public static String warn(Exception e) {
        String msg = "[WARNING] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        // msg += "\n";
        return msg;
    }

    public static String error(Exception e) {
        String msg = "[ERROR] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        // msg += "\n";
        return msg;
    }
}