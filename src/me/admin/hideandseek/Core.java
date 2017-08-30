package me.admin.hideandseek;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	
	public String PDXTAG = "[PDX-MC] ";
	
	public void onEnable() {
		getLogger().info(PDXTAG + "The Hide and Seek Gamemode has been activated!")
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	
	public void onDisbale() {
		getLogger().info(PDXTAG + "The Hide and Seek Gamemode has been deactivated!")
	}

}
