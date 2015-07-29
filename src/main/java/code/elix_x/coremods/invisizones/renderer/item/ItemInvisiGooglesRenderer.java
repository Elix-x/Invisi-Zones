package code.elix_x.coremods.invisizones.renderer.item;

import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.exception.McoHttpException;

import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.models.items.ModelInvisiGoogles;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemInvisiGooglesRenderer implements IItemRenderer {

	private ModelInvisiGoogles model;
	
	public ItemInvisiGooglesRenderer() {
		model = new ModelInvisiGoogles();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(InvisiZonesBase.MODID + ":textures/models/items/invisigoogles.png"));
		
		GL11.glPushMatrix();
		switch(type){
		case EQUIPPED:
			GL11.glScaled(2, 2, 2);
//			GL11.glRotatef(90, 1, 0, 0);
//			GL11.glTranslated(0, 0.5, -0.25);
//			GL11.glTranslated(0, 0.5, 0);
			GL11.glTranslated(0, 0, 0.5);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(135, 0, 1, 0);
			GL11.glTranslated(-0.35, 0, 0.5);
			model.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			break;
		case ENTITY:
			GL11.glRotatef(180, 1, 0, 0);
			model.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glScaled(2, 2, 2);
			GL11.glTranslated(0, 0, -0.5);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(90, 0, 1, 0);
//			GL11.glTranslated(-0.35, 0, 0.5);
			GL11.glTranslated(0.5, 0, 0.5);
			GL11.glRotatef(90, 0, 1, 0);
			model.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			break;
		case INVENTORY:
			GL11.glScaled(2, 2, 2);
//			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslated(0, 0.25, 0.125);
			model.render(0.0625f);
			break;
		default:
			break;
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
