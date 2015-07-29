package code.elix_x.coremods.invisizones.core;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManagerClient;

public class InvisiZoneHooks {

	public static boolean renderEntity(Entity entity){
		return InvisiZonesManagerClient.renderEntity(entity);
	}
	
	public static boolean renderParticles(EntityFX particles){
		return InvisiZonesManagerClient.renderParticles(particles);
	}
	
	public static boolean doGetBlockInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.doGetBlockInsideChunk(chunk, x, y, z);
	}
	
	public static Block getBlockInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.getBlockInsideChunk(chunk, x, y, z);
	}

}
