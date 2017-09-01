package me.admin.hideandseek.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.admin.hideandseek.Core;

public class SetLobbyCommand implements CommandExecutor{
	
	private Core lobbyPoint;
	
	public SetLobbyCommand(Core lobbyPoint) {
		this.lobbyPoint = lobbyPoint;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (((sender instanceof Player)) && (sender.isOp())) {
			
			Location loc = ((Entity) sender).getLocation();
			String world = loc.getWorld().getName();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			float yaw = loc.getYaw();
			float pitch = loc.getPitch();
			this.lobbyPoint.getLobby().set("world", world);
			this.lobbyPoint.getLobby().set("x", Double.valueOf(x));
			this.lobbyPoint.getLobby().set("y", Double.valueOf(y));
			this.lobbyPoint.getLobby().set("z", Double.valueOf(z));
			this.lobbyPoint.getLobby().set("yaw", Float.valueOf(yaw));
			this.lobbyPoint.getLobby().set("pitch", Float.valueOf(pitch));
			this.lobbyPoint.saveLobby();
			
			sender.sendMessage("§5** §dYou have set the Lobby!");
		}else {
			sender.sendMessage("§4** §cYou are not a server admin! You may not perform this command!");
		}
		return false;

	}
}
