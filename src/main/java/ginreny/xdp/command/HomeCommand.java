package ginreny.xdp.command;

import ginreny.xdp.XDP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class HomeCommand implements CommandExecutor
{

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if(args.length == 0) return false;

        if(!(sender instanceof Player p)) return false;

        switch (args[0])
        {
            case "add":
            case "ad":
            case "a":
                XDP.homeManager.addHome(p, args[1], p.getLocation());
                break;

            case "delete":
            case "del":
            case "d":
                XDP.homeManager.deleteHome(p, args[1]);
                break;

            case "tp":
                XDP.homeManager.tpPlayerHome(p, args[1]);
                break;
        }

        return true;
    }
}
