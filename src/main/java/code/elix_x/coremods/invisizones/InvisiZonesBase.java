package code.elix_x.coremods.invisizones;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import code.elix_x.coremods.invisizones.blocks.BlockSimpleZoner;
import code.elix_x.coremods.invisizones.events.OnPlayerJoinServerEvent;
import code.elix_x.coremods.invisizones.events.PlayerTickEvent;
import code.elix_x.coremods.invisizones.items.ItemInvisiGoogles;
import code.elix_x.coremods.invisizones.items.ItemWrench;
import code.elix_x.coremods.invisizones.net.UpdateRendererMessage;
import code.elix_x.coremods.invisizones.net.UpdateZonerMessage;
import code.elix_x.coremods.invisizones.net.ZonesSyncingMessage;
import code.elix_x.coremods.invisizones.proxy.CommonProxy;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = InvisiZonesBase.MODID, name = InvisiZonesBase.NAME, version = InvisiZonesBase.VERSION, dependencies = "required-after:excore")
public class InvisiZonesBase {

	public static final String MODID = "invisizones";
	public static final String NAME = "Invisi Zones";
	public static final String VERSION = "1.1.4";

	public static final Logger logger = LogManager.getLogger("IZ Base");
	
	@Mod.Instance("invisizones")
	public static InvisiZonesBase instance;

	@SidedProxy(clientSide = "code.elix_x.coremods.invisizones.proxy.ClientProxy", serverSide = "code.elix_x.coremods.invisizones.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper net;

	public static CreativeTabs invisitab;

	public static Item wrench;
	public static Item invisigoogles;
	
	public static Block simpleZoner;

	public static Block holoScanner;
	public static Block blockyHolographer;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{ 
		net = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		net.registerMessage(ZonesSyncingMessage.Handler.class, ZonesSyncingMessage.class, 0, Side.CLIENT);
		net.registerMessage(UpdateRendererMessage.Handler.class, UpdateRendererMessage.class, 1, Side.CLIENT);
		net.registerMessage(UpdateZonerMessage.Handler.class, UpdateZonerMessage.class, 2, Side.CLIENT);

		proxy.preInit();
	}


	@EventHandler
	public void init(FMLInitializationEvent event)
	{ 
		wrench = new ItemWrench();
		GameRegistry.registerItem(wrench, "inviziwrench");
		invisigoogles = new ItemInvisiGoogles();
		GameRegistry.registerItem(invisigoogles, "invisigoogles");

		simpleZoner = new BlockSimpleZoner();
		GameRegistry.registerBlock(simpleZoner, "simpleinvizizoner");
		GameRegistry.registerTileEntity(TileEntitySimpleInvisiZoner.class, "TileEntitySimpleInvisiZoner");

		
		GameRegistry.addRecipe(new ItemStack(wrench), new Object[] {" #$", " %#", "%  ", '%', Blocks.iron_block, '#', new ItemStack(Blocks.stained_glass_pane, 1, 5), '$', Blocks.glowstone});
		
		GameRegistry.addRecipe(new ItemStack(simpleZoner), new Object[] {"%#%", "#$#", "%#%", '%', Blocks.iron_block, '#', new ItemStack(Blocks.stained_glass_pane, 1, 5), '$', Blocks.glowstone});
		
		GameRegistry.addRecipe(new ItemStack(invisigoogles), new Object[] {"%%%", "#$#", '%', Items.iron_ingot, '#', new ItemStack(Blocks.stained_glass_pane, 1, 5), '$', Blocks.glowstone});

		invisitab = new CreativeTabs("invisitab") {
			@Override
			public Item getTabIconItem() {
				return wrench;
			}
		};

		wrench.setCreativeTab(InvisiZonesBase.invisitab);
		simpleZoner.setCreativeTab(InvisiZonesBase.invisitab);
		invisigoogles.setCreativeTab(InvisiZonesBase.invisitab);
		//		FMLCommonHandler.instance().bus().register(new ServerEvents());
		MinecraftForge.EVENT_BUS.register(new OnPlayerJoinServerEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerTickEvent());

		proxy.Init();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{ 
		proxy.postInit();
	}

	@EventHandler
	public void onStarting(FMLServerStartingEvent event){

	}

	@EventHandler
	public void onStopped(FMLServerStoppedEvent event){
		InvisiZonesManager.clearZones();
	}

}
