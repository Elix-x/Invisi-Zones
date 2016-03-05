package code.elix_x.coremods.invisizones.core;

import code.elix_x.coremods.invisizones.zones.InvisiZonesManagerClient;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

public class InvisiZoneHooks {

	@SideOnly(Side.CLIENT)
	public static boolean renderEntity(Entity entity){
		return InvisiZonesManagerClient.renderEntity(entity);
	}

	@SideOnly(Side.CLIENT)
	public static boolean renderParticles(EntityFX particles){
		return InvisiZonesManagerClient.renderParticles(particles);
	}

	public static boolean doGetBlockInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.doGetBlockInsideChunk(chunk, x, y, z);
	}

	public static Block getBlockInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.getBlockInsideChunk(chunk, x, y, z);
	}

	public static boolean doGetBlockMetadataInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.doGetBlockMetadataInsideChunk(chunk, x, y, z);
	}

	public static int getBlockMetadataInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.getBlockMetadataInsideChunk(chunk, x, y, z);
	}
	public static boolean doGetTileEntityInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.doGetTileEntityInsideChunk(chunk, x, y, z);
	}

	public static TileEntity getTileEntityInsideChunk(Chunk chunk, int x, int y, int z){
		return InvisiZonesManagerClient.getTileEntityInsideChunk(chunk, x, y, z);
	}
}
