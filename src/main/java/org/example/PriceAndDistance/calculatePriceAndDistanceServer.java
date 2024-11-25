package org.example.PriceAndDistance;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class calculatePriceAndDistanceServer implements IUpdatePrice, IgetPrice{

    private static calculatePriceAndDistanceServer instance;
    private static Map<UUID , HashMap<UUID, Integer>> dist;

    private calculatePriceAndDistanceServer()
    {
        dist = null;
    }

    public static calculatePriceAndDistanceServer getInstance()
    {
        if (instance == null)
        {
            instance = new calculatePriceAndDistanceServer();
        }
        return instance;
    }

    public static double calculatePrice(UUID from, UUID to)
    {
        // Dummy function to calculate Price
        Integer distance = getDistance(from, to);
        double base = 25.0;
        if (distance < 3.0 && distance <1.0)
        {
            return base + (2.5*distance);
        }
        else if (distance >= 3.0)
        {
            return base + (2.0 * distance);
        }
        else
            return base;
    }

    @Override
    public void updateDistance(Map<UUID , HashMap<UUID, Integer>> distanceToBeUpdatedWith)
    {
        dist = distanceToBeUpdatedWith;
    }

    public Integer getDistance(UUID from, UUID to)
    {
        if (dist.containsKey(from))
        {
            if (dist.get(from).containsKey(to)){
                return dist.get(from).get(to);
            }
        }
        return -1;
    }
}
