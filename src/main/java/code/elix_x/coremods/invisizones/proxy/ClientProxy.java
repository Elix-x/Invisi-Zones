package code.elix_x.coremods.invisizones.proxy;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.events.AddInformationEvent;
import code.elix_x.coremods.invisizones.events.DrawBlockSelectorEvent;
import code.elix_x.coremods.invisizones.models.armor.ModelInvisiGooglesWearable;
import code.elix_x.coremods.invisizones.renderer.item.ItemInvisiGooglesRenderer;
import code.elix_x.coremods.invisizones.renderer.item.ItemInvisiWrenchRenderer;
import code.elix_x.coremods.invisizones.renderer.item.TileEntityItemRenderer;
import code.elix_x.coremods.invisizones.renderer.tileentity.TileEntitySimpleInvisiZonerRenderer;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event){

	}

	@Override
	public void init(FMLInitializationEvent event){
		MinecraftForgeClient.registerItemRenderer(InvisiZonesBase.invisigoogles, new ItemInvisiGooglesRenderer());
		MinecraftForgeClient.registerItemRenderer(InvisiZonesBase.invisiWrench, new ItemInvisiWrenchRenderer());
		registerTeRenderer(new TileEntitySimpleInvisiZoner(), Item.getItemFromBlock(InvisiZonesBase.simpleInvisiZoner), new TileEntitySimpleInvisiZonerRenderer());

		MinecraftForge.EVENT_BUS.register(new DrawBlockSelectorEvent());
		MinecraftForge.EVENT_BUS.register(new AddInformationEvent());
	}

	public static void registerTeRenderer(TileEntity te, Item item, TileEntitySpecialRenderer renderer){
		ClientRegistry.bindTileEntitySpecialRenderer(te.getClass(), renderer);
		MinecraftForgeClient.registerItemRenderer(item, new TileEntityItemRenderer(renderer, te));
	}

	@Override
	public void postInit(FMLPostInitializationEvent event){

	}

	@Override
	public ModelBiped getGlassesModel(){
		return new ModelInvisiGooglesWearable(0.5f);
	}

}
