package edu.bilkent.bilbilet.exception;

public class TicketNotAvailableException extends BaseException {
    public TicketNotAvailableException(String message) {
        super(message);
    }
    
    public TicketNotAvailableException() {
        super("Ticket is not available :(");
    }
}
