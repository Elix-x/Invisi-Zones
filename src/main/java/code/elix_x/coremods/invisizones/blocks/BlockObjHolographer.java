package code.elix_x.coremods.invisizones.blocks;

import code.elix_x.coremods.invisizones.tileentities.TileEntityObjHolographer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockObjHolographer extends CustomRenderedBlockContainer {

	public BlockObjHolographer() {
		super(Material.glass);
		
		setBlockName("objholographer");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityObjHolographer();
	}

}
