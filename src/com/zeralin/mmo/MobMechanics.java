package com.zeralin.mmo;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class MobMechanics implements Listener{

	public Main main;
	
	public MobMechanics(Main plugin){
	    main = plugin;
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (!(e.getEntity() instanceof Player)){
			e.setDroppedExp(0);
			e.getDrops().clear();
		}
		
		if (e.getEntity() instanceof Wolf && e.getEntity().getKiller() instanceof Player){
			Wolf wolf = (Wolf) e.getEntity();
			if (wolf.getCustomName().equalsIgnoreCase(ChatColor.WHITE + "Dire Wolf")){
                   Random random = new Random();
                   int hp = random.nextInt(40) + 11;
				    
				   ItemStack plate = new ItemStack(Material.LEATHER_CHESTPLATE);
				   ItemMeta plateMeta = plate.getItemMeta();
				   plateMeta.setDisplayName(ChatColor.WHITE + "Ripped Shirt");
				   plateMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + hp));
				   plate.setItemMeta(plateMeta);
				   e.getDrops().add(plate);
			}
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e){
	    if (e.getEntity().getCustomName() == null){
			   e.setCancelled(true);
	  }
	  
	    if (e.getEntity().getName().equalsIgnoreCase("T1Wolf")){
	    	Wolf mob = (Wolf) e.getEntity();
	    	Random random = new Random();
	    	int hp = random.nextInt(10) + 16;
	    	mob.setCustomName(ChatColor.WHITE + "Dire Wolf");
	    	mob.setMaxHealth(hp);
	    	mob.setHealth(hp);
	    	mob.isAngry();
	    	mob.isAdult();
	    }
	    
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Wolf){
			Wolf wolf = (Wolf) e.getDamager();
			if (wolf.getCustomName().equalsIgnoreCase(ChatColor.WHITE + "Dire Wolf")){
				Random random = new Random();
				int dmg = random.nextInt(5) + 6;
				e.setDamage(dmg);	
			}
		}
	}
	
}
