package com.zeralin.mmo;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
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
	
	int wolfs = 0;
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (!(e.getEntity() instanceof Player)){
			e.getDrops().clear();
		}
	
		    e.setDroppedExp(0);

		    if (e.getEntity().getCustomName() == null) {} else {
		    
			if (e.getEntity().getCustomName().equalsIgnoreCase(ChatColor.WHITE + "Dire Wolf")){
                   Random random = new Random();
				   int drop = (150) + 1;
				   
                   if (drop <= 10 && drop >= 0){
                       int hp = random.nextInt(40) + 11;
                	   ItemStack plate = new ItemStack(Material.LEATHER_CHESTPLATE);
                	   ItemMeta plateMeta = plate.getItemMeta();
                	   plateMeta.setDisplayName(ChatColor.WHITE + "Ripped Shirt");
				   	   plateMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + hp));
				   	   plate.setItemMeta(plateMeta);
				   	   e.getDrops().add(plate);
                   } else if (drop <= 20 && drop >= 11){
    				   int min = random.nextInt(2) + 4;
    				   int max = random.nextInt(6) + 6;
                	   ItemStack wep = new ItemStack(Material.WOOD_SWORD);
                	   ItemMeta wepMeta = wep.getItemMeta();
                	   wepMeta.setDisplayName(ChatColor.WHITE + "Splintered Sword");
                	   wepMeta.setLore(Arrays.asList(ChatColor.WHITE + "Damage: " + min + " - " + max));
                	   wep.setItemMeta(wepMeta);
                	   e.getDrops().add(wep);
                   } else if (drop <= 30 && drop >= 21){
                	   int hp = random.nextInt(10) + 16;
                	   ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
                	   ItemMeta helmMeta = helm.getItemMeta();
                	   helmMeta.setDisplayName(ChatColor.WHITE + "Ruined Helmet");
                	   helmMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + hp));
                	   helm.setItemMeta(helmMeta);
                	   e.getDrops().add(helm);
                   } else if (drop <= 40 && drop >= 31){
                	   int hp = random.nextInt(35) + 11;
                	   ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
                	   ItemMeta legsMeta = legs.getItemMeta();
                	   legsMeta.setDisplayName(ChatColor.WHITE + "Blood Stained");
                	   legsMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + hp));
                	   legs.setItemMeta(legsMeta);
                	   e.getDrops().add(legs);
                   } else if (drop <= 50 && drop >= 41){
                	   int hp = random.nextInt(11) + 15;
                	   ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                	   ItemMeta bootsMeta = boots.getItemMeta();
                	   bootsMeta.setDisplayName(ChatColor.WHITE + "Broken Boots");
                	   bootsMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health:" + hp));
                	   boots.setItemMeta(bootsMeta);
                	   e.getDrops().add(boots);
                   }
			}
			if (e.getEntity().getName().equalsIgnoreCase(ChatColor.WHITE + "Wolf Tamer")){
				Random random = new Random();
				int hp = random.nextInt(100) + 51;
				
				ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
		    	ItemMeta helmMeta = helm.getItemMeta();
		    	helmMeta.setDisplayName(ChatColor.WHITE + "Fur Hood");
		    	helmMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + hp, ChatColor.LIGHT_PURPLE + "Rare Item"));
		    	helm.setItemMeta(helmMeta);
		    	e.getDrops().add(helm);
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
	    
	    if (e.getEntity().getName().equalsIgnoreCase("Tamer")){
	    	Skeleton mob = (Skeleton) e.getEntity();
	    	Random random = new Random();
	    	int hp = random.nextInt(150) + 301;
	    	mob.setCustomName(ChatColor.WHITE + "Wolf Tamer");
	    	mob.setMaxHealth(hp);
	    	mob.setHealth(hp);
	    	
	    	mob.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
	    }
	    
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Creature){
			Creature mob = (Creature) e.getDamager();
			if (mob.getCustomName().equalsIgnoreCase(ChatColor.WHITE + "Dire Wolf")){
				Random random = new Random();
				int dmg = random.nextInt(5) + 6;
				e.setDamage(dmg);	
			}
			if (mob.getCustomName().equals(ChatColor.WHITE + "Wolf Tamer")){
				Random random = new Random();
				int dmg = random.nextInt(50) + 31;
				e.setDamage(dmg);
				
		        if (wolfs < 4){
		        	Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
						@Override
						public void run() {
							Entity wolf = e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.WOLF);
							wolf.setCustomName(ChatColor.WHITE + "Dire Wolf");
						}
		        	}, 20L * 5);
		        }
			}
		}
	}
	
}
