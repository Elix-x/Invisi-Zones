package code.elix_x.coremods.invisizones.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OnPlayerJoinServerEvent {

	public OnPlayerJoinServerEvent() {

	}

	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event){
		if(!event.world.isRemote){
			if(event.entity instanceof EntityPlayerMP){
				InvisiZonesManager.sincZonesWith((EntityPlayerMP) event.entity);
			}
		}
	}
}
