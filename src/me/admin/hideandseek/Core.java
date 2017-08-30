package me.admin.hideandseek;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	
	public String PDXTAG = "[PDX-MC] ";
	
	public void onEnable() {
		getLogger().info(PDX-TAG + "The Hide and Seek Gamemode has been activated!")
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(this, this);
	}
	
	public void onDisbale() {
		getLogger().info(PDX-TAG + "The Hide and Seek Gamemode has been deactivated!")
	}

}
