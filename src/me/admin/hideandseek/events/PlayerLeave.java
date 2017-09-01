package me.admin.hideandseek.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.admin.hideandseek.utils.GameStates;

public class PlayerLeave implements Listener{
	
	GameStates state;
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (state.isState(state.PRE_GAME)) {
			e.setQuitMessage("§5" + e.getPlayer().getName().toString() + " quit! §d(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
		}else {
			e.setQuitMessage(null);
		}
	}

}
