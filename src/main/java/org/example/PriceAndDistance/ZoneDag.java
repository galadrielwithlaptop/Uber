package org.example.PriceAndDistance;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZoneDag {
    private Map<UUID, zone> zones;
    private boolean latestDag;
    private Map<UUID , HashMap<UUID, Integer>> dist;
    private Lock zoneLock;
    private static ZoneDag instance;
    private IUpdatePrice updatePrice;

    private ZoneDag()
    {
        latestDag = false;
        dist = new HashMap<UUID, HashMap<UUID, Integer>>();
        zoneLock = new ReentrantLock();
        updatePrice = calculatePriceAndDistanceServer.getInstance();
    }

    public static ZoneDag getInstance()
    {
        if (instance == null)
        {
            instance = new ZoneDag();
        }
        return instance;
    }


    public zone addZone(UUID Uuid)
    {
        if (zones.containsKey(Uuid))
        {
            return null;
        }
        var newZone = new zone(Uuid);
        zoneLock.lock();
        try
        {
            zones.put(Uuid, newZone);
            latestDag = false;
        }
        finally {
            zoneLock.unlock();
        }
        return newZone;
    }

    public void addConn(zone from, zone to)
    {
        if (zones.containsKey(from) && zones.containsKey(to))
        {
            from.addConn(to);
            to.addConn(from);
        }
        latestDag = false;
    }

    public void createDag()
    {
        if (latestDag == false) {
            zoneLock.lock();
            try {
                for (Map.Entry<UUID, zone> entry : zones.entrySet()) {
                    dist.put(entry.getKey(), distanceCalculator(entry.getKey()));
                }
            }
            finally {
                zoneLock.unlock();
            }
        }
        latestDag = true;

    }

    private HashMap<UUID, Integer> distanceCalculator(UUID from)
    {
        HashMap<UUID, Integer> shortestDist = new LinkedHashMap<>(zones.size());
        HashMap<UUID, Integer> tempMap = new LinkedHashMap<>();
        tempMap.put(from,0);
        shortestDist.put(from,0);
        Integer dist = 0;
        while(tempMap.size() != 0)
        {
            dist+=1;
            UUID firstKey = tempMap.keySet().iterator().next();
            var allConn = zones.get(firstKey).getConn();
            for (zone toZone : allConn)
            {
                if (!shortestDist.containsKey(toZone.getZoneUuid()))
                {
                    tempMap.put(toZone.getZoneUuid(), dist);
                    shortestDist.put(toZone.getZoneUuid(), dist );
                }
            }
            tempMap.remove(firstKey);
        }
        return shortestDist;
    }
}

