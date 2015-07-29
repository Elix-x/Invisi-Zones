package code.elix_x.coremods.invisizones.net;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import code.elix_x.coremods.invisizones.zones.InvisiZone;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ZonesSyncingMessage implements IMessage{

	private Set<InvisiZone> zones;
	
	public ZonesSyncingMessage() {
		 zones = new HashSet<InvisiZone>();
	}
	
	public ZonesSyncingMessage(Set<InvisiZone> zons) {
		this();
		zones = zons;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		int id = nbt.getInteger("amount");
		for(int i = 0; i < id; i++){
			zones.add(InvisiZone.fromNBT(nbt.getCompoundTag("zoneN" + i)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound nbt = new NBTTagCompound();
		int id = 0;
		for(InvisiZone zone : zones){
			nbt.setTag("zoneN" + id, zone.toNbt(zone));
			id++;
		}
		nbt.setInteger("amount", id);
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	public static class Handler implements IMessageHandler<ZonesSyncingMessage, IMessage>{

		@Override
		public IMessage onMessage(ZonesSyncingMessage message, MessageContext ctx) {
			InvisiZonesManager.setZones(message.zones);
			return null;
		}

	}

}
