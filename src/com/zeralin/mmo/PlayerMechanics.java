package com.zeralin.mmo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerMechanics implements Listener{

	public Main main;
	
	public PlayerMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		main.setupScoreboard();
		
		if (e.getPlayer().hasPlayedBefore()){
			e.getPlayer().setHealthScale(20D);
		    e.getPlayer().setHealthScaled(true);
		} else {
			e.getPlayer().setMaxHealth(100D);
			e.getPlayer().setHealth(100D);
			e.getPlayer().setLevel(100);
			e.getPlayer().setHealthScale(20D);
		    e.getPlayer().setHealthScaled(true);
		}
	}
	
	@EventHandler
	public void onEntityRegen(EntityRegainHealthEvent e){
		if (e.getEntity() instanceof Player){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.getPlayer().setMaxHealth(100D);
		e.getPlayer().setHealth(100D);
		e.getPlayer().setLevel(100);
		e.getPlayer().setHealthScale(20D);
	    e.getPlayer().setHealthScaled(true);
	}
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
}
