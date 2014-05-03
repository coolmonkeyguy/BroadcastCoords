package net.pl3x.broadcastcoords;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import net.pl3x.broadcastcoords.commands.CmdCoords;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class BroadcastCoords extends JavaPlugin {
	public void onEnable() {
		if (!new File(getDataFolder() + File.separator + "config.yml").exists())
			saveDefaultConfig();
		
		getCommand("coords").setExecutor(new CmdCoords(this));
		
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			log("&4[ERROR] &rFailed to start Metrics: " + e.getMessage());
		}
		
		log(getName() + " v" + getDescription().getVersion() + " by BillyGalbreath enabled!");
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
}
