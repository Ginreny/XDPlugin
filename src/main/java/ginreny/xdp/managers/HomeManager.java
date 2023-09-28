package ginreny.xdp.managers;

import ginreny.xdp.XDP;
import ginreny.xdp.util.HomeList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeManager
{
    private Map<UUID, HomeList> homeListMap = new HashMap<>();
    private File dataFile;
    private HomeList getHomeListOfAPlayer(Player p)
    {
        if(homeListMap.get(p.getUniqueId()) == null)
            homeListMap.put(p.getUniqueId(), new HomeList());

        return homeListMap.get(p.getUniqueId());
    }

    public void addHome(Player p, String homeName, Location homeLocation)
    {
        getHomeListOfAPlayer(p).setHome(homeName, homeLocation);
        p.sendMessage("增加成功!");
    }

    public void deleteHome(Player p, String homeName)
    {
        if(getHomeListOfAPlayer(p).deleteHome(homeName))
        {
            p.sendMessage("删除成功!");
        }

        p.sendMessage("删除失败!");
    }

    public void loadData()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile)))
        {
            homeListMap =  (Map<UUID, HomeList>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            homeListMap = new HashMap<>();
        }
    }

    public void saveData()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile)))
        {
            oos.writeObject(homeListMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initHomeMap()
    {
        dataFile = new File(XDP.getInstance().getDataFolder(), "HomeMapData.xdh");

        if (!dataFile.exists())
        {
            try
            {
                XDP.getInstance().getDataFolder().mkdirs();
                dataFile.createNewFile();
                homeListMap = new HashMap<>();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            loadData();
        }
    }

    public void showHomeList(Player p)
    {
        int it = 0;

        p.sendMessage(p.getName() + "拥有家园如下:");
        for(String homeName : getHomeListOfAPlayer(p).getHomeNames())
        {
            it++;
            p.sendMessage(" " + it + ". " + homeName);
        }
    }

    public void tpPlayerHome(Player p, String homeName)
    {
        HomeList homeList = getHomeListOfAPlayer(p);

        if(homeList.getHome(homeName) == null)
            p.sendMessage("传送失败，不存在的家!");

        p.teleport(homeList.getHome(homeName));
        p.sendMessage("传送成功!");
    }

}
