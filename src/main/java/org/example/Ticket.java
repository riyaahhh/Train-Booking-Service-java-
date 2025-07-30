package org.example;

import java.util.Date;

public class Ticket {
    public user user1;
    private String ticketid;
    private String userid;
    private String source;
    private String destination;
    private Date dateOfDeparture;
    private Train trainToBeBoarded;
    public Ticket(String ticketid, String userid, String source, String destination, Date dateOfDeparture) {
        this.ticketid = ticketid;
        this.userid = userid;
        this.source = source;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.trainToBeBoarded = null;
    }
    public Ticket() {}
    public String getTicketid() {
        return ticketid;
    }
    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }
    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }
    public Train getTrainToBeBoarded() {
        return trainToBeBoarded;
    }
    public void setTrainToBeBoarded(Train trainToBeBoarded) {
        this.trainToBeBoarded = trainToBeBoarded;
    }
}
