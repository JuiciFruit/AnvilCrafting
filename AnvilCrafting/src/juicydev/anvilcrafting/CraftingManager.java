package juicydev.anvilcrafting;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class CraftingManager {

	private static final CraftingManager inst = new CraftingManager();
	private HashMap<ItemStack[], ItemStack> recipes = new HashMap<ItemStack[], ItemStack>();

	/**
	 * @return The crafting manager instance.
	 */
	public static final CraftingManager getInstance() {
		return inst;
	}

	private CraftingManager() {

	}

	/**
	 * Registers a shaped anvil recipe.
	 * 
	 * @param input
	 * @param output
	 */
	public void registerShapedRecipe(ItemStack[] input, ItemStack output) {
		recipes.put(input, output);
	}
	
	/**
	 * Clears the anvil recipes.
	 */
	public void clearRecipes() {
		recipes.clear();
	}

	/**
	 * @return The recipes HashMap.
	 */
	public HashMap<ItemStack[], ItemStack> getRecipes() {
		return recipes;
	}

	/**
	 * Checks to see if an ItemStack array is an anvil recipe.
	 * 
	 * @param iss
	 * @return
	 */
	public static boolean isRecipe(ItemStack[] iss) {
		HashMap<ItemStack[], ItemStack> r = CraftingManager.getInstance()
				.getRecipes();

		for (int i = 0; i < r.size(); i++) {
			ItemStack[] iss2 = (ItemStack[]) r.keySet().toArray()[i];

			if ((iss[0] + " " + iss[1] + " " + iss[2] + " " + iss[3] + " "
					+ iss[4] + " " + iss[5] + " " + iss[6] + " " + iss[7] + " " + iss[8])
					.replace("ITEMSTACK{", "")
					.replace(" x ", "")
					.replace("1", "")
					.replace("2", "")
					.replace("3", "")
					.replace("4", "")
					.replace("5", "")
					.replace("6", "")
					.replace("7", "")
					.replace("8", "")
					.replace("9", "")
					.replace("0", "")
					.equalsIgnoreCase(
							(iss[0] + " " + iss2[1] + " " + iss2[2] + " "
									+ iss2[3] + " " + iss2[4] + " " + iss2[5]
									+ " " + iss2[6] + " " + iss2[7] + " " + iss2[8])
									.replace("ITEMSTACK{", "")
									.replace(" x ", "").replace("1", "")
									.replace("2", "").replace("3", "")
									.replace("4", "").replace("5", "")
									.replace("6", "").replace("7", "")
									.replace("8", "").replace("9", "")
									.replace("0", ""))) {
				return true;
			}
		}

		/*
		 * int k = 0; for (int i = 0; i < r.size(); i++) { ItemStack[] iss2 =
		 * (ItemStack[]) r.keySet().toArray()[i]; k = 0; for (int j = 0; j <
		 * iss2.length; j++) { if (iss[j] != null) { ItemStack is = iss[j];
		 * ItemStack is2 = iss2[j]; if (is.getType() == is2.getType()) { k++; }
		 * } else { if (iss2[j] == null) { k++; } } }
		 * 
		 * if (Integer.valueOf(k).equals(Integer.valueOf("8"))) { return true; }
		 * }
		 */

		/*
		 * if (r.containsKey(iss)) { return false;
		 * 
		 * }
		 */

		return false;
	}

	/**
	 * Gets the ItemStack result of the anvil recipe for the input ItemStack
	 * array.
	 * 
	 * @param iss
	 * @return
	 */
	public static ItemStack getResult(ItemStack[] iss) {
		HashMap<ItemStack[], ItemStack> r = CraftingManager.getInstance()
				.getRecipes();
		/*
		 * int k = 0; for (int i = 0; i < r.size(); i++) { ItemStack[] iss2 =
		 * (ItemStack[]) r.keySet().toArray()[i]; k = 0; for (int j = 0; j <
		 * iss2.length; j++) { if (iss[j].getType().equals(iss2[j].getType())) {
		 * k++; } }
		 * 
		 * if (Integer.valueOf(k).equals(Integer.valueOf("8"))) { ItemStack
		 * result = (ItemStack) r.values().toArray()[i]; return result; } }
		 */

		/*
		 * if (r.containsKey(iss)) { ItemStack is = r.get(iss); return is;
		 * 
		 * }
		 */
		
		for (int i = 0; i < r.size(); i++) {
			ItemStack[] iss2 = (ItemStack[]) r.keySet().toArray()[i];

			if ((iss[0] + " " + iss[1] + " " + iss[2] + " " + iss[3] + " "
					+ iss[4] + " " + iss[5] + " " + iss[6] + " " + iss[7] + " " + iss[8])
					.replace("ITEMSTACK{", "")
					.replace(" x ", "")
					.replace("1", "")
					.replace("2", "")
					.replace("3", "")
					.replace("4", "")
					.replace("5", "")
					.replace("6", "")
					.replace("7", "")
					.replace("8", "")
					.replace("9", "")
					.replace("0", "")
					.equalsIgnoreCase(
							(iss[0] + " " + iss2[1] + " " + iss2[2] + " "
									+ iss2[3] + " " + iss2[4] + " " + iss2[5]
									+ " " + iss2[6] + " " + iss2[7] + " " + iss2[8])
									.replace("ITEMSTACK{", "")
									.replace(" x ", "").replace("1", "")
									.replace("2", "").replace("3", "")
									.replace("4", "").replace("5", "")
									.replace("6", "").replace("7", "")
									.replace("8", "").replace("9", "")
									.replace("0", ""))) {
				ItemStack is = (ItemStack) r.values().toArray()[i];
				return is;
			}
		}

		return null;
	}
}
