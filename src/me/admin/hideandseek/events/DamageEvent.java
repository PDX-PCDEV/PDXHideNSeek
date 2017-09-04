package me.admin.hideandseek.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.admin.hideandseek.utils.GameStates;

public class DamageEvent implements Listener
{
	
	GameStates state;
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) 
	{
		if (event.getEntity() instanceof Player)
		{
			if (state.isState(state.PRE_GAME))
			{
				event.setCancelled(true);
			}
		}
	}

}
