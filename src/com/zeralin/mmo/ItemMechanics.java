package com.zeralin.mmo;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemMechanics implements Listener{

	public Main main;
	
	public ItemMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onWeaponSwing(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.WOOD_AXE ||
			player.getItemInHand().getType() == Material.WOOD_SWORD ||
			player.getItemInHand().getType() == Material.STONE_AXE ||
			player.getItemInHand().getType() == Material.STONE_SWORD ||
			player.getItemInHand().getType() == Material.IRON_AXE ||
			player.getItemInHand().getType() == Material.IRON_SWORD ||
			player.getItemInHand().getType() == Material.DIAMOND_AXE ||
			player.getItemInHand().getType() == Material.DIAMOND_SWORD ||
			player.getItemInHand().getType() == Material.GOLD_AXE ||
			player.getItemInHand().getType() == Material.GOLD_SWORD){
			player.getItemInHand().setDurability((short) 0);
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
				player.updateInventory();
			}
		}
	}
	
}
