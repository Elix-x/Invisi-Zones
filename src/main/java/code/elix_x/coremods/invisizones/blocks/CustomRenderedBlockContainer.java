package code.elix_x.coremods.invisizones.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class CustomRenderedBlockContainer extends BlockContainer {

	protected CustomRenderedBlockContainer(Material material) {
		super(material);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
