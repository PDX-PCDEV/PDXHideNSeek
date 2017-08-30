package me.admin.hideandseek;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

public enum GameState implements Listener{
	
	IN_GAME, PRE_GAME, POST_GAME;
	
	public static GameState state;
	
	public void canJoin(PlayerPreLoginEvent Event) {
		if (!GameState.getState().equals(GameState.PRE_GAME)) {
			//TODO Put them into Spectator
		}else {
			// Add them to a list of tributes left
		}
	}
	
	public static GameState getState() {
		return state;
	}

}
