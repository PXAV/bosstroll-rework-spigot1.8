package de.pxav.bosstroll.configuration;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.utils.MathExercise;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the configuration file of the plugin.
 * This includes:
 * - Creating and loading the file
 * - Loading config data to the cache.
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
    private String fakeBanMessage;
    private int mathTrollTime, lagDelay;
    private long fireBallExplosionRange, explosionBowRange;
    private List<MathExercise> mathExercises = new ArrayList<>();
    private List<String> randomTrollMessages = new ArrayList<>();

    /**
     * Default constructor.
     * Executes #loadFile() to create or load the configuration file.
     * Executed #loadSettings() to load all settings to the cache.
     *
     * @param main The instance of your main class.
     */
    public ConfigurationFile(final BossTroll main) {
        this.main = main;
    }

    /**
     * Initializes the file.
     */
    public void loadFile() {
        this.file = new File(this.main.getDefaultFilePath(), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§aConfiguration file successfully loaded!");
    }

    /**
     * Load all settings from the file and copies them into own fields.
     */
    public void loadSettings() {

        commandUsePermission = configuration.getString("Permissions.UseTrollCommand");

        mathTrollTime = configuration.getInt("MathTroll.Time");
        lagDelay = configuration.getInt("LagTroll.Delay");
        fireBallExplosionRange = configuration.getLong("Tools.FireBallExplosionRange");
        explosionBowRange = configuration.getLong("Tools.ExplosionBowRange");

        this.randomTrollMessages.addAll(configuration.getStringList("RandomChatTroll.Messages"));

        StringBuilder banMessageBuilder = new StringBuilder();
        configuration.getStringList("FakeBan.Message").forEach(current -> {
            banMessageBuilder.append(current.replace("&", "§")).append("\n");
        });
        fakeBanMessage = banMessageBuilder.toString();

        configuration.getStringList("MathTroll.Exercises").forEach(current -> {
            final String[] input = current.split("==");
            final String task = input[0];
            try {
                final int solution = Integer.parseInt(input[1]);
                this.mathExercises.add(new MathExercise(task, solution));
            } catch (NumberFormatException e) {
                Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§cError while loading configuration data! " +
                        "The solution of math exercise '" + task + "' is invalid! [" + input[1] + "]");
            }

        });

        Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§7Loaded §c" + this.mathExercises.size() + " §7math exercises.");
        Bukkit.getConsoleSender().sendMessage(this.main.getPrefix() + "§aSuccessfully copied configuration data to cache!");
    }

}
