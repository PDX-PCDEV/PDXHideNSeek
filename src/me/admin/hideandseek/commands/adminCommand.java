package me.admin.hideandseek.commands;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.admin.hideandseek.Core;

public class adminCommand implements CommandExecutor
{
	
	Core pl;
	
	public adminCommand(Core plu) 
	{
		this.pl = plu;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("admin")) 
		{
			Player player = (Player)sender;
			if (sender.isOp()) {
				openAdminMenu(player);
			}else {
				sender.sendMessage("§4** §cYou are not a server admin! You may not perform this command!");
			}
		}
		return true;
	}
	
	public void openAdminMenu(Player player) 
	{
		Inventory inv = Bukkit.createInventory(null, 27, "§f§lAdmin GUI");
		
		ItemStack start = new ItemStack(Material.CONCRETE, 1,  (byte)5);
		ItemMeta startMeta = start.getItemMeta();
		startMeta.setDisplayName("§a§lStart the game!");
		start.setItemMeta(startMeta);
		
		ItemStack cancel = new ItemStack(Material.BARRIER);
		ItemMeta cancelMeta = cancel.getItemMeta();
		cancelMeta.setDisplayName("§fClose the menu!");
		cancel.setItemMeta(cancelMeta);
		
		ItemStack restart = new ItemStack(Material.CONCRETE, 1,  (byte)4);
		ItemMeta restartMeta = start.getItemMeta();
		restartMeta.setDisplayName("§6§lRestart the game!");
		restart.setItemMeta(restartMeta);
	
		
		inv.setItem(12, start);
		inv.setItem(13, cancel);
		inv.setItem(14, restart);
		player.openInventory(inv);
	}

}
