package code.elix_x.coremods.invisizones.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.models.ModelZoneRenderer;
import code.elix_x.coremods.invisizones.models.tileentities.ModelSimpleInvisiZoner;
import code.elix_x.coremods.invisizones.tileentities.TileEntitySimpleInvisiZoner;
import code.elix_x.coremods.invisizones.zones.InvisiZonesManager;

public class TileEntitySimpleInvisiZonerRenderer extends TileEntitySpecialRenderer {

	public ResourceLocation zonerTexture;

	private ModelSimpleInvisiZoner zonerModel;

	public ResourceLocation zoneTexture;

	private ModelZoneRenderer zoneModel;

	public TileEntitySimpleInvisiZonerRenderer(){
		zonerTexture = new ResourceLocation(InvisiZonesBase.MODID + ":textures/models/tileentities/simpleinvisizoner.png");

		zonerModel = new ModelSimpleInvisiZoner();

		zoneTexture = new ResourceLocation(InvisiZonesBase.MODID + ":textures/models/zonerenderer.png");

		zoneModel = new ModelZoneRenderer();
	}

	public void renderTileEntityAt(TileEntitySimpleInvisiZoner zoner, double x, double y, double z, float f) {
		boolean synced = zoner.isSynced();

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
		GL11.glRotatef(180, 0f, 0f, 1f);

		this.bindTexture(zonerTexture);

		GL11.glPushMatrix();

		this.zonerModel.render(0.0625f, synced);

		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		if(synced){
			if(InvisiZonesManager.areGooglesActive(Minecraft.getMinecraft().thePlayer)){
				GL11.glPushMatrix();

				GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
				GL11.glRotatef(180, 0f, 0f, 1f);

				this.bindTexture(zoneTexture);

				GL11.glPushMatrix();

				//				GL11.glTranslated(zoner.partnerX < zoner.xCoord ? Math.max(zoner.partnerX - zoner.xCoord, zoner.xCoord - zoner.partnerX) - 1 : -(Math.max(zoner.partnerX - zoner.xCoord, zoner.xCoord - zoner.partnerX) - 1), 0, 0);
				double dX = Math.max(zoner.partnerX - zoner.xCoord, zoner.xCoord - zoner.partnerX) + 1;
				double dY = Math.max(zoner.partnerY - zoner.yCoord, zoner.yCoord - zoner.partnerY) + 1;
				double dZ = Math.max(zoner.partnerZ - zoner.zCoord, zoner.zCoord - zoner.partnerZ) + 1;
				
				GL11.glTranslated(0, zoner.partnerY < zoner.yCoord ? dY / 2 - 0.5 : -(dY/2) + 0.5, 0);
				GL11.glTranslated(0, -dY +1, 0);
				GL11.glScaled(1, dY, 1);
				
				GL11.glTranslated(zoner.partnerX < zoner.xCoord ? dX / 2 - 0.5 : -(dX/2) + 0.5, 0, 0);
				GL11.glTranslated(0, 0, zoner.partnerZ > zoner.zCoord ? dZ / 2 - 0.5 : -(dZ/2) + 0.5);
				GL11.glScaled(dX, 1, dZ);
				//				GL11.glTranslated(0, zoner.partnerY < zoner.yCoord ? Math.max(zoner.partnerY - zoner.yCoord, zoner.yCoord - zoner.partnerY) - 5 : -(Math.max(zoner.partnerY - zoner.yCoord, zoner.yCoord - zoner.partnerY) + 1), 0);
				//								GL11.glTranslated(0, 0, zoner.partnerZ > zoner.zCoord ? Math.max(zoner.partnerZ - zoner.zCoord, zoner.zCoord - zoner.partnerZ) - 2 : -(Math.max(zoner.partnerZ - zoner.zCoord, zoner.zCoord - zoner.partnerZ) - 1));
				//				GL11.glScaled(1, Math.max(zoner.partnerY - zoner.yCoord, zoner.yCoord - zoner.partnerY) + 1, 1);
				//								GL11.glScaled(1, 1, Math.max(zoner.partnerZ - zoner.zCoord, zoner.zCoord - zoner.partnerZ) + 1);
				//				GL11.glTranslated(2, 0, 0);
				zoneModel.render(0.0625f);

				GL11.glPopMatrix();
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		this.renderTileEntityAt((TileEntitySimpleInvisiZoner) tileentity, x, y, z, f);
	}

}
