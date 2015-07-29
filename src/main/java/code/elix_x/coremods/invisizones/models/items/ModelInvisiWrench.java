package code.elix_x.coremods.invisizones.models.items;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInvisiWrench extends ModelBase
{
	//fields
	ModelRenderer Handle;
	ModelRenderer G11;
	ModelRenderer G12;
	ModelRenderer G13;
	ModelRenderer G14;
	ModelRenderer G21;
	ModelRenderer G22;
	ModelRenderer G23;
	ModelRenderer G24;
	ModelRenderer ControllerOut;
	ModelRenderer ControllerIn;
	
	Random random;

	public ModelInvisiWrench()
	{
		random = new Random();
		textureWidth = 32;
		textureHeight = 32;

		Handle = new ModelRenderer(this, 4, 17);
		Handle.addBox(0F, -10F, 0F, 1, 10, 1);
		Handle.setRotationPoint(0F, 0F, 0F);
		Handle.setTextureSize(32, 32);
		Handle.mirror = false;
		setRotation(Handle, 0F, 0F, 0F);
		G11 = new ModelRenderer(this, 4, 12);
		G11.addBox(0F, -3F, -0.5F, 1, 4, 1);
		G11.setRotationPoint(1F, -10F, 1F);
		G11.setTextureSize(32, 32);
		G11.mirror = false;
		setRotation(G11, 0.7853982F, 3.141593F, 0F);
		G12 = new ModelRenderer(this, 4, 12);
		G12.addBox(-0.5F, -3F, 0F, 1, 4, 1);
		G12.setRotationPoint(1F, -10F, 0F);
		G12.setTextureSize(32, 32);
		G12.mirror = false;
		setRotation(G12, 0F, 0F, 0.7853982F);
		G13 = new ModelRenderer(this, 4, 12);
		G13.addBox(0F, -3F, -0.5F, 1, 4, 1);
		G13.setRotationPoint(0F, -10F, 0F);
		G13.setTextureSize(32, 32);
		G13.mirror = false;
		setRotation(G13, 0.7853982F, 0F, 0F);
		G14 = new ModelRenderer(this, 4, 12);
		G14.addBox(-0.5F, -3F, 0F, 1, 4, 1);
		G14.setRotationPoint(0F, -10F, 1F);
		G14.setTextureSize(32, 32);
		G14.mirror = false;
		setRotation(G14, 0F, 3.141593F, 0.7853982F);
		G21 = new ModelRenderer(this, 4, 8);
		G21.addBox(0F, -3F, 0F, 1, 3, 1);
		G21.setRotationPoint(0F, -11F, 2.5F);
		G21.setTextureSize(32, 32);
		G21.mirror = false;
		setRotation(G21, 0F, 0F, 0F);
		G22 = new ModelRenderer(this, 4, 8);
		G22.addBox(0F, -3F, 0F, 1, 3, 1);
		G22.setRotationPoint(2.5F, -11F, 0F);
		G22.setTextureSize(32, 32);
		G22.mirror = false;
		setRotation(G22, 0F, 0F, 0F);
		G23 = new ModelRenderer(this, 4, 8);
		G23.addBox(0F, -3F, 0F, 1, 3, 1);
		G23.setRotationPoint(0F, -11F, -2.5F);
		G23.setTextureSize(32, 32);
		G23.mirror = false;
		setRotation(G23, 0F, 0F, 0F);
		G24 = new ModelRenderer(this, 4, 8);
		G24.addBox(0F, -3F, 0F, 1, 3, 1);
		G24.setRotationPoint(-2.5F, -11F, 0F);
		G24.setTextureSize(32, 32);
		G24.mirror = false;
		setRotation(G24, 0F, 0F, 0F);
		ControllerOut = new ModelRenderer(this, 0, 2);
		ControllerOut.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		ControllerOut.setRotationPoint(0.5F, -15F, 0.5F);
		ControllerOut.setTextureSize(32, 32);
		ControllerOut.mirror = false;
		setRotation(ControllerOut, 0F, 0F, 0F);
		ControllerIn = new ModelRenderer(this, 4, 0);
		ControllerIn.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		ControllerIn.setRotationPoint(0.5F, -15F, 0.5F);
		ControllerIn.setTextureSize(32, 32);
		ControllerIn.mirror = false;
		setRotation(ControllerIn, 0F, 0F, 0F);
	}

	public void render(float f) {
		Handle.render(f);
		G11.render(f);
		G12.render(f);
		G13.render(f);
		G14.render(f);
		G21.render(f);
		G22.render(f);
		G23.render(f);
		G24.render(f);
		preRotateControllers();
		ControllerIn.render(f);
		ControllerOut.render(f);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Handle.render(f5);
		G11.render(f5);
		G12.render(f5);
		G13.render(f5);
		G14.render(f5);
		G21.render(f5);
		G22.render(f5);
		G23.render(f5);
		G24.render(f5);
		preRotateControllers();
		ControllerIn.render(f5);
		ControllerOut.render(f5);
	}

	private void preRotateControllers() {
		setRotation(ControllerIn, ControllerIn.rotateAngleX + random.nextFloat(), ControllerIn.rotateAngleY + random.nextFloat(), ControllerIn.rotateAngleZ + random.nextFloat());
		setRotation(ControllerOut, ControllerOut.rotateAngleX + random.nextFloat(), ControllerOut.rotateAngleY + random.nextFloat(), ControllerOut.rotateAngleZ + random.nextFloat());
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
