package code.elix_x.coremods.invisizones.googles;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InvisiGooglesHelper {

	public static final String INVISIGOOGLESACTIVENBT = "InvisiGooglesActive";

	public static ItemStack getDefaultGoogles(){
		ItemStack googles = new ItemStack(InvisiZonesBase.invisigoogles);
		setGooglesActive(googles, true);
		return googles;
	}

	public static boolean canBeGoogles(ItemStack itemstack){
		try {
			return itemstack.getItem().isValidArmor(itemstack, 0, null);
		} catch(NullPointerException e){
			return false;
		}
	}

	public static boolean areGoogles(ItemStack itemstack){
		return itemstack != null && itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey(INVISIGOOGLESACTIVENBT);
	}

	public static boolean areGooglesActive(ItemStack itemstack){
		return areGoogles(itemstack) && itemstack.stackTagCompound.getBoolean(INVISIGOOGLESACTIVENBT);
	}

	public static void setGooglesActive(ItemStack itemstack, boolean active){
		if(itemstack.stackTagCompound == null) itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setBoolean(INVISIGOOGLESACTIVENBT, active);
	}

}
