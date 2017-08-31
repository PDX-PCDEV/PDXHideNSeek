package me.admin.hideandseek.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import me.admin.hideandseek.Core;
import me.admin.hideandseek.utils.GameStates;

public class Scoreboard implements Listener{
	
	private org.bukkit.scoreboard.Scoreboard lobbyboard;
	private Objective lobbyo;
	
	GameStates state;
	
	Core pl;
	public Scoreboard(Core pln) {
		pl = pln;
	}

		public void refreshPlayerScoreboard(final Player player)
	    {
	        final int[] array = { 1, 2, 3 };

	        pl.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable()
	        {
	            public void run()
	            {
	                int[] arrayOfInt;
	                int j = (arrayOfInt = array).length;
	                for (int i = 0; i < j; i++)
	                {
	                    int number = arrayOfInt[i];
	                    if (number == 1) {
	                        if (GameStates.getStates().equals(GameStates.PRE_GAME)) {
	                        	setScoreboardLobby(player);
	                        }else if (GameStates.getStates().equals(GameStates.IN_GAME)) {
	                        	setScoreboardInGame(player);
	                        }
	                    }
	                }
	            }
	        }, 0L, 1 * 20);
	    }
		
		public void setScoreboardLobby(Player player){

            final ScoreboardManager sbManager = Bukkit.getScoreboardManager();
            final org.bukkit.scoreboard.Scoreboard sBoard = sbManager.getNewScoreboard();

            final Objective lobbyo = sBoard.registerNewObjective("Lobby", "dummeh");

            lobbyo.setDisplayName("§d§lLobby");
    		lobbyo.setDisplaySlot(DisplaySlot.SIDEBAR);
    		
    		Score spacer1 = lobbyo.getScore(ChatColor.RED.toString());
    		spacer1.setScore(8);
    		
    		Score servername = lobbyo.getScore("§fServer: §d" + pl.getServer().getServerName().toUpperCase());
    		servername.setScore(7);
    		
    		Score spacer2 = lobbyo.getScore(ChatColor.GOLD.toString());
    		spacer2.setScore(6);
    		
    		Score timeleft = lobbyo.getScore("§fTime left: §d"); //TODO ADD COUNTDOWN CLOCK HERE)
    		timeleft.setScore(5);
    		
    		Score spacer3 = lobbyo.getScore(ChatColor.AQUA.toString());
    		spacer3.setScore(4);
    		
    		Score playercount = lobbyo.getScore("§fPlayers: §d" + pl.getServer().getOnlinePlayers().size() + "/" + pl.getServer().getMaxPlayers());
    		playercount.setScore(3);
    		
    		Score spacer4 = lobbyo.getScore(ChatColor.BLUE.toString());
    		spacer4.setScore(2);
    		
    		Score websiteurl = lobbyo.getScore("§d   pdx-mc.com");
    		websiteurl.setScore(1);

            player.setScoreboard(sBoard);
}
		
		public void setScoreboardInGame(Player player){

			 final ScoreboardManager sbManager = Bukkit.getScoreboardManager();
            final org.bukkit.scoreboard.Scoreboard sBoardGAME = sbManager.getNewScoreboard();

            final Objective ingameo = sBoardGAME.registerNewObjective("Lobby", "dummeh");

            ingameo.setDisplayName("§d§lHIDE N' SEEK");
            ingameo.setDisplaySlot(DisplaySlot.SIDEBAR);
    		
    		Score spacer1 = ingameo.getScore(ChatColor.RED.toString());
    		spacer1.setScore(9);
    		
    		Score servername = ingameo.getScore("§fYour Block: §d"); //TODO ADD THEY BLOCK THEY ARE HERE
    		servername.setScore(8);
    		
    		Score spacer2 = ingameo.getScore(ChatColor.GOLD.toString());
    		spacer2.setScore(7);
    		
    		Score timeleft = ingameo.getScore("§fTime left: §d"); //TODO ADD COUNTDOWN CLOCK HERE, TO THE END OF THE GAME (LASTS FOR MAX OF 5 minutes)
    		timeleft.setScore(6);
    		
    		Score spacer3 = ingameo.getScore(ChatColor.AQUA.toString());
    		spacer3.setScore(5);
    		
    		Score hiderscount = ingameo.getScore("§fHiders: §d"); //TODO ADD REMAINING HIDERS HERE
    		hiderscount.setScore(4);
    		
    		Score seekerscount = ingameo.getScore("§fSeekers: §d"); //TODO ADD AMOUNT OF SEEKERS CURRENTLY
    		seekerscount.setScore(3);
    		
    		Score spacer4 = ingameo.getScore(ChatColor.DARK_GRAY.toString());
    		spacer4.setScore(2);
    		
    		Score websiteurl = ingameo.getScore("§d   pdx-mc.com");
    		websiteurl.setScore(1);

            player.setScoreboard(sBoardGAME);
}
		
		@EventHandler
		public void onJoin(PlayerJoinEvent event) {
			Player player = event.getPlayer();
			refreshPlayerScoreboard(player);
		}
		
	}
	
	
