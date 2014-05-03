package main.java.net.pl3x.broadcastcoords.commands;

import main.java.net.pl3x.broadcastcoords.BroadcastCoords;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCoords implements CommandExecutor {
	private BroadcastCoords plugin;
	
	public CmdCoords(BroadcastCoords plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender cs, Command cmd, String commandLabel, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("coords"))
			return false;
		if (!cs.hasPermission("bc.broadcastcoords")) {
			cs.sendMessage(plugin.colorize("&cYou do not have permission for that command!"));
			plugin.log(cs.getName() + " was denied access to that command!");
			return true;
		}
		Player player;
		if (args.length > 0) {
			player = plugin.getServer().getPlayer(args[0]);
			if (player == null) {
				cs.sendMessage(plugin.colorize("&cPlayer does not exist!"));
				return true;
			}
		} else {
			cs.sendMessage(plugin.colorize("&cYou must specify a player!"));
			return false;
		}
		
		
		String msg = plugin.getConfig().getString("broadcast-format", "&b{name} &7is at World&f: &3{world} &7Coords &f: &7X: &c{x}&7, &7Z: &c{z}");
		
		msg = msg.replaceAll("(?i)\\{name\\}", player.getName());
		msg = msg.replaceAll("(?i)\\{dispname\\}", player.getDisplayName());
		msg = msg.replaceAll("(?i)\\{world\\}", player.getWorld().getName());
		msg = msg.replaceAll("(?i)\\{x\\}", Integer.toString(player.getLocation().getBlockX()));
		msg = msg.replaceAll("(?i)\\{y\\}", Integer.toString(player.getLocation().getBlockY()));
		msg = msg.replaceAll("(?i)\\{z\\}", Integer.toString(player.getLocation().getBlockZ()));
		
		plugin.getServer().broadcastMessage(plugin.colorize(msg));
		return true;
	}
	
	
}