package me.admin.hideandseek;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import me.admin.hideandseek.commands.SetLobbyCommand;
import me.admin.hideandseek.events.PlayerJoin;
import me.admin.hideandseek.utils.Countdown;
import me.admin.hideandseek.utils.GameStates;

public class Core extends JavaPlugin implements Listener{

	GameStates state;
	Countdown countdown = new Countdown();
	public static Core instance;
	private File lobbyFile;
    private FileConfiguration lobby;
	
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new PlayerJoin(this), this);
		instance = this;
		
		GameStates.setState(GameStates.PRE_GAME);
		countdown.startCountdown();
		
		for (Player player : getServer().getOnlinePlayers()) {
			refreshPlayerScoreboard(player);
		}
		
		registerLobbyConfig();
		registerLobbyFiles();
		
		getCommand("setlobby").setExecutor(new SetLobbyCommand(this));
		
	
	}
	
	public static Core getInstance() {
		return instance;
	}


	public void onDisable() {

	}
	
	
	
	
	/**
	 *
	 * START OF SCOREBOARD
	 * 
	 */
	
	private org.bukkit.scoreboard.Scoreboard lobbyboard;
	private Objective lobbyo;
	

    
		public void refreshPlayerScoreboard(final Player player){
	        final int[] array = { 1, 2, 3 };

	        getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable(){
	            public void run(){
	                int[] arrayOfInt;
	                int j = (arrayOfInt = array).length;
	                for (int i = 0; i < j; i++){
	                    int number = arrayOfInt[i];
	                    if (number == 1) {
	                    	if (state.isState(state.PRE_GAME)) {
	            				setScoreboardLobby(player);
	            			}
	            			if (state.isState(state.IN_GAME)) {
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

            
            lobbyo.setDisplayName("§5§lLobby");
    		lobbyo.setDisplaySlot(DisplaySlot.SIDEBAR);
    		
    		
    		Score spacer1 = lobbyo.getScore(ChatColor.RED.toString());
    		spacer1.setScore(8);
    		
    		
    		Score servername = lobbyo.getScore("§fServer: §d" + getServer().getServerName().toUpperCase());
    		servername.setScore(7);
    		
    		
    		Score spacer2 = lobbyo.getScore(ChatColor.GOLD.toString());
    		spacer2.setScore(6);
    		
    		
    		Score timeleft = lobbyo.getScore("§fTime left: §d" + countdown.getCurrentTimer());
    		timeleft.setScore(5);
    		
    		
    		Score spacer3 = lobbyo.getScore(ChatColor.AQUA.toString());
    		spacer3.setScore(4);
    		
    		
    		Score playercount = lobbyo.getScore("§fPlayers: §d" + getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers());
    		playercount.setScore(3);
    		
    		
    		Score spacer4 = lobbyo.getScore(ChatColor.BLUE.toString());
    		spacer4.setScore(2);
    		
    		
    		Score websiteurl = lobbyo.getScore("§5      pdx-mc.com");
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
	
	/**
	 * 
	 * END OF SCOREBOARD
	 * 
	 */
	
	/*
	 * 
	 * CONFIGURATION SECTION
	 * 
	*/
		
		public void registerLobbyConfig() {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		
		private void registerLobbyFiles() {
			this.lobbyFile = new File(getDataFolder(), "lobby.yml");
			this.lobby = YamlConfiguration.loadConfiguration(this.lobbyFile);
			saveLobby();
		}
		
		public void saveLobby() {
			try {
				this.lobby.save(this.lobbyFile);
			}catch (Exception localException) {}
		}
		
		public File getLobbyFile() {
			return this.lobbyFile;
		}
		
		public FileConfiguration getLobby() {
			return this.lobby;
		}
		
		
		
	/*
	 * 
	 * END OF CONFIGURATION SECTION
	 * 
	 */
		
	
}
