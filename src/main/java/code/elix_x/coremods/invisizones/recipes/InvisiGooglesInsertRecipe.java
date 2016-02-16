package code.elix_x.coremods.invisizones.recipes;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.googles.InvisiGooglesHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class InvisiGooglesInsertRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting grid, World world) {
		boolean googles = false;
		boolean helmet = false;
		int items = 0;
		for(int i = 0; i < grid.getSizeInventory(); i++){
			ItemStack itemstack = grid.getStackInSlot(i);
			if(itemstack != null){
				items++;
				if(itemstack.getItem() == InvisiZonesBase.invisigoogles){
					googles = true;
				} else if(InvisiGooglesHelper.canBeGoogles(itemstack)){
					helmet = true;
				}
			}
		}
		return googles && helmet && items == 2;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting grid) {
		ItemStack googles = null;
		ItemStack helmet = null;
		int items = 0;
		for(int i = 0; i < grid.getSizeInventory(); i++){
			ItemStack itemstack = grid.getStackInSlot(i);
			if(itemstack != null){
				items++;
				if(itemstack.getItem() == InvisiZonesBase.invisigoogles){
					googles = itemstack;
				} else if(InvisiGooglesHelper.canBeGoogles(itemstack)){
					helmet = itemstack;
				}
			}
		}
		if(googles != null && helmet != null && items == 2){
			InvisiGooglesHelper.setGooglesActive(helmet, InvisiGooglesHelper.areGooglesActive(googles));
			return helmet;
		} else {
			return null;
		}
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
