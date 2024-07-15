package teamrevolution.farming;

import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import teamrevolution.farming.listener.BlockBreakingListener;

public final class Farming extends JavaPlugin {

    private static Farming plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        new BlockBreakingListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setPlugin(Farming plugin) {
        Farming.plugin = plugin;
    }

    public static Farming getPlugin() {
        return plugin;
    }
}
