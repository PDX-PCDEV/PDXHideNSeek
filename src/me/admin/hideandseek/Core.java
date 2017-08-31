package me.admin.hideandseek;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.admin.hideandseek.scoreboard.Scoreboard;
import me.admin.hideandseek.utils.GameStates;

public class Core extends JavaPlugin implements Listener{
	
	public static GameStates state;
	
	Scoreboard scoreboard;
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			scoreboard.refreshPlayerScoreboard(player);
		}
	}
	
	public void onDisable() {
	}

}
