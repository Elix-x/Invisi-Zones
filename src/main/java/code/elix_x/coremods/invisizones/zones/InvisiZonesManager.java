package code.elix_x.coremods.invisizones.zones;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.entities.proprieties.InvisiExtendedEntityProperties;
import code.elix_x.coremods.invisizones.googles.InvisiGooglesHelper;
import code.elix_x.coremods.invisizones.net.UpdateRendererMessage;
import code.elix_x.coremods.invisizones.net.ZonesSyncingMessage;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class InvisiZonesManager {

	public static final Logger logger = LogManager.getLogger("Invisi Zones Manager");

	private static Set<InvisiZone> zones = new HashSet<InvisiZone>();

	public static Set<InvisiZone> getZones(){
		return zones;
	}

	public static void removeZone(InvisiZone zone){
		Iterator<InvisiZone> iterator = getZones().iterator();
		while(iterator.hasNext()){
			InvisiZone temp = iterator.next();
			if(zone.equals(temp)){
				iterator.remove();
				break;
			}
		}
		syncPlayers();
	}

	public static void setZones(Set<InvisiZone> zones2){
		logger.info("Setting zones to: " + zones2 + " On: " + FMLCommonHandler.instance().getSide());
		zones.clear();
		zones = zones2;
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			InvisiZonesManagerClient.setZonesClient();
		}
	}

	public static void clearZones(){
		getZones().clear();
	}

	private static void syncPlayers(){
		InvisiZonesBase.net.sendToAll(new ZonesSyncingMessage(getZones()));
	}

	public static void sincZonesWith(EntityPlayerMP player){
		InvisiZonesBase.net.sendTo(new ZonesSyncingMessage(getZones()), player);
	}

	public static InvisiZone get(int x, int y, int z, int x2, int y2, int z2, UUID player, int dimId){
		InvisiZone nzone = new InvisiZone(AxisAlignedBB.getBoundingBox(Math.min(x, x2), Math.min(y, y2), Math.min(z, z2), Math.max(x, x2), Math.max(y, y2), Math.max(z, z2)), player, dimId);
		for(InvisiZone zone : getZones()){
			if(zone.equals(nzone)){
				return zone;
			}
		}
		getZones().add(nzone);
		syncPlayers();
		return nzone;
	}

	public static InvisiZone[] getInZone(EntityPlayer player){
		InvisiZone[] z = new InvisiZone[]{};
		for(InvisiZone zone : getZones()){
			if(zone.dimId == player.worldObj.provider.dimensionId){
				if(zone.inside(player.boundingBox)){
					z = ArrayUtils.add(z, zone);
				}
			}
		}
		return z;
	}

	public static boolean inZone(EntityPlayer player){
		for(InvisiZone zone : getZones()){
			if(zone.dimId == player.worldObj.provider.dimensionId){
				if(zone.inside(player.boundingBox)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean areGooglesActive(EntityPlayer player){
		return InvisiGooglesHelper.areGooglesActive(player.getCurrentArmor(0));
	}

	public static void checkPlayerAndUpdateRenderer(EntityPlayer player){
		if(player.getExtendedProperties("invisiPrevData") == null){
			player.registerExtendedProperties("invisiPrevData", new InvisiExtendedEntityProperties());
		}
		InvisiExtendedEntityProperties props = (InvisiExtendedEntityProperties) player.getExtendedProperties("invisiPrevData");
		if(props.hadGoogles != InvisiZonesManager.areGooglesActive(player)){
			props.hadGoogles = InvisiZonesManager.areGooglesActive(player);
			InvisiZonesManager.updateRenderer(player);
		}
		if(InvisiZonesManager.areGooglesActive(player)){
			return;
		}
		if(props.wasInZone != InvisiZonesManager.inZone(player)){
			props.wasInZone = InvisiZonesManager.inZone(player);
			InvisiZonesManager.updateRenderer(player, InvisiZonesManager.getInZone(player));
		}
	}

	public static void updateRenderer(EntityPlayer player, InvisiZone... zoness){
		if(FMLCommonHandler.instance().getSide() != Side.CLIENT){
			InvisiZonesBase.net.sendTo(new UpdateRendererMessage(), (EntityPlayerMP) player);
			return;
		}
		if(ArrayUtils.isEmpty(zoness)){
			for(InvisiZone zone : getZones()){
				zone.updateAffectedRenderers(player, player.worldObj.provider.dimensionId);
				zone.relight(player, player.worldObj.provider.dimensionId);
			}
		} else {
			for(InvisiZone zone : zoness){
				zone.updateAffectedRenderers(player, player.worldObj.provider.dimensionId);
				zone.relight(player, player.worldObj.provider.dimensionId);
			}
		}
	}

	public static boolean drawBlockHighlight(EntityPlayer player, MovingObjectPosition selector){
		int x = selector.blockX;
		int y = selector.blockY;
		int z = selector.blockZ;
		try{
			for(InvisiZone zone : InvisiZonesManager.getZones()){
				if(!zone.renderBlockHihglights()){
					if(zone.dimId == player.worldObj.provider.dimensionId){
						if(zone.inside(Vec3.createVectorHelper(x, y, z))){
							if(!zone.renderBlockHighLightAt(player, selector)){
								return false;
							}
						}
					}
				}
			}
		}catch (Exception e){

		}
		return true;
	}

}
