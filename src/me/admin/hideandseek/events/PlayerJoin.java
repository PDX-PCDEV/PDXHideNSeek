package me.admin.hideandseek.events;

import org.bukkit.Bukkit;
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
	private me.admin.hideandseek.Core spawnPoint;

    public PlayerJoin(Core spawnPoint)
    {
        this.spawnPoint = spawnPoint;
    }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (state.isState(state.PRE_GAME)) {
		Player p = event.getPlayer();
		
		FileConfiguration i = this.spawnPoint.getLobby();
        if (!i.contains("world"))
        {
            p.sendMessage("§4** §cLobby not set. Contact a senior member of staff.");
            return;
        }
        World w = Bukkit.getWorld(i.getString("world"));
        double x = i.getDouble("x");
        double y = i.getDouble("y");
        double z = i.getDouble("z");
        float yaw = (float)i.getDouble("yaw");
        float pitch = (float)i.getDouble("pitch");
        Location loc = new Location(w, x, y, z, yaw, pitch);
        p.teleport(loc);
        
        event.setJoinMessage("§5" + event.getPlayer().getName().toString() + " joined! §d(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
		}else {
			
			//TODO SET THEM TO SPECTATOR IN THE MIDDLE OF THE MAP
		}
	}

}
