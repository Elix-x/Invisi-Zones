package code.elix_x.coremods.invisizones.items;

import java.util.UUID;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;

public class ItemWrench extends Item {	
	
	public ItemWrench() {

		setTextureName(InvisiZonesBase.MODID + ":invisiwrench");
		setUnlocalizedName("invisiwrench");

		setCreativeTab(InvisiZonesBase.invisitab);
		
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(!world.isRemote){
			if(itemstack != null){
				if(itemstack.getItem() == InvisiZonesBase.invisiWrench){
					if(player.isSneaking()){
						NBTTagCompound wrenchData = itemstack.stackTagCompound;
						if(wrenchData == null){
							itemstack.stackTagCompound = new NBTTagCompound();
							wrenchData = itemstack.stackTagCompound;
						}
						NBTTagCompound syncingData = new NBTTagCompound();
						syncingData.setInteger("partnerX", 0);
						syncingData.setInteger("partnerY", 0);
						syncingData.setInteger("partnerZ", 0);
						syncingData.setString("partnerOwner", "");
						wrenchData.setTag("syncingData", syncingData);
						wrenchData.setBoolean("syncing", false);
						itemstack.stackTagCompound = wrenchData;
						
						player.addChatComponentMessage(new ChatComponentText("Succesfully cleared settings!"));
					}
				}
			}
		}
		return itemstack;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if(!world.isRemote){
			if(itemstack != null){
				if(itemstack.getItem() == InvisiZonesBase.invisiWrench){
					if(world.getBlock(x, y, z) == InvisiZonesBase.simpleInvisiZoner){
						if(player.isSneaking()){
							TileEntitySimpleInvisiZoner zoner = (TileEntitySimpleInvisiZoner) world.getTileEntity(x, y, z);
							world.setBlock(x, y, z, Blocks.air);
							world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(InvisiZonesBase.simpleInvisiZoner, zoner.isSynced() ? 2 : 1)));
							return true;
						} else {
							NBTTagCompound wrenchData = itemstack.stackTagCompound;
							if(wrenchData == null){
								itemstack.stackTagCompound = new NBTTagCompound();
								wrenchData = itemstack.stackTagCompound;
							}
							if(!wrenchData.hasKey("syncingData")){
								NBTTagCompound syncingData = new NBTTagCompound();
								syncingData.setInteger("partnerX", 0);
								syncingData.setInteger("partnerY", 0);
								syncingData.setInteger("partnerZ", 0);
								syncingData.setString("partnerOwner", "");
								wrenchData.setTag("syncingData", syncingData);
								wrenchData.setBoolean("syncing", false);
								itemstack.stackTagCompound = wrenchData;
							}

							TileEntitySimpleInvisiZoner zoner = (TileEntitySimpleInvisiZoner) world.getTileEntity(x, y, z);
							if(!wrenchData.getBoolean("syncing")){
								if(!zoner.isSynced()){
									if(zoner.getOwnerUUID().equals(player.func_146094_a(player.getGameProfile()))){
										NBTTagCompound syncingData = wrenchData.getCompoundTag("syncingData");
										syncingData.setInteger("partnerX", zoner.xCoord);
										syncingData.setInteger("partnerY", zoner.yCoord);
										syncingData.setInteger("partnerZ", zoner.zCoord);
										syncingData.setInteger("partneDim", zoner.getWorldObj().provider.dimensionId);
										syncingData.setString("partnerOwner", zoner.getOwnerUUID().toString());

										wrenchData.setBoolean("syncing", true);

										player.addChatComponentMessage(new ChatComponentText("Succesfully saved data!"));
										return true;
									}
								}
							} else {
								if(!zoner.isSynced()){
									if(zoner.getOwnerUUID().equals(player.func_146094_a(player.getGameProfile()))){
										NBTTagCompound syncingData = wrenchData.getCompoundTag("syncingData");
										if(zoner.getOwnerUUID().equals(UUID.fromString(syncingData.getString("partnerOwner")))){
											if(syncingData.getInteger("partnerDim") == zoner.getWorldObj().provider.dimensionId);
											zoner.sync((TileEntitySimpleInvisiZoner) world.getTileEntity(syncingData.getInteger("partnerX"), syncingData.getInteger("partnerY"), syncingData.getInteger("partnerZ")), player.func_146094_a(player.getGameProfile()));
											wrenchData.setBoolean("syncing", false);
											player.addChatComponentMessage(new ChatComponentText("Succesfully linked zoners!"));
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
		//		return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_,p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
	}
}
