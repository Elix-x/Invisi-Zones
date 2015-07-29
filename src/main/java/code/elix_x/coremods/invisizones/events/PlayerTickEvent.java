package code.elix_x.coremods.invisizones.events;

import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

public class PlayerTickEvent {

	public PlayerTickEvent() {

	}

	@SubscribeEvent
	public void onTick(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			InvisiZonesManager.checkPlayerAndUpdateRenderer((EntityPlayer) event.entityLiving);
		}
	}
}
