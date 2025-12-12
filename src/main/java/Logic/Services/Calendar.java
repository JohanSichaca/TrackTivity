package Logic.Services;

import java.time.ZonedDateTime;

/**
 * Represents a scheduled calendar event with a client and service number.
 */
public class Calendar {

    private ZonedDateTime date;
    private String clientName;
    private Integer serviceNo;

    public Calendar(ZonedDateTime date, String clientName, Integer serviceNo) {
        this.date = date;
        this.clientName = clientName;
        this.serviceNo = serviceNo;
    }

    /** Returns the event date and time. */
    public ZonedDateTime getDate() {
        return date;
    }

    /** Sets the event date and time. */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /** Returns the client name associated with the event. */
    public String getClientName() {
        return clientName;
    }

    /** Sets the client name associated with the event. */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /** Returns the service number of the event. */
    public Integer getServiceNo() {
        return serviceNo;
    }

    /** Sets the service number of the event. */
    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", serviceNo=" + serviceNo +
                '}';
    }
}
