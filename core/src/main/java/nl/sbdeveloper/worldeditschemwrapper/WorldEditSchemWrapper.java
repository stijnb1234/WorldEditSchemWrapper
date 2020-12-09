package nl.sbdeveloper.worldeditschemwrapper;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import lombok.experimental.Delegate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WorldEditSchemWrapper implements WESInterface {
    private static WorldEditSchemWrapper instance;
    private JavaPlugin plugin;

    public static WorldEditSchemWrapper getInstance() {
        if (instance == null) {
            instance = new WorldEditSchemWrapper();
        }
        return instance;
    }

    @Delegate private WorldEditInterface handler;

    private WorldEditSchemWrapper() {
        int targetVersion;
        try {
            Class.forName("com.sk89q.worldguard.WorldGuard");
            targetVersion = 7;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.sk89q.worldguard.protection.flags.registry.FlagRegistry");
                targetVersion = 6;
            } catch (ClassNotFoundException e1) {
                targetVersion = -1;
            }
        }

        if (targetVersion == 6) {
            handler = new nl.sbdeveloper.worldeditschemwrapper.we.we6.WorldEditHandler_6(this);
        } else if (targetVersion == 7) {
            handler = new nl.sbdeveloper.worldeditschemwrapper.we.we7.WorldEditHandler_7(this);
        }
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public WorldEditPlugin getWorldEdit() {
        return (WorldEditPlugin) plugin.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    public Logger getLogger() {
        return plugin.getLogger();
    }
}
