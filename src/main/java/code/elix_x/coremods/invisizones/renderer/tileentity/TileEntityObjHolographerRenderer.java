package code.elix_x.coremods.invisizones.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.models.tileentities.ModelScreenedFrame;
import code.elix_x.coremods.invisizones.tileentities.TileEntityObjHolographer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntityObjHolographerRenderer extends TileEntitySpecialRenderer {

	private ResourceLocation texture;
	private ModelScreenedFrame model;
	private IModelCustom projectorModel;

	public TileEntityObjHolographerRenderer() {
		texture = new ResourceLocation(InvisiZonesBase.MODID + ":textures/models/tileentities/holographer.png");
		model = new ModelScreenedFrame();
		projectorModel = AdvancedModelLoader.loadModel(new ResourceLocation(InvisiZonesBase.MODID + ":models/tileentities/holoprojector.obj"));
	}

	public void renderHolographerAt(TileEntityObjHolographer holographer, double x, double y, double z, float f) {
		
	/*	GL11.glPushMatrix();
//		GL11.glScaled(0.5, 0.5, 0.5);
//		GL11.glTranslated(0, 1, 0);
		projectorModel.renderAll();
		GL11.glPopMatrix();*/
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
		GL11.glRotatef(180, 0f, 0f, 1f);
		
		this.bindTexture(texture);	
		
		GL11.glPushMatrix();

		model.render(0.0625f);
		
//		GL11.glRotatef(180, 1, 0, 0);
//		GL11.glScaled(1/16, 1/16, 1/16);
		
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		renderHolographerAt((TileEntityObjHolographer) tileentity, x, y, z, f);
	}

}
