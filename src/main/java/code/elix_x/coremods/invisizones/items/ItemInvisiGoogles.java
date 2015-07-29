package code.elix_x.coremods.invisizones.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import code.elix_x.coremods.invisizones.InvisiZonesBase;
import code.elix_x.coremods.invisizones.models.armor.ModelInvisiGooglesWearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemInvisiGoogles extends ItemArmor {

	public ItemInvisiGoogles() {
		super(EnumHelper.addArmorMaterial("invisiGooglesMaterial", -1, new int[]{0, 0, 0, 0}, 0), 0, 0);
		setUnlocalizedName("invisigoogles");
		//		setTextureName(InvisiZonesBase.modid + ":invisigoogles.png");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return InvisiZonesBase.MODID + ":textures/models/armor/invisigoogles.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) {
		if(itemstack != null){
			if(itemstack.getItem() == InvisiZonesBase.invisigoogles){
				return InvisiZonesBase.proxy.getGlassesModel();
			}
		}
		return super.getArmorModel(entityLiving, itemstack, armorSlot);
	}

	@Override
	public void renderHelmetOverlay(ItemStack itemstack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY) {
		if(itemstack != null){
			if(itemstack.getItem() == InvisiZonesBase.invisigoogles){
				renderGooglesScreen(resolution.getScaledWidth(), resolution.getScaledHeight());
			}
		}
	}

	private void renderGooglesScreen(int i, int j) {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(InvisiZonesBase.MODID + ":textures/misc/invisigooglesoverlay.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, (double)j, -90.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV((double)i, (double)j, -90.0D, 1.0D, 1.0D);
		tessellator.addVertexWithUV((double)i, 0.0D, -90.0D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isValidArmor(ItemStack itemstack, int armorType, Entity entity) {
		return armorType == 0 ? true : super.isValidArmor(itemstack, armorType, entity);
	}
}
