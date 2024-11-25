package org.example.cx;

import java.util.UUID;

public class cxInformation {
    private String name;
    private UUID cxUuid;
    private String mobileNumber; // Ideally mobileNumber should be an object
    private String emailId; // same with emailId
    private String[] paymentMethod;
    private  currentRide;

    private Boolean isOnRide;

    public String getName() {
        return name;
    }

    public Boolean getOnRide() {
        return isOnRide;
    }

    public cxInformation(String name, String mblNumber, String eId, String[] paymentMethod)
    {
        this.name = name;
        this.mobileNumber = mblNumber;
        this.emailId = eId;
        this.paymentMethod = paymentMethod;
        this.isOnRide = false;
    }

    public void setOnRide(Boolean onRide) {
        isOnRide = onRide;
    }
}
