package juicydev.anvilcrafting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

import juicydev.anvilcrafting.listeners.BlockListener;
import juicydev.anvilcrafting.listeners.InventoryListener;
import juicydev.anvilcrafting.listeners.PlayerListener;
import juicydev.anvilcrafting.recipes.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilCrafting extends JavaPlugin {

	private final Server s = getServer();

	public HashMap<Location, Inventory> anvils = new HashMap<Location, Inventory>();

	public AnvilCrafting mainPlugin;
	public final String name = "AnvilCrafting";
	public String loggerPrefix = "[AnvilCrafting] ";
	public Logger logger = Logger.getLogger("Minecraft.AnvilCrafting.JuicyDev");

	public void onDisable() {
		saveDatabase();
		Recipes.clearRecipes(CraftingManager.getInstance());
		log("Thank you for using AnvilCrafting by JuicyDev!");
	}

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		mainPlugin = this;

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			log(e);
		}

		pm.registerEvents(new InventoryListener(this), this);
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new BlockListener(this), this);

		/*
		 * s.getScheduler().scheduleSyncRepeatingTask(mainPlugin, new Runnable()
		 * {
		 * 
		 * @Override public void run() { for (Location loc : anvils.keySet()) {
		 * Inventory inv = anvils.get(loc);
		 * 
		 * ItemStack[] iss = { inv.getItem(0), inv.getItem(1), inv.getItem(2),
		 * inv.getItem(3), inv.getItem(4), inv.getItem(5), inv.getItem(6),
		 * inv.getItem(7), inv.getItem(8) };
		 * 
		 * log("Testing location " + loc.getBlockX() + " " + loc.getBlockY() +
		 * " " + loc.getBlockZ()); log("Location recipe " + iss[0] + " " +
		 * iss[1] + " " + iss[2] + " " + iss[3] + " " + iss[4] + " " + iss[5] +
		 * " " + iss[6] + " " + iss[7] + " " + iss[8]); log("");
		 * 
		 * if (CraftingManager.isRecipe(iss)) {
		 * log("Succeful recipe located at location " + loc.getBlockX() + " " +
		 * loc.getBlockY() + " " + loc.getBlockZ());
		 * 
		 * if (CraftingManager.getResult(iss) != null) {
		 * log("Succeful item drop at location " + loc.getBlockX() + " " +
		 * loc.getBlockY() + " " + loc.getBlockZ()); World world =
		 * loc.getWorld(); Item item = world.dropItem(loc.add(0.5, 1.1, 0.5),
		 * CraftingManager.getResult(iss)); item.setMetadata("noPickUp", new
		 * FixedMetadataValue(mainPlugin, true)); } } } } }, 0L, (long) (20 *
		 * 5)); // Fires every 5 seconds
		 */

		s.getScheduler().scheduleSyncDelayedTask(mainPlugin, new Runnable() {
			@Override
			public void run() {
				loadDatabase();
				Recipes.addRecipes(CraftingManager.getInstance());

				/* Fires every 60 seconds as database backup, in case of crash */
				s.getScheduler().scheduleSyncRepeatingTask(mainPlugin,
						new Runnable() {
							@Override
							public void run() {
								saveDatabase();
							}
						}, 0L, (long) (20 * 60));
			}
		}, (long) 10);

		if (!getDataFolder().exists())
			getDataFolder().mkdirs();

		registerRecipes();

		log("Thank you for using AnvilCrafting by JuicyDev!");
	}

	/* ===== Methods ===== */

	public void noPerm(Player player) {
		err(player, "You do not have permission to do this.");
	}

	public void msg(Player player, String str) {
		player.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString()
				+ loggerPrefix + ChatColor.YELLOW + str);
	}

	public void msg(CommandSender sender, String str) {
		sender.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString()
				+ loggerPrefix + ChatColor.YELLOW + str);
	}

	public void err(Player player, String str) {
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString()
				+ loggerPrefix + ChatColor.RED + str);
	}

	public void err(CommandSender sender, String str) {
		sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString()
				+ loggerPrefix + ChatColor.RED + str);
	}

	public void log(String text) {
		logger.info(loggerPrefix + text);
	}

	public void log(Throwable e) {
		logger.severe(loggerPrefix + e.toString());
		e.printStackTrace();
	}

	public void registerRecipes() {
		// Remove Old Anvil Recipe
		Server s = getServer();
		Iterator<Recipe> it = s.recipeIterator();
		Recipe recipe;
		while (it.hasNext()) {
			recipe = it.next();
			if (recipe != null
					&& recipe.getResult().getType().equals(Material.ANVIL)) {
				it.remove();
			}
		}

		// Add New Anvil Recipe
		ShapedRecipe anvil = new ShapedRecipe(new ItemStack(Material.ANVIL, 1));
		anvil.shape(new String[] { "iii", " i ", "iii" });
		anvil.setIngredient(Character.valueOf('i'), Material.IRON_INGOT);
		s.addRecipe(anvil);

		// Add Diamond Horse Armour Recipe
		ShapedRecipe diaHorse = new ShapedRecipe(new ItemStack(
				Material.DIAMOND_BARDING, 1));
		diaHorse.shape(new String[] { " lh", "dcd", "l l" });
		diaHorse.setIngredient(Character.valueOf('l'), Material.LEATHER);
		diaHorse.setIngredient(Character.valueOf('h'), Material.DIAMOND_HELMET);
		diaHorse.setIngredient(Character.valueOf('d'), Material.DIAMOND);
		diaHorse.setIngredient(Character.valueOf('c'),
				Material.DIAMOND_CHESTPLATE);
		s.addRecipe(diaHorse);

		// Add Hammer Recipe
		ItemStack forgeHammerItem = new ItemStack(Material.STONE_PICKAXE, 1,
				(short) 0);
		ItemMeta forgeHammerMeta = forgeHammerItem.getItemMeta();
		forgeHammerMeta.setDisplayName(ChatColor.WHITE + "Hammer");
		List<String> lore = (Arrays.asList(ChatColor.GRAY
				+ ChatColor.ITALIC.toString()
				+ "Required to use anvils for crafting."));
		forgeHammerMeta.setLore(lore);
		forgeHammerItem.setItemMeta(forgeHammerMeta);
		ShapedRecipe forgeHammer = new ShapedRecipe(forgeHammerItem);
		forgeHammer.shape(new String[] { "csc", "csc", " s " });
		forgeHammer.setIngredient(Character.valueOf('c'), Material.COBBLESTONE);
		forgeHammer.setIngredient(Character.valueOf('s'), Material.STICK);
		s.addRecipe(forgeHammer);

		// Remove Iron Recipes
		Iterator<Recipe> it2 = s.recipeIterator();
		Recipe recipe2;
		while (it2.hasNext()) {
			recipe2 = it2.next();
			Material[] ims = { Material.IRON_SWORD, Material.IRON_PICKAXE,
					Material.IRON_SPADE, Material.IRON_AXE, Material.IRON_HOE,
					Material.IRON_HELMET, Material.IRON_CHESTPLATE,
					Material.IRON_LEGGINGS, Material.IRON_BOOTS,
					Material.SHEARS };
			if (recipe2 != null) {
				for (Material im : ims) {
					if (recipe2.getResult().getType().equals(im)) {
						it2.remove();
					}
				}
			}
		}

		// Remove Gold Recipes
		Iterator<Recipe> it3 = s.recipeIterator();
		Recipe recipe3;
		while (it3.hasNext()) {
			recipe3 = it3.next();
			Material[] gms = { Material.GOLD_SWORD, Material.GOLD_PICKAXE,
					Material.GOLD_SPADE, Material.GOLD_AXE, Material.GOLD_HOE,
					Material.GOLD_HELMET, Material.GOLD_CHESTPLATE,
					Material.GOLD_LEGGINGS, Material.GOLD_BOOTS };
			if (recipe3 != null) {
				for (Material gm : gms) {
					if (recipe3.getResult().getType().equals(gm)) {
						it3.remove();
					}
				}
			}
		}
	}

	public static ItemStack[] invToItemStack(Inventory inv) {
		ItemStack[] iss = { inv.getItem(0), inv.getItem(1), inv.getItem(2),
				inv.getItem(3), inv.getItem(4), inv.getItem(5), inv.getItem(6),
				inv.getItem(7), inv.getItem(8) };

		return iss;
	}

	public void loadDatabase() {
		Scanner scanner = null;
		try {
			File directory = new File(getDataFolder() + File.separator
					+ "anvils");
			if (!directory.isDirectory()) {
				directory.mkdirs();
			}
			String[] directoryContents = directory.list();

			anvils.clear();

			for (String fileName : directoryContents) {
				if (fileName.endsWith(".dat")) {
					String[] locDataFromFileName = fileName.substring(0,
							fileName.length() - 4).split(";");

					double x = Double.parseDouble(locDataFromFileName[0]);
					double y = Double.parseDouble(locDataFromFileName[1]);
					double z = Double.parseDouble(locDataFromFileName[2]);
					String worldUUID = String.valueOf(locDataFromFileName[3]);

					Inventory inv = Bukkit.createInventory(null,
							InventoryType.DISPENSER);

					scanner = new Scanner(new FileInputStream(new File(
							getDataFolder() + File.separator + "anvils",
							fileName)), "UTF-8");

					int i = 0;
					while (scanner.hasNextLine()) {
						String nextLine = scanner.nextLine().replace("\n", "")
								.replace("\r", "");

						if ((!nextLine.equalsIgnoreCase(""))
								&& (!nextLine.equalsIgnoreCase(" "))) {
							String[] lineSplit = nextLine.split(";");

							for (String line : lineSplit) {
								String[] data = line.split(":");

								String item = data[0];
								int amount = Integer.parseInt(data[1]);
								short damage = Short.parseShort(data[2]);
								String name = data[3];

								if (item.equalsIgnoreCase("AIR")) {
									i++;
								} else {
									ItemStack is = new ItemStack(
											Material.getMaterial(item), amount,
											damage);
									if (!name.equalsIgnoreCase("null")) {
										ItemMeta meta = is.getItemMeta();
										meta.setDisplayName(name);
										is.setItemMeta(meta);
									}
									inv.setItem(i, is);
									i++;
								}
							}
						}
					}

					UUID uid = UUID.fromString(worldUUID);
					World world = getServer().getWorld(uid);
					Location loc = new Location(world, x, y, z);

					anvils.put(loc, inv);
				}
			}
		} catch (Exception e) {
			log(e);
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	public void saveDatabase() {
		try {
			File directory = new File(getDataFolder() + File.separator
					+ "anvils");
			if (!directory.exists())
				directory.mkdirs();
			for (File file : directory.listFiles()) {
				file.delete();
			}

			for (int i = 0; i < anvils.size(); i++) {
				Location loc = (Location) anvils.keySet().toArray()[i];
				double x = loc.getBlockX();
				double y = loc.getBlockY();
				double z = loc.getBlockZ();
				String world = loc.getWorld().getUID().toString();

				Writer out = new OutputStreamWriter(new FileOutputStream(
						new File(getDataFolder() + File.separator + "anvils", x
								+ ";" + y + ";" + z + ";" + world + ".dat")),
						"UTF-8");

				Inventory inv = (anvils.get(anvils.keySet().toArray()[i]));
				for (int j = 0; j <= 8; j++) {
					if (inv.getItem(j) != null) {
						ItemStack is = inv.getItem(j);
						ItemMeta meta = is.getItemMeta();
						out.write(is.getType().name() + ":" + is.getAmount()
								+ ":" + is.getDurability() + ":"
								+ meta.getDisplayName() + ";" + "\n");
					} else {
						out.write("AIR" + ":" + "0" + ":" + "0" + ":" + "null"
								+ ";" + "\n");
					}
				}

				out.flush();
				out.close();

			}
		} catch (Exception e) {
			log(e);
		}
	}
}
