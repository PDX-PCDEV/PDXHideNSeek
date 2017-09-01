package me.admin.hideandseek.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.admin.hideandseek.Core;
import me.admin.hideandseek.utils.GameStates;

public class PlayerJoin implements Listener{
	
	GameStates state;
	private Core spawnPoint;

    public PlayerJoin(Core spawnPoint)
    {
        this.spawnPoint = spawnPoint;
    }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		if (state.isState(state.PRE_GAME)) {
		FileConfiguration i = this.spawnPoint.getLobby();
        if (!i.contains("world")){
            player.sendMessage("§4** §cLobby not set. Contact a senior member of staff.");
            return;
        }
        World w = Bukkit.getWorld(i.getString("world"));
        double x = i.getDouble("x");
        double y = i.getDouble("y");
        double z = i.getDouble("z");
        float yaw = (float)i.getDouble("yaw");
        float pitch = (float)i.getDouble("pitch");
        Location loc = new Location(w, x, y, z, yaw, pitch);
        player.teleport(loc);
        
        if (state.isState(state.PRE_GAME)) {
        	event.setJoinMessage("§5" + event.getPlayer().getName().toString() + " joined! §d(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
        }else {
        	event.setJoinMessage(null);
        }
		}else {
			
			//TODO SET THEM TO SPECTATOR IN THE MIDDLE OF THE MAP
		}
	}

}
