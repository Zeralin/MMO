package com.zeralin.mmo;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
			e.getPlayer().setHealthScale(20D);
		    e.getPlayer().setHealthScaled(true);
		    
		    Location spawn = new Location(e.getPlayer().getWorld(), -601, 68, 882);
		    e.getPlayer().teleport(spawn);
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.getPlayer().setMaxHealth(100D);
		e.getPlayer().setHealth(100D);
		e.getPlayer().setHealthScale(20D);
	    e.getPlayer().setHealthScaled(true);
	    
	    Location spawn = new Location(e.getPlayer().getWorld(), -601, 68, 882);
	    e.getPlayer().teleport(spawn);
	}
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
}
