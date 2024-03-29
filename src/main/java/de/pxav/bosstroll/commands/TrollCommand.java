package de.pxav.bosstroll.commands;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

/**
 * This class represents the main command of the plugin. You can access
 * all troll features (tools, player, server) with this command.
 *
 * @author pxav
 */
public class TrollCommand implements CommandExecutor {

    // instance of the main class.
    private BossTroll main;

    /**
     * Default constructor. Automatically registeres the command.
     * It's recommended to execute this on every plugin startup.
     *
     * @param commandString What should the player type to execute this command?
     *                      This should also be changed in the plugin.yml
     * @param main          The instance of the main class.
     */
    public TrollCommand(final String commandString, final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginCommand(commandString).setExecutor(this);
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {

        // checks if the command sender is a player
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(this.main.getPrefix() + "§cYou need to be a player to execute this command.");
            return false;
        }

        final Player player = (Player) commandSender;

        // checks if the player has the needed permission(s)
        if(!player.hasPermission(this.main.getConfigurationFile().getCommandUsePermission())) {
            player.sendMessage(this.main.getPrefix() + "§7You do §cnot §7have permission to execute this command.");
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("server")) {
            this.main.getServerTrollInventory().open(player);
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("tools")) {
            this.main.getTrollToolsInventory().open(player);
            return false;
        }

        if(args.length == 1
                && !args[0].equalsIgnoreCase("server")
                && !args[0].equalsIgnoreCase("tools")) {

            this.main.getPlayerInfo().getPlayersTrolling().remove(player.getUniqueId());
            this.main.getListenerUtil().unregisterListener(player);
            final Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage(this.main.getPrefix() + "§cDieser Spieler ist akutell nicht online.");
                return false;
            }

            this.main.getPlayerInfo().getPlayersTrolling().put(player.getUniqueId(), target.getUniqueId());
            player.sendMessage(this.main.getPrefix() + "§7You are now trolling " + target.getName());
            this.main.getPlayerTrollInventory().open(player);


            return false;
        }

        this.main.getMessageUtils().sendMultipleLineMessage(player, new String[] {
                "§8§m----------------------------",
                "§7Running §cBossTroll §7in v" + this.main.getDescription().getVersion(),
                "§ctroll <PLAYER>",
                "§ctroll tools",
                "§ctroll server",
                "§8§m----------------------------"
        }, true);

        return false;
    }
}
