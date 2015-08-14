package code.elix_x.coremods.invisizones.zones;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import code.elix_x.coremods.invisizones.InvisiZonesBase;
import cpw.mods.fml.common.FMLCommonHandler;

public class InvisiZone {

	private AxisAlignedBB box;
	private UUID owner;
	public int dimId;

	private InvisiZone(){

	}

	public InvisiZone(AxisAlignedBB bbox, UUID player, int dim) {
		box = bbox;
		owner = player;
		dimId = dim;
	}

	public boolean renderBlocks(){
		return false;
	}

	public boolean renderBlockHihglights() {
		return false;
	}

	public boolean renderEntities(){
		return false;
	}

	public boolean renderParticles() {
		return false;
	}
	
	public boolean renderBlockAt(EntityPlayer player, int x, int y, int z) {
		if(getPlayerRenderBox().intersectsWith(player.boundingBox)){
			return true;
		} else {
			if(isOwner(player.func_146094_a(player.getGameProfile()))){
				if(InvisiZonesManager.hasGoogles(player)){
					return true;
				}
			}
		}
		return false;
	}

	/*public boolean changeLightOpacityAt(EntityPlayer player, int x, int y, int z) {
		if(box.intersectsWith(player.boundingBox)){
			return true;
		} else if(!box.intersectsWith(player.boundingBox)){
			if(isOwner(player.func_146094_a(player.getGameProfile()))){
				if(InvisiZonesManager.hasGoogles(player)){
					return true;
				}
			}
		}
		return false;
	}*/

	public boolean renderBlockHighLightAt(EntityPlayer player, MovingObjectPosition selector) {
		if(getPlayerRenderBox().intersectsWith(player.boundingBox)){
			return true;
		} else {
			if(isOwner(player.func_146094_a(player.getGameProfile()))){
				if(InvisiZonesManager.hasGoogles(player)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean renderEntityAt(EntityPlayer player, Entity entity) {
		if(getPlayerRenderBox().intersectsWith(player.boundingBox)){
			return true;
		} else {
			if(isOwner(player.func_146094_a(player.getGameProfile()))){
				if(InvisiZonesManager.hasGoogles(player)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean renderParticlesAt(EntityPlayer player, EntityFX particles) {
		if(getPlayerRenderBox().intersectsWith(player.boundingBox)){
			return true;
		} else {
			if(isOwner(player.func_146094_a(player.getGameProfile()))){
				if(InvisiZonesManager.hasGoogles(player)){
					return true;
				}
			}
		}
		return false;
	}

	public void updateAffectedRenderers(EntityPlayer player, int dim) {
		if(dimId == dim){
			Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate((int) box.minX - getRerenderingAddition(), (int) box.minY - getRerenderingAddition(), (int) box.minZ - getRerenderingAddition(), (int) box.maxX + getRerenderingAddition(), (int) box.maxY + getRerenderingAddition(), (int) box.maxZ + getRerenderingAddition());
		}
	}
	
	public int getRerenderingAddition() {
		return 0;
	}
	
	public void relight(EntityPlayer player, int dim) {
		if(dimId == dim){
//			Minecraft.getMinecraft().theWorld.
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("box", writeBoxToNbt(box, new NBTTagCompound()));
		nbt.setString("owner", owner.toString());
		nbt.setInteger("dimId", dimId);
		return nbt;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		box = readBoxFromNBT(nbt.getCompoundTag("box"));
		owner = UUID.fromString(nbt.getString("owner"));
		dimId = nbt.getInteger("dimId");
	}

	public boolean isOwner(UUID player) {
		//		System.out.println("Comparing 2 UUIDs: " + owner + " & " + player + " on " + FMLCommonHandler.instance().getSide());
		return owner.equals(player);
	}

	public boolean inside(Vec3 vec){
		//		return box.isVecInside(vec);
		return vec.xCoord >= box.minX && vec.xCoord <= box.maxX ? (vec.yCoord >= box.minY && vec.yCoord <= box.maxY ? vec.zCoord >= box.minZ && vec.zCoord <= box.maxZ : false) : false;
	}

	public boolean inside(AxisAlignedBB bbox){
		//		return box.intersectsWith(bbox);
		return intersectsWithBorders(box, bbox);
	}

	public AxisAlignedBB getBox(){
		return box;
	}
	
	private AxisAlignedBB getPlayerRenderBox() {
		return box.expand(0.5, 0.5, 0.5);
	}

	public boolean equals(InvisiZone zone){
		return areBoxesEqual(box, zone.box) && isOwner(zone.owner) && dimId == zone.dimId;
	}

	public static boolean areBoxesEqual(AxisAlignedBB bbox, AxisAlignedBB bbox2) {
		return bbox.minX == bbox2.minX && bbox.minY == bbox2.minY && bbox.minZ == bbox2.minZ && bbox.maxX == bbox2.maxX && bbox.maxY == bbox2.maxY && bbox.maxZ == bbox2.maxZ;
	}

	public static boolean intersectsWithBorders(AxisAlignedBB bbox, AxisAlignedBB bbox2) {
		return bbox.maxX >= bbox2.minX && bbox.minX <= bbox2.maxX ? (bbox.maxY >= bbox2.minY && bbox.minY <= bbox2.maxY ? bbox.maxZ >= bbox2.minZ && bbox.minZ <= bbox2.maxZ : false) : false;
	}

	public static NBTTagCompound writeBoxToNbt(AxisAlignedBB bbox, NBTTagCompound nbt) {
		nbt.setDouble("minX", bbox.minX);
		nbt.setDouble("minY", bbox.minY);
		nbt.setDouble("minZ", bbox.minZ);
		nbt.setDouble("maxX", bbox.maxX);
		nbt.setDouble("maxY", bbox.maxY);
		nbt.setDouble("maxZ", bbox.maxZ);
		return nbt;
	}

	public static AxisAlignedBB readBoxFromNBT(NBTTagCompound nbt){
		return AxisAlignedBB.getBoundingBox(nbt.getDouble("minX"), nbt.getDouble("minY"), nbt.getDouble("minZ"), nbt.getDouble("maxX"), nbt.getDouble("maxY"), nbt.getDouble("maxZ"));
	}

	public static NBTTagCompound toNbt(InvisiZone zone){
		return zone.writeToNBT(new NBTTagCompound());
	}

	public static InvisiZone fromNBT(NBTTagCompound nbt) {
		InvisiZone zone = new InvisiZone();
		zone.readFromNBT(nbt);
		return zone;
	}



	@Override
	public String toString() {
		return "Zone owned by " + owner + " in the dimension " + dimId + " within " + box;
	}

}
