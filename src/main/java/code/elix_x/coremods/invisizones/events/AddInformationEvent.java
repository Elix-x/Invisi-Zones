package code.elix_x.coremods.invisizones.events;

import code.elix_x.coremods.invisizones.googles.InvisiGooglesHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Locale;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class AddInformationEvent {
	
	public AddInformationEvent() {
		
	}
	
	@SubscribeEvent
	public void addInfo(ItemTooltipEvent event){
		if(InvisiGooglesHelper.areGoogles(event.itemStack)){
			event.toolTip.add(String.format("%s: %s", I18n.format("tooltip.invisigoogles.name"), I18n.format(InvisiGooglesHelper.areGooglesActive(event.itemStack) ? "tooltip.invisigoogles.on" : "tooltip.invisigoogles.off")));
		}
	}

}
