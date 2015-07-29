package code.elix_x.coremods.invisizones.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityObjHolographer extends TileEntity {
	
	public String modelName;
	
	public int offsetX;
	public int offsetY;
	public int offsetZ;
	
	public int rotationX;
	public int rotationY;
	public int rotationZ;
	
	public int scaleX;
	public int scaleY;
	public int scaleZ;
	
	public TileEntityObjHolographer() {
		modelName = "";
		
		offsetX = 0;
		offsetY = 0;
		offsetZ = 0;
		
		rotationX = 0;
		rotationY = 0;
		rotationZ = 0;
		
		scaleX = 1;
		scaleY = 1;
		scaleZ = 1;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("modelName", modelName);
		
		nbt.setInteger("offsetX", offsetX);
		nbt.setInteger("offsetY", offsetY);
		nbt.setInteger("offsetZ", offsetZ);
		
		nbt.setInteger("rotationX", rotationX);
		nbt.setInteger("rotationY", rotationY);
		nbt.setInteger("rotationZ", rotationZ);
		
		nbt.setInteger("scaleX", scaleX);
		nbt.setInteger("scaleY", scaleY);
		nbt.setInteger("scaleZ", scaleZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		modelName = nbt.getString("modelName");
		
		offsetX = nbt.getInteger("offsetX");
		offsetY = nbt.getInteger("offsetY");
		offsetZ = nbt.getInteger("offsetZ");
		
		rotationX = nbt.getInteger("rotationX");
		rotationY = nbt.getInteger("rotationY");
		rotationZ = nbt.getInteger("rotationZ");
		
		scaleX = nbt.getInteger("scaleX");
		scaleY = nbt.getInteger("scaleY");
		scaleZ = nbt.getInteger("scaleZ");
	}

}
