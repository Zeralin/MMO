package com.zeralin.mmo;

import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class MobMechanics implements Listener{

	public Main main;
	
	public MobMechanics(Main plugin){
	    main = plugin;
	}

	//ideas to use chatcolor.underline for elites/bosses
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e){
	    if (e.getEntity().getCustomName() == null){
			   e.setCancelled(true);
		} else if (e.getEntity().getCustomName().equalsIgnoreCase("T1")){
				Creature mob = (Creature) e.getEntity();
				spawnT1(mob);
		} else if (e.getEntity().getCustomName().equalsIgnoreCase("T2")){
				Creature mob = (Creature) e.getEntity();
				spawnT2(mob);
		} else if (e.getEntity().getCustomName().equalsIgnoreCase("T3")){
				Creature mob = (Creature) e.getEntity();
				spawnT3(mob);
		} else if (e.getEntity().getCustomName().equalsIgnoreCase("T4")){
				Creature mob = (Creature) e.getEntity();
				spawnT4(mob);
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		e.setDroppedExp(0);
	}
	
    public void spawnT1(Creature mob){
		mob.setCustomName(ChatColor.WHITE + "Tier 1");
		mob.setCustomNameVisible(true);
		mob.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
	}

    public void spawnT2(Creature mob){
    	mob.setCustomName(ChatColor.GREEN + "Tier 2");
		mob.setCustomNameVisible(true);
		mob.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
    }

    public void spawnT3(Creature mob){
    	mob.setCustomName(ChatColor.YELLOW + "Tier 3");
		mob.setCustomNameVisible(true);
		mob.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
    }
    
	public void spawnT4(Creature mob){
		mob.setCustomName(ChatColor.AQUA + "Tier 4");
		mob.setCustomNameVisible(true);
		mob.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
	}
	
}
