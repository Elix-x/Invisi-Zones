package code.elix_x.coremods.invisizones.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;

public class BlockSimpleZoner extends CustomRenderedBlockContainer {

	public BlockSimpleZoner() {
		super(Material.glass);

		setBlockName("simpleinvisizoner");

		setCreativeTab(InvisiZonesBase.invisitab);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune){
		return new ArrayList<ItemStack>();
	}

	/*@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
		return false;
	}*/

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		if(!world.isRemote){
//			System.out.println("Zoner placed by: " + entity);
			if(entity instanceof EntityPlayer){
				((TileEntitySimpleInvisiZoner) world.getTileEntity(x, y, z)).setOwner((EntityPlayer) entity);
			} else {
				world.setBlock(x, y, z, Blocks.air);
			}
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if(!world.isRemote){
			TileEntity te = world.getTileEntity(x, y, z);
			if(te instanceof TileEntitySimpleInvisiZoner){
				((TileEntitySimpleInvisiZoner) te).update();;
			}
		}
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		if(!world.isRemote){
			TileEntity te = world.getTileEntity(x, y, z);
			if(te instanceof TileEntitySimpleInvisiZoner){
				((TileEntitySimpleInvisiZoner) te).removeTE();
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySimpleInvisiZoner();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
