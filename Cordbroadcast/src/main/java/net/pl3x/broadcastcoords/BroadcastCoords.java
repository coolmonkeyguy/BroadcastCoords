package main.java.net.pl3x.broadcastcoords;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import main.java.net.pl3x.broadcastcoords.commands.CmdCoords;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class BroadcastCoords extends JavaPlugin {
	public void onEnable() {
		if (!new File(getDataFolder() + File.separator + "config.yml").exists())
			saveDefaultConfig();
		
		getCommand("coords").setExecutor(new CmdCoords(this));
		getCommand("bcreload");
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			log("&4[ERROR] &rFailed to start Metrics: " + e.getMessage());
		}
		
		log(getName() + " v" + getDescription().getVersion() + " by Coolmonkeyguy enabled!");
	}
	
	public void onDisable() {
		log(getName() + " Disabled.");
	}
	
	public void log (Object obj) {
		if (getConfig().getBoolean("color-logs", true)) {
			getServer().getConsoleSender().sendMessage(colorize("&3[&d" +  getName() + "&3]&r " + obj));
		} else {
			Bukkit.getLogger().log(Level.INFO, "[" + getName() + "] " + ((String) obj).replaceAll("(?)\u00a7([a-f0-9k-or])", ""));
		}
	}
	
	public String colorize(String str) {
		return str.replaceAll("(?i)&([a-f0-9k-or])", "\u00a7$1");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		  
		  Player player = (Player) sender;
		  
		  if (CommandLabel.equalsIgnoreCase("bcreload")) {
			  if (player.hasPermission("bc.bcreload")) {
	      player.sendMessage(ChatColor.GRAY + "BroadcastCoords" + ChatColor.GREEN + " Reload Complete.");
		  reloadConfig();
		  }
		  
			else
			{
				player.sendMessage(ChatColor.RED + " You don't have permission!");	
				
			}
		  
		  }
		  
		  
		return false;
	  }
		
	}
	

		

