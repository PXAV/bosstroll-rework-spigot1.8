package de.pxav.bosstroll.configuration;

import de.pxav.bosstroll.BossTroll;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * This class handles the configuration file of the plugin.
 *
 * @author pxav
 */

@Getter
public class ConfigurationFile {

    // instane of the main class
    private BossTroll main;

    private File file;
    private YamlConfiguration configuration;

    private String commandUsePermission;

    /**
     * Default constructor.
     * Executes #loadFile() to create or load the configuration file.
     * Executed #loadSettings() to load all settings to the cache.
     *
     * @param main The instance of your main class.
     */
    public ConfigurationFile(final BossTroll main) {
        this.main = main;
        this.loadFile();
        this.loadSettings();
    }

    /**
     * Initializes the file.
     */
    private void loadFile() {
        this.file = new File(this.main.getDefaultFilePath(), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§aConfiguration file successfully loaded!");
    }

    /**
     * Load all settings from the file.
     */
    private void loadSettings() {

        commandUsePermission = configuration.getString("Permissions.UseTrollCommand");

        Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§aSuccessfully copied configuration data to cache!");
    }

}
