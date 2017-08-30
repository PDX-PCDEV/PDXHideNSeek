package me.admin.hideandseek;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(this, this);
	}
	
	public void onDisbale() {
		
	}

}
