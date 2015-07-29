package code.elix_x.coremods.invisizones.models.tileentities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSimpleInvisiZoner extends ModelBase
{
	//fields
	ModelRenderer FrameDown1;
	ModelRenderer FrameDown2;
	ModelRenderer FrameDown3;
	ModelRenderer FrameDown4;
	ModelRenderer FrameMedium1;
	ModelRenderer FrameMedium2;
	ModelRenderer FrameMedium3;
	ModelRenderer FrameMedium4;
	ModelRenderer FrameUp1;
	ModelRenderer FrameUp2;
	ModelRenderer FrameUp3;
	ModelRenderer FrameUp4;
	ModelRenderer Screen1;
	ModelRenderer Screen2;
	ModelRenderer Screen3;
	ModelRenderer Screen4;
	ModelRenderer Screen5;
	ModelRenderer Screen6;
	ModelRenderer Xp;
	ModelRenderer Xm;
	ModelRenderer Yp;
	ModelRenderer Ym;
	ModelRenderer Zp;
	ModelRenderer Zm;

	public ModelSimpleInvisiZoner()
	{
		textureWidth = 64;
		textureHeight = 32;

		FrameDown1 = new ModelRenderer(this, 0, 18);
		FrameDown1.addBox(0F, 0F, 0F, 16, 1, 1);
		FrameDown1.setRotationPoint(-8F, 23F, -8F);
		FrameDown1.setTextureSize(64, 32);
		FrameDown1.mirror = false;
		setRotation(FrameDown1, 0F, 0F, 0F);
		FrameDown2 = new ModelRenderer(this, 0, 0);
		FrameDown2.addBox(0F, 0F, 0F, 1, 1, 16);
		FrameDown2.setRotationPoint(-8F, 23F, -8F);
		FrameDown2.setTextureSize(64, 32);
		FrameDown2.mirror = false;
		setRotation(FrameDown2, 0F, 0F, 0F);
		FrameDown3 = new ModelRenderer(this, 0, 0);
		FrameDown3.addBox(0F, 0F, 0F, 1, 1, 16);
		FrameDown3.setRotationPoint(7F, 23F, -8F);
		FrameDown3.setTextureSize(64, 32);
		FrameDown3.mirror = false;
		setRotation(FrameDown3, 0F, 0F, 0F);
		FrameDown4 = new ModelRenderer(this, 0, 18);
		FrameDown4.addBox(0F, 0F, 0F, 16, 1, 1);
		FrameDown4.setRotationPoint(-8F, 23F, 7F);
		FrameDown4.setTextureSize(64, 32);
		FrameDown4.mirror = false;
		setRotation(FrameDown4, 0F, 0F, 0F);
		FrameMedium1 = new ModelRenderer(this, 35, 0);
		FrameMedium1.addBox(0F, 0F, 0F, 1, 16, 1);
		FrameMedium1.setRotationPoint(-8F, 8F, -8F);
		FrameMedium1.setTextureSize(64, 32);
		FrameMedium1.mirror = false;
		setRotation(FrameMedium1, 0F, 0F, 0F);
		FrameMedium2 = new ModelRenderer(this, 35, 0);
		FrameMedium2.addBox(0F, 0F, 0F, 1, 16, 1);
		FrameMedium2.setRotationPoint(7F, 8F, -8F);
		FrameMedium2.setTextureSize(64, 32);
		FrameMedium2.mirror = false;
		setRotation(FrameMedium2, 0F, 0F, 0F);
		FrameMedium3 = new ModelRenderer(this, 35, 0);
		FrameMedium3.addBox(0F, 0F, 0F, 1, 16, 1);
		FrameMedium3.setRotationPoint(-8F, 8F, 7F);
		FrameMedium3.setTextureSize(64, 32);
		FrameMedium3.mirror = false;
		setRotation(FrameMedium3, 0F, 0F, 0F);
		FrameMedium4 = new ModelRenderer(this, 35, 0);
		FrameMedium4.addBox(0F, 0F, 0F, 1, 16, 1);
		FrameMedium4.setRotationPoint(7F, 8F, 7F);
		FrameMedium4.setTextureSize(64, 32);
		FrameMedium4.mirror = false;
		setRotation(FrameMedium4, 0F, 0F, 0F);
		FrameUp1 = new ModelRenderer(this, 0, 18);
		FrameUp1.addBox(0F, 0F, 0F, 16, 1, 1);
		FrameUp1.setRotationPoint(-8F, 8F, -8F);
		FrameUp1.setTextureSize(64, 32);
		FrameUp1.mirror = false;
		setRotation(FrameUp1, 0F, 0F, 0F);
		FrameUp2 = new ModelRenderer(this, 0, 0);
		FrameUp2.addBox(0F, 0F, 0F, 1, 1, 16);
		FrameUp2.setRotationPoint(-8F, 8F, -8F);
		FrameUp2.setTextureSize(64, 32);
		FrameUp2.mirror = false;
		setRotation(FrameUp2, 0F, 0F, 0F);
		FrameUp3 = new ModelRenderer(this, 0, 0);
		FrameUp3.addBox(0F, 0F, 0F, 1, 1, 16);
		FrameUp3.setRotationPoint(7F, 8F, -8F);
		FrameUp3.setTextureSize(64, 32);
		FrameUp3.mirror = false;
		setRotation(FrameUp3, 0F, 0F, 0F);
		FrameUp4 = new ModelRenderer(this, 0, 18);
		FrameUp4.addBox(0F, 0F, 0F, 16, 1, 1);
		FrameUp4.setRotationPoint(-8F, 8F, 7F);
		FrameUp4.setTextureSize(64, 32);
		FrameUp4.mirror = false;
		setRotation(FrameUp4, 0F, 0F, 0F);
		Screen1 = new ModelRenderer(this, 22, 18);
		Screen1.addBox(0F, 0F, 0F, 14, 0, 14);
		Screen1.setRotationPoint(-7F, 23.5F, -7F);
		Screen1.setTextureSize(64, 32);
		Screen1.mirror = false;
		setRotation(Screen1, 0F, 0F, 0F);
		Screen2 = new ModelRenderer(this, 36, 18);
		Screen2.addBox(0F, 0F, 0F, 14, 14, 0);
		Screen2.setRotationPoint(-7F, 9F, -7.5F);
		Screen2.setTextureSize(64, 32);
		Screen2.mirror = false;
		setRotation(Screen2, 0F, 0F, 0F);
		Screen3 = new ModelRenderer(this, 36, 4);
		Screen3.addBox(0F, 0F, 0F, 0, 14, 14);
		Screen3.setRotationPoint(-7.5F, 9F, -7F);
		Screen3.setTextureSize(64, 32);
		Screen3.mirror = false;
		setRotation(Screen3, 0F, 0F, 0F);
		Screen4 = new ModelRenderer(this, 36, 4);
		Screen4.addBox(0F, 0F, 0F, 0, 14, 14);
		Screen4.setRotationPoint(7.5F, 9F, -7F);
		Screen4.setTextureSize(64, 32);
		Screen4.mirror = false;
		setRotation(Screen4, 0F, 0F, 0F);
		Screen5 = new ModelRenderer(this, 36, 18);
		Screen5.addBox(0F, 0F, 0F, 14, 14, 0);
		Screen5.setRotationPoint(-7F, 9F, 7.5F);
		Screen5.setTextureSize(64, 32);
		Screen5.mirror = false;
		setRotation(Screen5, 0F, 0F, 0F);
		Screen6 = new ModelRenderer(this, 22, 18);
		Screen6.addBox(0F, 0F, 0F, 14, 0, 14);
		Screen6.setRotationPoint(-7F, 8.5F, -7F);
		Screen6.setTextureSize(64, 32);
		Screen6.mirror = false;
		setRotation(Screen6, 0F, 0F, 0F);
		Xp = new ModelRenderer(this, 52, 0);
		Xp.addBox(0F, 0F, 0F, 4, 2, 2);
		Xp.setRotationPoint(0F, 15F, -1F);
		Xp.setTextureSize(64, 32);
		Xp.mirror = false;
		setRotation(Xp, 0F, 0F, 0F);
		Xm = new ModelRenderer(this, 52, 0);
		Xm.addBox(-4F, 0F, 0F, 4, 2, 2);
		Xm.setRotationPoint(0F, 15F, -1F);
		Xm.setTextureSize(64, 32);
		Xm.mirror = false;
		setRotation(Xm, 0F, 0F, 0F);
		Yp = new ModelRenderer(this, 54, 4);
		Yp.addBox(0F, -4F, 0F, 2, 4, 2);
		Yp.setRotationPoint(-1F, 16F, -1F);
		Yp.setTextureSize(64, 32);
		Yp.mirror = false;
		setRotation(Yp, 0F, 0F, 0F);
		Ym = new ModelRenderer(this, 54, 4);
		Ym.addBox(0F, 0F, 0F, 2, 4, 2);
		Ym.setRotationPoint(-1F, 16F, -1F);
		Ym.setTextureSize(64, 32);
		Ym.mirror = false;
		setRotation(Ym, 0F, 0F, 0F);
		Zp = new ModelRenderer(this, 52, 10);
		Zp.addBox(0F, 0F, -4F, 2, 2, 4);
		Zp.setRotationPoint(-1F, 15F, 0F);
		Zp.setTextureSize(64, 32);
		Zp.mirror = false;
		setRotation(Zp, 0F, 0F, 0F);
		Zm = new ModelRenderer(this, 52, 10);
		Zm.addBox(0F, 0F, 0F, 2, 2, 4);
		Zm.setRotationPoint(-1F, 15F, 0F);
		Zm.setTextureSize(64, 32);
		Zm.mirror = false;
		setRotation(Zm, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		FrameDown1.render(f5);
		FrameDown2.render(f5);
		FrameDown3.render(f5);
		FrameDown4.render(f5);
		FrameMedium1.render(f5);
		FrameMedium2.render(f5);
		FrameMedium3.render(f5);
		FrameMedium4.render(f5);
		FrameUp1.render(f5);
		FrameUp2.render(f5);
		FrameUp3.render(f5);
		FrameUp4.render(f5);
		Xp.render(f);
		Xm.render(f);
		Yp.render(f);
		Ym.render(f);
		Zp.render(f);
		Zm.render(f);
		GL11.glEnable(GL11.GL_BLEND);
		Screen1.render(f);
		Screen2.render(f);
		Screen3.render(f);
		Screen4.render(f);
		Screen5.render(f);
		Screen6.render(f);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void render(float f, boolean synced) {
		FrameDown1.render(f);
		FrameDown2.render(f);
		FrameDown3.render(f);
		FrameDown4.render(f);
		FrameMedium1.render(f);
		FrameMedium2.render(f);
		FrameMedium3.render(f);
		FrameMedium4.render(f);
		FrameUp1.render(f);
		FrameUp2.render(f);
		FrameUp3.render(f);
		FrameUp4.render(f);
		if(!synced){
			Xp.render(f);
			Xm.render(f);
			Yp.render(f);
			Ym.render(f);
			Zp.render(f);
			Zm.render(f);
		}
		GL11.glEnable(GL11.GL_BLEND);
		Screen1.render(f);
		Screen2.render(f);
		Screen3.render(f);
		Screen4.render(f);
		Screen5.render(f);
		Screen6.render(f);
		GL11.glDisable(GL11.GL_BLEND);
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
