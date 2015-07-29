package code.elix_x.coremods.invisizones.proxy;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.events.DrawBlockSelectorEvent;
import code.elix_x.coremods.invisizones.models.armor.ModelInvisiGooglesWearable;
import code.elix_x.coremods.invisizones.renderer.item.ItemInvisiGooglesRenderer;
import code.elix_x.coremods.invisizones.renderer.item.ItemInvisiWrenchRenderer;
import code.elix_x.coremods.invisizones.renderer.item.TileEntityItemRenderer;
import code.elix_x.coremods.invisizones.renderer.tileentity.TileEntityObjHolographerRenderer;
import code.elix_x.coremods.invisizones.renderer.tileentity.TileEntitySimpleInvisiZonerRenderer;
import code.elix_x.coremods.invisizones.tileentities.TileEntityObjHolographer;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public void preInit() {

	}

	public void Init() {
		MinecraftForge.EVENT_BUS.register(new DrawBlockSelectorEvent());
		
		MinecraftForgeClient.registerItemRenderer(InvisiZonesBase.invisigoogles, new ItemInvisiGooglesRenderer());
		MinecraftForgeClient.registerItemRenderer(InvisiZonesBase.wrench, new ItemInvisiWrenchRenderer());
		registerTeRenderer(new TileEntitySimpleInvisiZoner(), Item.getItemFromBlock(InvisiZonesBase.simpleZoner), new TileEntitySimpleInvisiZonerRenderer());
	}

	public static void registerTeRenderer(TileEntity te, Item item, TileEntitySpecialRenderer renderer){
		ClientRegistry.bindTileEntitySpecialRenderer(te.getClass(), renderer);
		MinecraftForgeClient.registerItemRenderer(item, new TileEntityItemRenderer(renderer, te));
	}
	
	public void postInit() {

	}
	
	@Override
	public ModelBiped getGlassesModel() {
		return new ModelInvisiGooglesWearable(0.5f);
	}
}
