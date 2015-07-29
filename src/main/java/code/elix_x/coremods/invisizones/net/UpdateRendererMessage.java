package code.elix_x.coremods.invisizones.net;

import io.netty.buffer.ByteBuf;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import code.elix_x.coremods.invisizones.zones.InvisiZone;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManagerClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class UpdateRendererMessage implements IMessage{
	
	public UpdateRendererMessage() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}
	
	public static class Handler implements IMessageHandler<UpdateRendererMessage, IMessage>{

		@Override
		public IMessage onMessage(UpdateRendererMessage message, MessageContext ctx) {
			InvisiZonesManagerClient.updateRendererDefault();
			return null;
		}

	}

}
