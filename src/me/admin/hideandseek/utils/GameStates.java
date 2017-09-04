package me.admin.hideandseek.utils;

import me.admin.hideandseek.Core;

public enum GameStates 
{

	IN_GAME ("In Game", false), PRE_GAME ("Pre Game", true), POST_GAME ("Post Game", false), REBOOTING ("Rebooting", false);
	
	
	String gameState;
	boolean canJoin;
	private static GameStates state;
	
	
	private GameStates(String state, boolean canJoin) 
	{
		gameState = state;
		this.canJoin = canJoin;
	}
	
	
	public String toString()
	{
		return gameState;
	}
	
	public boolean isJoin()
	{
		return canJoin;
	}
	
	public static GameStates[] getStates()
	{
		GameStates[] gs = {IN_GAME, PRE_GAME, POST_GAME, REBOOTING};
	
		return gs;
	}
	
	public static void setState(GameStates state) 
	{
		GameStates.state = state;
	}
	
	public static boolean isState(GameStates state) 
	{
		return GameStates.state == state;
	}
}
