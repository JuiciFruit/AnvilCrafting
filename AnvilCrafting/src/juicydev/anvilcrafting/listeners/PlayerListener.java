package juicydev.anvilcrafting.listeners;

import juicydev.anvilcrafting.AnvilCrafting;
import juicydev.anvilcrafting.CraftingManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListener implements Listener {

	public final AnvilCrafting plugin;

	public PlayerListener(AnvilCrafting plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent event) {
		if (event.isCancelled())
			return;

		Player player = event.getPlayer();
		Block bl = event.getClickedBlock();
		Location loc = bl.getLocation();

		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (bl.getType().equals(Material.ANVIL)) {
				if (plugin.anvils.containsKey(loc)) {
					if ((!player.isSneaking() || player.getItemInHand()
							.getType().equals(Material.AIR))) {
						Inventory inv = plugin.anvils.get(loc);

						player.closeInventory();
						player.openInventory(inv);
						event.setCancelled(true);
					}
				} else {
					Inventory inv = Bukkit.createInventory(null,
							InventoryType.DISPENSER);
					plugin.anvils.put(loc, inv);
					player.closeInventory();
					player.openInventory(inv);
					event.setCancelled(true);
				}
			}
		} else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (bl.getType().equals(Material.ANVIL)) {
				ItemStack hand = event.getPlayer().getItemInHand();
				ItemMeta meta = hand.getItemMeta();

				if (hand.getType().equals(Material.STONE_PICKAXE)
						&& meta.getDisplayName().equals(
								ChatColor.WHITE + "Hammer")) {
					if (plugin.anvils.containsKey(loc)) {
						Inventory inv = plugin.anvils.get(loc);

						ItemStack[] iss = AnvilCrafting.invToItemStack(inv);

						if (CraftingManager.isRecipe(iss)) {
							ItemStack is = CraftingManager.getResult(iss);
							for (int i = 0; i <= 8; i++) {
								try {
									ItemStack is2 = inv.getItem(i);
									if (is2 != null) {
										if (is2.getAmount() - 1 >= 1) {
											is2.setAmount(is2.getAmount() - 1);
										} else {
											inv.clear(i);
										}
									}
								} catch (Exception e) {
								}
							}

							if ((hand.getDurability() + 1) < 132) {
								hand.setDurability((short) (hand
										.getDurability() + 1));
								loc.getWorld().playSound(loc, Sound.ANVIL_USE,
										1.0F, 1.0F);
							} else {
								int i = event.getPlayer().getInventory()
										.getHeldItemSlot();
								event.getPlayer().getInventory().clear(i);
								loc.getWorld().playSound(loc,
										Sound.ANVIL_BREAK, 1.0F, 1.0F);
							}

							World world = bl.getWorld();
							world.dropItem(loc.add(0.5, 1.5, 0.5), is);
							String str = "an";
							if (is.getType().toString().toLowerCase()
									.contains("gold")) {
								str = "a";
							}
							event.getPlayer().sendMessage(
									"Succesfully crafted "
											+ str
											+ " "
											+ is.getType().toString()
													.replace('_', ' ')
													.toLowerCase() + ".");
						} else {
							if ((hand.getDurability() + 1) < 132) {
								hand.setDurability((short) (hand
										.getDurability() + 1));
								loc.getWorld().playSound(loc, Sound.ANVIL_LAND,
										1.0F, 1.0F);
							} else {
								int i = event.getPlayer().getInventory()
										.getHeldItemSlot();
								event.getPlayer().getInventory().clear(i);
								loc.getWorld().playSound(loc,
										Sound.ANVIL_BREAK, 1.0F, 1.0F);
							}
							event.getPlayer()
									.sendMessage(
											"Nothing could be made from this combination");
						}
					}

					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemPickup(PlayerPickupItemEvent event) {
		if (event.isCancelled())
			return;

		Item item = event.getItem();
		if (item.hasMetadata("noPickUp")) {
			event.setCancelled(true);
		}
	}
}
