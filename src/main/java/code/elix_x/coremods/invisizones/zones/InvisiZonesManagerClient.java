package code.elix_x.coremods.invisizones.zones;

import code.elix_x.coremods.invisizones.entities.proprieties.InvisiExtendedEntityProperties;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InvisiZonesManagerClient {
	
	@SideOnly(Side.CLIENT)
	public static void setZonesClient(){
		InvisiZonesManager.checkPlayerAndUpdateRenderer(Minecraft.getMinecraft().thePlayer);
		InvisiZonesManager.updateRenderer(Minecraft.getMinecraft().thePlayer);
	}
	
	@SideOnly(Side.CLIENT)
	public static void updateRendererDefault() {
		InvisiZonesManager.updateRenderer(Minecraft.getMinecraft().thePlayer);
	}
	
	/*
	 * Hooks
	 */	

	@SideOnly(Side.CLIENT)
	public static boolean doGetBlockInsideChunk(Chunk chunk, int x, int y, int z){
		if(chunk.worldObj.isRemote){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			x = chunk.xPosition * 16 + x;
			z = chunk.zPosition * 16 + z;
			for(InvisiZone zone : InvisiZonesManager.getZones()){
				if(!zone.renderBlocks()){
					if(zone.dimId == player.worldObj.provider.dimensionId){
						if(zone.inside(Vec3.createVectorHelper(x, y, z))){
							if(!zone.renderBlockAt(player, x, y, z)){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public static Block getBlockInsideChunk(Chunk chunk, int x, int y, int z){
		return Blocks.air;
	}

	@SideOnly(Side.CLIENT)
	public static boolean renderEntity(Entity entity) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		for(InvisiZone zone : InvisiZonesManager.getZones()){
			if(!zone.renderEntities()){
				if(zone.dimId == player.worldObj.provider.dimensionId){
					if(zone.inside(entity.boundingBox)){
						if(!zone.renderEntityAt(player, entity)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public static boolean renderParticles(EntityFX particles) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		for(InvisiZone zone : InvisiZonesManager.getZones()){
			if(!zone.renderParticles()){
				if(zone.dimId == player.worldObj.provider.dimensionId){
					if(zone.inside(particles.boundingBox)){
						if(!zone.renderParticlesAt(player, particles)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
