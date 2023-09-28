package ginreny.xdp.util;

import org.bukkit.Location;

import java.util.*;

public class HomeList
{
    private Map<String, Location> homeMap = new HashMap<>();

    public Set<String> getHomeNames()
    {
        return homeMap.keySet();
    }

    public void setHome(String homeName, Location homeLocation)
    {
        homeMap.put(homeName, homeLocation);
    }

    public Boolean deleteHome(String homeName)
    {
        if(!homeMap.containsKey(homeName)) return false;

        homeMap.remove(homeName);
        return true;
    }

    public Location getHome(String homeName)
    {
        return homeMap.getOrDefault(homeName, null);
    }
}
