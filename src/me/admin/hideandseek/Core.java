package me.admin.hideandseek;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	
	public void onDisbale() {
	}

}
