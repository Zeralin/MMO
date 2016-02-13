package com.zeralin.mmo;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.md_5.bungee.api.ChatColor;

public class ItemMechanics implements Listener{

	public Main main;
	
	public ItemMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e){
		if (e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();

			if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR && 
					 player.getItemInHand().getItemMeta().hasLore()) {
				int damageMin = getMin(player.getItemInHand(), "Damage");
				int damageMax = getMax(player.getItemInHand(), "Damage");
				Random random = new Random();
				double dmg = random.nextInt((damageMax - damageMin) + 1) + damageMin;
				e.setDamage(dmg);

			}
		}
	}
	
	public int getMin(ItemStack item, String value){
		int dmg = 1;
		List<String> lore = item.getItemMeta().getLore();
		if (lore != null && lore.get(0).contains(value)){
			try {
				String damage = (String) lore.get(0).split(": ")[1];
				damage = ChatColor.stripColor(damage);
				damage = damage.split(" - ")[0];
				dmg = Integer.parseInt(damage.trim());
			} catch (Exception e) { }
		}
		return dmg;
	}
	
	public int getMax(ItemStack item, String value){
		int dmg = 1;
		List<String> lore = item.getItemMeta().getLore();
		if (lore != null && lore.get(0).contains(value)){
			try {
				String damage = (String) lore.get(0).split(": ")[1];
				damage = ChatColor.stripColor(damage);
				damage = damage.split(" - ")[1];
				dmg = Integer.parseInt(damage.trim());
			} catch (Exception e) { }
		}
		return dmg;
	}
	
	@EventHandler
	public void onDuraTake(EntityDamageByEntityEvent e){	
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			PlayerInventory inv = player.getInventory();
			
			if (inv.getHelmet() != null){
				inv.getHelmet().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getChestplate() != null){
				inv.getChestplate().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getLeggings() != null){
				inv.getLeggings().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getBoots() != null){
				inv.getBoots().setDurability((short) 0);
				player.updateInventory();
			}
		}
	}
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent e){	
			Player player = (Player) e.getPlayer();
			PlayerInventory inv = player.getInventory();
			
			if (inv.getHelmet() != null){
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						player.updateInventory();
						inv.getHelmet().setDurability((short) 0);
					}
				}, 30L);
			}
			if (inv.getChestplate() != null){
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						player.updateInventory();
						inv.getChestplate().setDurability((short) 0);
					}
				}, 30L);
			}
			if (inv.getLeggings() != null){
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						player.updateInventory();
						inv.getLeggings().setDurability((short) 0);
					}
				}, 30L);
			}
			if (inv.getBoots() != null){
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						player.updateInventory();
						inv.getBoots().setDurability((short) 0);
					}
				}, 30L);
		}
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
			Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
				@Override
				public void run() {
					player.getItemInHand().setDurability((short) 0);
				}
			}, 30L);
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
				player.updateInventory();
			}
		}
	}
	
	@EventHandler
	public void onItemCraft(CraftItemEvent e){
		e.setCancelled(true);
	}
}
