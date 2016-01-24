package com.zeralin.mmo;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerMechanics implements Listener{

	public Main main;
	
	public PlayerMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
			if (e.getPlayer().getItemInHand() != null){
				e.getPlayer().getItemInHand().setDurability((short) 0);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
		}
	}
}
