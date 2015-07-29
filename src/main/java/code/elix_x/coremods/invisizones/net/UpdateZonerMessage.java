package code.elix_x.coremods.invisizones.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class UpdateZonerMessage implements IMessage{

	private NBTTagCompound nbt;
	
	public UpdateZonerMessage() {

	}

	public UpdateZonerMessage(NBTTagCompound tag) {
		nbt = tag;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler implements IMessageHandler<UpdateZonerMessage, IMessage>{

		@Override
		public IMessage onMessage(UpdateZonerMessage message, MessageContext ctx) {
			TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.nbt.getInteger("x"), message.nbt.getInteger("y"), message.nbt.getInteger("z"));
			if(te instanceof TileEntitySimpleInvisiZoner){
				((TileEntitySimpleInvisiZoner) te).syncClient(message.nbt);
			}
			return null;
		}

	}

}
