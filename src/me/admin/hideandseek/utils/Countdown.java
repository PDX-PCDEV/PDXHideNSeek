package me.admin.hideandseek.utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.admin.hideandseek.Core;

public class Countdown {
	
	static Core pl;
	
	GameStates state;
	
	static int taskID;
	
	public static int timer = 100;
	

	public static String formatIntoHHMMSS()
	{
	int remainder = timer % 3600;
	int minutes = remainder / 60;
	int seconds = remainder % 60;
	 
	return new StringBuilder().append(minutes).append(":").append(seconds < 10 ? "0" : "").append(seconds).toString();
	}
	
	public String getCurrentTimer() {
		return formatIntoHHMMSS();
	}
	

	@SuppressWarnings("deprecation")
	public void startCountdown() {
		 taskID = Core.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask((Core.getInstance()), new BukkitRunnable(){
			@Override
			public void run() {
				if (state.isState(state.PRE_GAME)) {
					if (timer != 0) {
						timer--;
						formatIntoHHMMSS();
					}else {
						if (Bukkit.getServer().getOnlinePlayers().size() <= 3) {
							Bukkit.broadcastMessage("§5** §dNot enough players to start! Please wait until more join!§d");
							restartTimer();
						}else {
							//TODO BEGIN THE GAME HERE
							state.setState(state.IN_GAME);
						}
					}
				}
			}
			 
		 }, 20L, 20L);
	}


	public void restartTimer() {
		Bukkit.getScheduler().cancelTask(taskID);
		timer = 100;
		startCountdown();
	}

}
