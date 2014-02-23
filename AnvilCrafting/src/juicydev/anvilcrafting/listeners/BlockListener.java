package juicydev.anvilcrafting.listeners;

import juicydev.anvilcrafting.AnvilCrafting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockListener implements Listener {

	public final AnvilCrafting plugin;

	public BlockListener(AnvilCrafting plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled())
			return;

		Block bl = event.getBlockPlaced();
		Location loc = bl.getLocation();

		if (bl.getType().equals(Material.ANVIL)) {
			Inventory inv = Bukkit.createInventory(null,
					InventoryType.DISPENSER);
			plugin.anvils.put(loc, inv);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled())
			return;

		Block bl = event.getBlock();
		Location loc = bl.getLocation();

		if (bl.getType().equals(Material.ANVIL)) {
			if (plugin.anvils.containsKey(loc)) {
				ItemStack hand = event.getPlayer().getItemInHand();
				ItemMeta meta = hand.getItemMeta();

				if (hand.getType().equals(Material.STONE_PICKAXE)
						&& meta.getDisplayName().equals(
								ChatColor.WHITE + "Hammer")) {
					event.setCancelled(true);
				} else {
					Inventory inv = plugin.anvils.get(loc);
					for (int i = 0; i <= 8; i++) {
						ItemStack is = inv.getItem(i);
						if (is != null) {
							bl.getWorld().dropItemNaturally(loc, is);
						}
					}
					plugin.anvils.remove(loc);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockDamage(BlockDamageEvent event) {
		if (event.isCancelled())
			return;

		Block bl = event.getBlock();
		Location loc = bl.getLocation();

		if (bl.getType().equals(Material.ANVIL)) {
			if (plugin.anvils.containsKey(loc)) {
				ItemStack hand = event.getPlayer().getItemInHand();
				ItemMeta meta = hand.getItemMeta();

				if (hand.getType().equals(Material.STONE_PICKAXE)
						&& meta.getDisplayName().equals(
								ChatColor.WHITE + "Hammer")) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAnvilFall(EntityChangeBlockEvent event) {
		if (event.isCancelled())
			return;

		Block bl = event.getBlock();
		if (bl.getType().equals(Material.ANVIL)) {
			Location loc = bl.getLocation();
			if (plugin.anvils.containsKey(loc)) {
				event.setCancelled(true);
			}
		}
	}
}
