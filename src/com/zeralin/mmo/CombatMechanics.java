package com.zeralin.mmo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatMechanics implements Listener{

    public Main main;
    
    public CombatMechanics(Main plugin){
    	main = plugin;
    }
    
    public List<String> tagged = new ArrayList<String>();

    @EventHandler
    public void onWorldDamage(EntityDamageEvent e){
    	if (e.getEntity() instanceof Player){
    		Player player = (Player) e.getEntity();
    		tagged.add(player.getName());
    		Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
				@Override
				public void run() {
					tagged.remove(player.getName());
				}
    		}, 20L * 10);
    	}
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
    	if (e.getEntity() instanceof Player){
    		Player player = (Player) e.getEntity();
    		tagged.add(player.getName());
    		Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
				@Override
				public void run() {
					tagged.remove(player.getName());
				}
    		}, 20L * 10);
    	}
    }
}
