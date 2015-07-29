package code.elix_x.coremods.invisizones.events;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManagerClient;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

public class DrawBlockSelectorEvent {
	
	public DrawBlockSelectorEvent() {
		
	}
	
	@SubscribeEvent
	public void draw(DrawBlockHighlightEvent event){
		if(!InvisiZonesManager.drawBlockHighlight(event.player, event.target)){
			event.setCanceled(true);
		}
	}

}
