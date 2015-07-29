package code.elix_x.coremods.invisizones.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelInvisiGoogles extends ModelBase
{
	//fields
	ModelRenderer LeftStick;
	ModelRenderer RighStick;
	ModelRenderer TopHandle;
	ModelRenderer Screen;

	public ModelInvisiGoogles()
	{
		textureWidth = 32;
		textureHeight = 32;

		LeftStick = new ModelRenderer(this, 18, 0);
		LeftStick.addBox(4F, -6F, -5F, 1, 1, 6);
		LeftStick.setRotationPoint(0F, 0F, 0F);
		LeftStick.setTextureSize(32, 32);
		LeftStick.mirror = false;
		setRotation(LeftStick, 0F, 0F, 0F);
		RighStick = new ModelRenderer(this, 18, 0);
		RighStick.addBox(-5F, -6F, -5F, 1, 1, 6);
		RighStick.setRotationPoint(0F, 0F, 0F);
		RighStick.setTextureSize(32, 32);
		RighStick.mirror = false;
		setRotation(RighStick, 0F, 0F, 0F);
		TopHandle = new ModelRenderer(this, 0, 8);
		TopHandle.addBox(-5F, -6F, -6F, 10, 1, 1);
		TopHandle.setRotationPoint(0F, 0F, 0F);
		TopHandle.setTextureSize(32, 32);
		TopHandle.mirror = false;
		setRotation(TopHandle, 0F, 0F, 0F);
		Screen = new ModelRenderer(this, 0, 0){
			@Override
			public void render(float p_78785_1_) {
				GL11.glEnable(GL11.GL_BLEND);
				super.render(p_78785_1_);
				GL11.glDisable(GL11.GL_BLEND);
			}
		};
		Screen.addBox(-4F, -5F, -5.5F, 8, 3, 0);
		Screen.setRotationPoint(0F, 0F, 0F);
		Screen.setTextureSize(32, 32);
		Screen.mirror = false;
		setRotation(Screen, 0F, 0F, 0F);
	}

	public void render(float f) {
		LeftStick.render(f);
		RighStick.render(f);
		TopHandle.render(f);
		Screen.render(f);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		LeftStick.render(f5);
		RighStick.render(f5);
		TopHandle.render(f5);
		Screen.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
