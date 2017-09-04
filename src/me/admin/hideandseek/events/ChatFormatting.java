package me.admin.hideandseek.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.admin.hideandseek.utils.GameStates;

public class ChatFormatting implements Listener
{
	
	GameStates state;
	
	@EventHandler
	public void chatFormat(AsyncPlayerChatEvent e) 
	{
		Player p = e.getPlayer();
		
		if (state.isState(state.PRE_GAME))
		{
			e.setFormat("§f[§dLOBBY§f] " + p.getName() + " §5»§f " + e.getMessage());
		}
		if (state.isState(state.IN_GAME)) 
		{
			e.setFormat("§f[§dPLAYER§f] " + p.getName() + " §5»§f " + e.getMessage());
		}
	}

}
