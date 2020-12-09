package nl.sbdeveloper.worldeditschemwrapper;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public interface WESInterface {
    FileConfiguration getConfig();

    WorldEditPlugin getWorldEdit();

    Logger getLogger();
}
