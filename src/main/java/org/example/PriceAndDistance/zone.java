package org.example.PriceAndDistance;

import java.util.*;

public class zone implements IZone{
    private UUID zoneUuid;
    private Set<zone> conn;

    public zone (UUID Uuid)
    {
        this.zoneUuid = Uuid;
        conn = new HashSet<zone>();
    }

    public UUID getZoneUuid() {
        return zoneUuid;
    }

    public void addConn(zone adjZone)
    {
        conn.add(adjZone);
    }

    public Set<zone> getConn() {
        return conn;
    }
}
