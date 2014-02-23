package juicydev.anvilcrafting.recipes;

import juicydev.anvilcrafting.AnvilCrafting;
import juicydev.anvilcrafting.CraftingManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Recipes {
	public final AnvilCrafting plugin;

	public Recipes(AnvilCrafting plugin) {
		this.plugin = plugin;
	}
	
	public static void reloadRecipes(CraftingManager cm) {
		clearRecipes(cm);
		addRecipes(cm);
	}
	
	public static void clearRecipes(CraftingManager cm) {
		cm.clearRecipes();
	}

	public static void addRecipes(CraftingManager cm) {
		ItemStack iron = new ItemStack(Material.IRON_INGOT, 1, (short) 0);
		ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1, (short) 0);
		ItemStack stick = new ItemStack(Material.STICK, 1, (short) 0);
		
		// Sword
		ItemStack[] ironSword1 = {iron, null, null, iron, null, null, stick, null, null};
		ItemStack[] ironSword2 = {null, iron, null, null, iron, null, null, stick, null};
		ItemStack[] ironSword3 = {null, null, iron, null, null, iron, null, null, stick};
		ItemStack[] goldSword1 = {gold, null, null, gold, null, null, stick, null, null};
		ItemStack[] goldSword2 = {null, gold, null, null, gold, null, null, stick, null};
		ItemStack[] goldSword3 = {null, null, gold, null, null, gold, null, null, stick};
		ItemStack ironSwordItem = new ItemStack(Material.IRON_SWORD, 1, (short) 0);
		ItemStack goldSwordItem = new ItemStack(Material.GOLD_SWORD, 1, (short) 0);
		cm.registerShapedRecipe(ironSword1, ironSwordItem);
		cm.registerShapedRecipe(ironSword2, ironSwordItem);
		cm.registerShapedRecipe(ironSword3, ironSwordItem);
		cm.registerShapedRecipe(goldSword1, goldSwordItem);
		cm.registerShapedRecipe(goldSword2, goldSwordItem);
		cm.registerShapedRecipe(goldSword3, goldSwordItem);
		
		// Pickaxe
		ItemStack[] ironPickaxe = {iron, iron, iron, null, stick, null, null, stick, null};
		ItemStack[] goldPickaxe = {gold, gold, gold, null, stick, null, null, stick, null};
		ItemStack ironPickaxeItem = new ItemStack(Material.IRON_PICKAXE, 1, (short) 0);
		ItemStack goldPickaxeItem = new ItemStack(Material.GOLD_PICKAXE, 1, (short) 0);
		cm.registerShapedRecipe(ironPickaxe, ironPickaxeItem);
		cm.registerShapedRecipe(goldPickaxe, goldPickaxeItem);
		
		// Spade
		ItemStack[] ironSpade1 = {iron, null, null, stick, null, null, stick, null, null};
		ItemStack[] ironSpade2 = {null, iron, null, null, stick, null, null, stick, null};
		ItemStack[] ironSpade3 = {null, null, iron, null, null, stick, null, null, stick};
		ItemStack[] goldSpade1 = {gold, null, null, stick, null, null, stick, null, null};
		ItemStack[] goldSpade2 = {null, gold, null, null, stick, null, null, stick, null};
		ItemStack[] goldSpade3 = {null, null, gold, null, null, stick, null, null, stick};
		ItemStack ironSpadeItem = new ItemStack(Material.IRON_SPADE, 1, (short) 0);
		ItemStack goldSpadeItem = new ItemStack(Material.GOLD_SPADE, 1, (short) 0);
		cm.registerShapedRecipe(ironSpade1, ironSpadeItem);
		cm.registerShapedRecipe(ironSpade2, ironSpadeItem);
		cm.registerShapedRecipe(ironSpade3, ironSpadeItem);
		cm.registerShapedRecipe(goldSpade1, goldSpadeItem);
		cm.registerShapedRecipe(goldSpade2, goldSpadeItem);
		cm.registerShapedRecipe(goldSpade3, goldSpadeItem);
		
		// Axe
		ItemStack[] ironAxe1 = {iron, iron, null, iron, stick, null, null, stick, null};
		ItemStack[] ironAxe2 = {null, iron, iron, null, stick, iron, null, stick, null};
		ItemStack[] goldAxe1 = {gold, gold, null, gold, stick, null, null, stick, null};
		ItemStack[] goldAxe2 = {null, gold, gold, null, stick, gold, null, stick, null};
		ItemStack ironAxeItem = new ItemStack(Material.IRON_AXE, 1, (short) 0);
		ItemStack goldAxeItem = new ItemStack(Material.GOLD_AXE, 1, (short) 0);
		cm.registerShapedRecipe(ironAxe1, ironAxeItem);
		cm.registerShapedRecipe(ironAxe2, ironAxeItem);
		cm.registerShapedRecipe(goldAxe1, goldAxeItem);
		cm.registerShapedRecipe(goldAxe2, goldAxeItem);
		
		// Hoe
		ItemStack[] ironHoe1 = {iron, iron, null, null, stick, null, null, stick, null};
		ItemStack[] ironHoe2 = {null, iron, iron, null, stick, null, null, stick, null};
		ItemStack[] goldHoe1 = {gold, gold, null, null, stick, null, null, stick, null};
		ItemStack[] goldHoe2 = {null, gold, gold, null, stick, null, null, stick, null};
		ItemStack ironHoeItem = new ItemStack(Material.IRON_HOE, 1, (short) 0);
		ItemStack goldHoeItem = new ItemStack(Material.GOLD_HOE, 1, (short) 0);
		cm.registerShapedRecipe(ironHoe1, ironHoeItem);
		cm.registerShapedRecipe(ironHoe2, ironHoeItem);
		cm.registerShapedRecipe(goldHoe1, goldHoeItem);
		cm.registerShapedRecipe(goldHoe2, goldHoeItem);
		
		// Helmet
		ItemStack[] ironHelmet1 = {iron, iron, iron, iron, null, iron, null, null, null};
		ItemStack[] ironHelmet2 = {null, null, null, iron, iron, iron, iron, null, iron};
		ItemStack[] goldHelmet1 = {gold, gold, gold, gold, null, gold, null, null, null};
		ItemStack[] goldHelmet2 = {null, null, null, gold, gold, gold, gold, null, gold};
		ItemStack ironHelmetItem = new ItemStack(Material.IRON_HELMET, 1, (short) 0);
		ItemStack goldHelmetItem = new ItemStack(Material.GOLD_HELMET, 1, (short) 0);
		cm.registerShapedRecipe(ironHelmet1, ironHelmetItem);
		cm.registerShapedRecipe(ironHelmet2, ironHelmetItem);
		cm.registerShapedRecipe(goldHelmet1, goldHelmetItem);
		cm.registerShapedRecipe(goldHelmet2, goldHelmetItem);
		
		// Chestplate
		ItemStack[] ironChestplate = {iron, null, iron, iron, iron, iron, iron, iron, iron};
		ItemStack[] goldChestplate = {gold, null, gold, gold, gold, gold, gold, gold, gold};
		ItemStack ironChestplateItem = new ItemStack(Material.IRON_CHESTPLATE, 1, (short) 0);
		ItemStack goldChestplateItem = new ItemStack(Material.GOLD_CHESTPLATE, 1, (short) 0);
		cm.registerShapedRecipe(ironChestplate, ironChestplateItem);
		cm.registerShapedRecipe(goldChestplate, goldChestplateItem);
		
		// Leggings
		ItemStack[] ironLeggings = {iron, iron, iron, iron, null, iron, iron, null, iron};
		ItemStack[] goldLeggings = {gold, gold, gold, gold, null, gold, gold, null, gold};
		ItemStack ironLeggingsItem = new ItemStack(Material.IRON_LEGGINGS, 1, (short) 0);
		ItemStack goldLeggingsItem = new ItemStack(Material.GOLD_LEGGINGS, 1, (short) 0);
		cm.registerShapedRecipe(ironLeggings, ironLeggingsItem);
		cm.registerShapedRecipe(goldLeggings, goldLeggingsItem);
		
		// Boots
		ItemStack[] ironBoots1 = {iron, null, iron, iron, null, iron, null, null, null};
		ItemStack[] ironBoots2 = {null, null, null, iron, null, iron, iron, null, iron};
		ItemStack[] goldBoots1 = {gold, null, gold, gold, null, gold, null, null, null};
		ItemStack[] goldBoots2 = {null, null, null, gold, null, gold, gold, null, gold};
		ItemStack ironBootsItem = new ItemStack(Material.IRON_BOOTS, 1, (short) 0);
		ItemStack goldBootsItem = new ItemStack(Material.GOLD_BOOTS, 1, (short) 0);
		cm.registerShapedRecipe(ironBoots1, ironBootsItem);
		cm.registerShapedRecipe(ironBoots2, ironBootsItem);
		cm.registerShapedRecipe(goldBoots1, goldBootsItem);
		cm.registerShapedRecipe(goldBoots2, goldBootsItem);
		
		// Horse Armour
		ItemStack leather = new ItemStack(Material.LEATHER, 1, (short) 0);
		ItemStack[] ironHorseArmour = {null, leather, ironHelmetItem, iron, ironChestplateItem, iron, leather, null, leather};
		ItemStack[] goldHorseArmour = {null, leather, goldHelmetItem, gold, goldChestplateItem, gold, leather, null, leather};
		ItemStack ironHorseArmourItem = new ItemStack(Material.IRON_BARDING, 1, (short) 0);
		ItemStack goldHorseArmourItem = new ItemStack(Material.GOLD_BARDING, 1, (short) 0);
		cm.registerShapedRecipe(ironHorseArmour, ironHorseArmourItem);
		cm.registerShapedRecipe(goldHorseArmour, goldHorseArmourItem);
	}
}
