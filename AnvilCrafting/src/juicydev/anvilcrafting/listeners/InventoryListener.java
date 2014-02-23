package juicydev.anvilcrafting.listeners;

import juicydev.anvilcrafting.AnvilCrafting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

	public final AnvilCrafting plugin;

	public InventoryListener(AnvilCrafting plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInteract(InventoryClickEvent event) {
		if (event.isCancelled())
			return;

		if (event.getInventory().getType().equals(InventoryType.DISPENSER)) {
			if (!(event.getInventory().getHolder() == null)) {
				return;
			}
			if (event.isShiftClick())
				event.setCancelled(true);
		}
	}
}
