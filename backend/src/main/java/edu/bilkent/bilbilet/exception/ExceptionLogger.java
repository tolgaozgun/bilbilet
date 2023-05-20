package edu.bilkent.bilbilet.exception;

public class ExceptionLogger {
    public static String log(Exception e) {
        String msg = "\n[LOG] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        msg += "\n";
        return msg;
    }

    public static String warn(Exception e) {
        String msg = "\n[WARNING] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        msg += "\n";
        return msg;
    }

    public static String error(Exception e) {
        String msg = "\n[ERROR] ";
        msg += (e.getMessage().equals(e.getLocalizedMessage())) ? e.getLocalizedMessage() : e.getLocalizedMessage() + ": " + e.getMessage();
        msg += "\n";
        return msg;
    }
}