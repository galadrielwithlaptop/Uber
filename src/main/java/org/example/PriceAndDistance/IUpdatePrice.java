package org.example.PriceAndDistance;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface IUpdatePrice {

    public void updateDistance(Map<UUID , HashMap<UUID, Integer>> distanceToBeUpdatedWith);
}
