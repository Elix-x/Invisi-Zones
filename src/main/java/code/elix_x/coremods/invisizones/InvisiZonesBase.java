package code.elix_x.coremods.invisizones;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import code.elix_x.coremods.invisizones.blocks.BlockSimpleZoner;
import code.elix_x.coremods.invisizones.events.OnPlayerJoinServerEvent;
import code.elix_x.coremods.invisizones.events.PlayerTickEvent;
import code.elix_x.coremods.invisizones.googles.InvisiGooglesHelper;
import code.elix_x.coremods.invisizones.items.ItemInvisiGoogles;
import code.elix_x.coremods.invisizones.items.ItemWrench;
import code.elix_x.coremods.invisizones.net.UpdateRendererMessage;
import code.elix_x.coremods.invisizones.net.UpdateZonerMessage;
import code.elix_x.coremods.invisizones.net.ZonesSyncingMessage;
import code.elix_x.coremods.invisizones.proxy.CommonProxy;
import code.elix_x.coremods.invisizones.recipes.InvisiGooglesInsertRecipe;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;
import code.elix_x.excore.EXCore;
import code.elix_x.excore.utils.items.ItemStackStringTranslator;
import code.elix_x.excore.utils.recipes.RecipeStringTranslator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = InvisiZonesBase.MODID, name = InvisiZonesBase.NAME, version = InvisiZonesBase.VERSION, dependencies = "required-after:" + EXCore.DEPENDENCY, acceptedMinecraftVersions = EXCore.MCVERSION)
public class InvisiZonesBase {

	public static final String MODID = "invisizones";
	public static final String NAME = "Invisi Zones";
	public static final String VERSION = "1.1.4";

	public static final Logger logger = LogManager.getLogger(NAME);

	@Mod.Instance(MODID)
	public static InvisiZonesBase instance;

	@SidedProxy(modId = MODID, clientSide = "code.elix_x.coremods.invisizones.proxy.ClientProxy", serverSide = "code.elix_x.coremods.invisizones.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper net;

	public static CreativeTabs invisitab;
	
	public static File configFile;
	public static Configuration config;

	public static Item invisiWrench;
	public static Item invisigoogles;

	public static Block simpleInvisiZoner;

	public static Block holoScanner;
	public static Block blockyHolographer;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){ 
		net = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		net.registerMessage(ZonesSyncingMessage.Handler.class, ZonesSyncingMessage.class, 0, Side.CLIENT);
		net.registerMessage(UpdateRendererMessage.Handler.class, UpdateRendererMessage.class, 1, Side.CLIENT);
		net.registerMessage(UpdateZonerMessage.Handler.class, UpdateZonerMessage.class, 2, Side.CLIENT);
		
		configFile = new File(event.getModConfigurationDirectory(), "InvisiZones.cfg");
		try {
			configFile.createNewFile();
		} catch (IOException e) {
			logger.error("Caught exception while creating config file: ", e);
		}
		config = new Configuration(configFile);
		config.load();
		
		invisiWrench = new ItemWrench();
		GameRegistry.registerItem(invisiWrench, "invisiwrench");
		
		invisigoogles = new ItemInvisiGoogles();
		GameRegistry.registerItem(invisigoogles, "invisigoogles");

		simpleInvisiZoner = new BlockSimpleZoner();
		GameRegistry.registerBlock(simpleInvisiZoner, "simpleinvizizoner");
		GameRegistry.registerTileEntity(TileEntitySimpleInvisiZoner.class, "TileEntitySimpleInvisiZoner");

		GameRegistry.addRecipe(RecipeStringTranslator.fromString(new ItemStack(invisiWrench), RecipeStringTranslator.validateFromConfig(config.getStringList("Invisi Wrench", "recipes", RecipeStringTranslator.toString("_#$", "_%#", "%__", '%', "blockIron", '#', "paneGlassLime", '$', "glowstone"), "Recipe for invisi wrench."))));
		GameRegistry.addRecipe(RecipeStringTranslator.fromString(new ItemStack(simpleInvisiZoner), RecipeStringTranslator.validateFromConfig(config.getStringList("Simple Invisi Zoner", "recipes", RecipeStringTranslator.toString("%#%", "#$#", "%#%", '%', "blockIron", '#', "paneGlassLime", '$', "glowstone"), "Recipe for simple invisi zoner."))));
		GameRegistry.addRecipe(RecipeStringTranslator.fromString(InvisiGooglesHelper.getDefaultGoogles(), RecipeStringTranslator.validateFromConfig(config.getStringList("Invisi Googles", "recipes", RecipeStringTranslator.toString("%%%", "#$#", '%', "blockIron", '#', "paneGlassLime", '$', "glowstone"), "Recipe for invisi googles."))));

		GameRegistry.addRecipe(new InvisiGooglesInsertRecipe());
		
		proxy.preInit(event);
		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event){ 
		invisitab = new CreativeTabs("invisitab"){

			@Override
			public Item getTabIconItem(){
				return invisiWrench;
			}

		};

		invisiWrench.setCreativeTab(InvisiZonesBase.invisitab);
		simpleInvisiZoner.setCreativeTab(InvisiZonesBase.invisitab);
		invisigoogles.setCreativeTab(InvisiZonesBase.invisitab);
		MinecraftForge.EVENT_BUS.register(new OnPlayerJoinServerEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerTickEvent());

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){ 
		proxy.postInit(event);
	}

	@EventHandler
	public void onStopped(FMLServerStoppedEvent event){
		InvisiZonesManager.clearZones();
	}

}
