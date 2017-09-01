package me.admin.hideandseek.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class InteractEvent implements Listener{
	
	
	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

}
