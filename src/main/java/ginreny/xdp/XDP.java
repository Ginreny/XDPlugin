package ginreny.xdp;

import ginreny.xdp.managers.HomeManager;
import ginreny.xdp.managers.WelcomeManager;
import ginreny.xdp.command.HomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class XDP extends JavaPlugin
{
    public static final WelcomeManager welcomeManager = new WelcomeManager();
    public static final HomeCommand homeCommand = new HomeCommand();

    public static final HomeManager homeManager = new HomeManager();

    private static Plugin INSTANCE;
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        INSTANCE = this;

        Bukkit.getPluginManager().registerEvents(welcomeManager, this);
        Objects.requireNonNull(Bukkit.getPluginCommand("xdhome")).setExecutor(homeCommand);

        homeManager.initHomeMap();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        homeManager.saveData();
    }

    public static Plugin getInstance()
    {
        return INSTANCE;
    }
}
