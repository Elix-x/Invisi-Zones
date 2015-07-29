package code.elix_x.coremods.invisizones.tileentities;

import java.util.UUID;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.net.UpdateRendererMessage;
import code.elix_x.coremods.invisizones.net.UpdateZonerMessage;
import code.elix_x.coremods.invisizones.zones.InvisiZone;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySimpleInvisiZoner extends TileEntity {

	private UUID owner;
	private boolean synced;

	public int partnerX;
	public int partnerY;
	public int partnerZ;

	private InvisiZone zone;

	public TileEntitySimpleInvisiZoner() {
		synced = false;
		partnerX = 0;
		partnerY = 0;
		partnerZ = 0;
	}

	public void sync(TileEntitySimpleInvisiZoner zoner, UUID owner) {
		if(!worldObj.isRemote){
			synced = true;
			zoner.synced = true;

			partnerX = zoner.xCoord;
			partnerY = zoner.yCoord;
			partnerZ = zoner.zCoord;
			zoner.partnerX = xCoord;
			zoner.partnerY = yCoord;
			zoner.partnerZ = zCoord;

			zone = zoner.zone = InvisiZonesManager.get(xCoord, yCoord, zCoord, zoner.xCoord, zoner.yCoord, zoner.zCoord, owner, worldObj.provider.dimensionId);
			
			syncClient();
			zoner.syncClient();
		}
	}

	private void syncClient() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		InvisiZonesBase.net.sendToAll(new UpdateZonerMessage(nbt));
	}
	
	public void syncClient(NBTTagCompound nbt){
		readFromNBT(nbt);
	}

	public void update(){
		if(!worldObj.isRemote){
			if(synced){
				if(worldObj.getTileEntity(partnerX, partnerY, partnerZ) instanceof TileEntitySimpleInvisiZoner){
					/*syncClient();
					((TileEntitySimpleInvisiZoner) worldObj.getTileEntity(partnerX, partnerY, partnerZ)).syncClient();*/
				} else {
					removeTE();
				}
			}
		}
	}

	public void removeTE(){
		if(synced){
			if(!worldObj.isRemote){
				synced = false;
				TileEntity te = worldObj.getTileEntity(partnerX, partnerY, partnerZ);
				if(te instanceof TileEntitySimpleInvisiZoner){
					if(((TileEntitySimpleInvisiZoner) te).synced){
						worldObj.setBlock(partnerX, partnerY, partnerZ, Blocks.air);
					}
				}
				removeZones();
			}
		}
	}

	private void removeZones() {
		if(!worldObj.isRemote){
			InvisiZonesManager.removeZone(zone);
			synced = false;
			partnerX = 0;
			partnerY = 0;
			partnerZ = 0;
			zone = null;
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	public void setOwner(EntityPlayer player){
		owner = player.func_146094_a(player.getGameProfile());
	}

	public UUID getOwnerUUID() {
		return owner;
	}

	public boolean isSynced() {
		return synced;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		owner = UUID.fromString(nbt.getString("owner"));
		synced = nbt.getBoolean("synced");
		if(synced){
			NBTTagCompound partnerData = nbt.getCompoundTag("partnerData");
			partnerX = partnerData.getInteger("partnerX");
			partnerY = partnerData.getInteger("partnerY");
			partnerZ = partnerData.getInteger("partnerZ");
		}
	}

	@Override
	public void setWorldObj(World world) {
		super.setWorldObj(world);
		if(!worldObj.isRemote){
			if(synced){
				if(zone == null){
					zone = InvisiZonesManager.get(xCoord, yCoord, zCoord, partnerX, partnerY, partnerZ, owner, worldObj.provider.dimensionId);
				}
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("owner", owner.toString());
		nbt.setBoolean("synced", synced);
		if(synced){
			NBTTagCompound partnerData = new NBTTagCompound();
			partnerData.setInteger("partnerX", partnerX);
			partnerData.setInteger("partnerY", partnerY);
			partnerData.setInteger("partnerZ", partnerZ);
			nbt.setTag("partnerData", partnerData);
		}
	}
}
