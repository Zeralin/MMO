package com.zeralin.mmo;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import net.md_5.bungee.api.ChatColor;

public class HealthMechanics implements Listener, CommandExecutor{

	public Main main;
	
	public HealthMechanics(Main plugin){
		main = plugin;
	}
	
	public int getHPFromLore(ItemStack item, String value){
		int health = 0;
		List<String> lore = item.getItemMeta().getLore();
		if (lore != null && lore.get(0).contains(value)){
			try {
				String hp = (String) lore.get(0).split(": ")[1];
				hp = ChatColor.stripColor(hp);
				health = Integer.parseInt(hp.trim());
			} catch (Exception e) {}
		}
		return health;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
			@Override
			public void run() {
					checkHealth(player);
			}
		}, 1L);
	}
	
	public void checkHealth(Player player){
			PlayerInventory inv = player.getInventory();
			int HP = 100;
			int health = 0; 
			
			if (inv.getHelmet() != null && inv.getHelmet().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getHelmet(), "Health");
				HP += health;
			}
			if (inv.getChestplate() != null && inv.getChestplate().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getChestplate(), "Health");
				HP += health;
			}
			if (inv.getLeggings() != null && inv.getLeggings().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getLeggings(), "Health");
				HP += health;
			}
			if (inv.getBoots() != null && inv.getBoots().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getBoots(), "Health");
				HP += health;
			}
			
				 player.setMaxHealth(HP);	
	 }
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player){
			if (cmd.getName().equalsIgnoreCase("setHealth")){
				if (cs.isOp()){
					Player player = (Player) cs;
			        if (args.length == 1){
			        	try {
			        		int hp = Integer.parseInt(args[0]);
			        		if (!(hp < 1)){
				        		player.setMaxHealth(hp);
				        		player.setHealth(hp);
				        		player.sendMessage(ChatColor.WHITE + "Your health has been set to " + 
				        		     ChatColor.GREEN + hp + ChatColor.WHITE + "!");
			        		} else {
			        			cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        		}
			        	} catch (Exception e){
			        		cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        	}
			        } else {
			        	cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        }
				} else {
					cs.sendMessage(ChatColor.RED + "Invalid permissions.");
			}
		}
	  }
		return true;
	}
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
    	 Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new Runnable(){
 			@Override
 			public void run() {
 				BountifulAPI.sendActionBar(e.getPlayer(), 
 				 ChatColor.WHITE + "HP " + (int) e.getPlayer().getHealth() + "/" + (int) e.getPlayer().getMaxHealth());
 			}
 	    }, 1L, 1L);
    }
	
	
	@EventHandler
	public void onRegen(EntityRegainHealthEvent e){
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
				int HPs = (int) player.getMaxHealth() / 20;
			    e.setAmount(HPs);
			    player.sendMessage(ChatColor.GREEN + "+" + HPs + " HP");
			}
	}
	
}
