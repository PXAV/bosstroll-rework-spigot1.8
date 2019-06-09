package de.pxav.bosstroll;

import de.pxav.bosstroll.configuration.ConfigurationFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin.
 *
 * @author pxav
 */

@Getter
public class BossTroll extends JavaPlugin {

    private String prefix = "§8[§cTroll§8] §r";
    private String defaultFilePath = "plugins//BossTrollRework";

    private ConfigurationFile configurationFile;

    @Override
    public void onEnable() {

        // creates and loads the plugin configuration file
        this.saveDefaultConfig();
        this.configurationFile = new ConfigurationFile(this);

    }

    @Override
    public void onDisable() {

    }
}
