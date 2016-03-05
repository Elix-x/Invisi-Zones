package code.elix_x.coremods.invisizones.entities.proprieties;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class InvisiExtendedEntityProperties implements IExtendedEntityProperties {

	public boolean hadGoogles;
	public boolean wasInZone;

	public InvisiExtendedEntityProperties(){
		hadGoogles = false;
		wasInZone = false;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt){
		nbt.setBoolean("hadGoogles", hadGoogles);
		nbt.setBoolean("wasInZone", wasInZone);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt){
		hadGoogles = nbt.getBoolean("hadGoogles");
		wasInZone = nbt.getBoolean("wasInZone");
	}

	@Override
	public void init(Entity entity, World world){

	}

}
