package org.example;

import org.example.App;
import org.example.cx.cxInformation;
import org.example.drivers.PremiumVehicle;

public class UberRegion {

    private String zoneCode;
    private long startLat;
    private long startLong;
    private long endLat;
    private long endLong;
    private RegularVehicleAgg regularVehicleAgg;
    private PremiumVehicleAgg premiumVehicleAgg;
    private cxInformation[] CxRequestForRegular;
    private cxInformation[] CxRequestForPremium;

    public UberRegion(long startLat, long startLong, long endLat, long endLong)
    {
        this.endLong = endLong;
        this.startLat = startLat;
        this.endLat = endLat;
        this.startLong = startLong;
        this.premiumVehicleAgg = new PremiumVehicleAgg();
        this.regularVehicleAgg = new RegularVehicleAgg();
    }



}
