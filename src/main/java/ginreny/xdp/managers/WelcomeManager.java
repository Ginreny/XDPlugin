package ginreny.xdp.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WelcomeManager implements Listener
{
    private final Title welcomeTitle = Title.title(
            Component.text("欢迎来到 ", NamedTextColor.AQUA, TextDecoration.BOLD)
                    .append(Component.text("XDUcraft", NamedTextColor.YELLOW, TextDecoration.BOLD)),
            Component.text("这里是 1.20 探索服务器", NamedTextColor.WHITE)
    );

    @EventHandler
    private void sendWelcomeTitle(PlayerJoinEvent e)
    {
        e.getPlayer().showTitle(welcomeTitle);
    }
}
