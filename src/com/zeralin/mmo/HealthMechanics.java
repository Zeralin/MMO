package com.zeralin.mmo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HealthMechanics implements Listener{

	public Main main;
	
	public HealthMechanics(Main plugin){
		main = plugin;
	}
	
	public void regenHealth(){
		for (Player player : Bukkit.getOnlinePlayers()){
				Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new Runnable(){
				@Override
				public void run() {
					int amount = 5;
					if (!player.isDead()){
			         if (player.getHealth() + amount > player.getMaxHealth()){
			        	 player.setHealth(player.getMaxHealth());
			        	 player.setLevel((int) player.getMaxHealth());
			         } else {
			        	 player.setHealth(player.getHealth() + amount);
			        	 player.setLevel((player.getLevel() + amount));
			         }
						}
					}
	          }, 20L, 20L);
			}
		}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if (e.getPlayer().hasPlayedBefore()){
			e.getPlayer().setHealthScale(20);
			e.getPlayer().setLevel((int) e.getPlayer().getHealth());
			e.getPlayer().setHealthScaled(true);
		} else {
			e.getPlayer().setMaxHealth(100);
			e.getPlayer().setHealth(100);
			e.getPlayer().setLevel(100);
			e.getPlayer().setHealthScale(20);
			e.getPlayer().setHealthScaled(true);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.getPlayer().setMaxHealth(100);
		e.getPlayer().setHealth(100);
		e.getPlayer().setLevel(100);
		e.getPlayer().setHealthScale(20);
		e.getPlayer().setHealthScaled(true);
	}
	
	@EventHandler
	public void onRegen(EntityRegainHealthEvent e){
		if (e.getEntity() instanceof Player){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			player.setLevel((int) player.getHealth() - (int) e.getDamage());
		}
	}
	
	@EventHandler
	public void onNaturalDamage(EntityDamageEvent e){
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			player.setLevel((int) player.getHealth() - (int) e.getDamage());
        }
	}
}
