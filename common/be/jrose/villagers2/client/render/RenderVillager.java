package be.jrose.villagers2.client.render;

import org.lwjgl.opengl.GL11;

import be.jrose.villagers2.entity.GenericVillager;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderVillager extends RenderLiving {

	protected ModelGenericVillager model;
	
	
	public RenderVillager(float par2) {
		super(new ModelGenericVillager(), par2);
		model = ((ModelGenericVillager)mainModel);
	}

	 /**
     * Determines wether Villager Render pass or not.
     */
    protected int shouldVillagerRenderPass(GenericVillager par1GenericVillager, int par2, float par3)
    {
        return -1;
    }

    public void renderVillager(GenericVillager par1GenericVillager, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1GenericVillager, par2, par4, par6, par8, par9);
    }

    /**
     * Passes the Villager special render.
     */
    protected void passVillagerSpecialRender(GenericVillager par1GenericVillager, double par2, double par4, double par6) {}

    protected void renderVillagerEquipedItems(GenericVillager par1GenericVillager, float par2)
    {
        super.renderEquippedItems(par1GenericVillager, par2);
    }

    protected void preRenderVillager(GenericVillager par1GenericVillager, float par2)
    {
        float var3 = 0.9375F;

        if (par1GenericVillager.getGrowingAge() < 0)
        {
            var3 = (float)((double)var3 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GL11.glScalef(var3, var3, var3);
    }

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.passVillagerSpecialRender((GenericVillager)par1EntityLiving, par2, par4, par6);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderVillager((GenericVillager)par1EntityLiving, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldVillagerRenderPass((GenericVillager)par1EntityLiving, par2, par3);
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderVillagerEquipedItems((GenericVillager)par1EntityLiving, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderVillager((GenericVillager)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderVillager((GenericVillager)par1Entity, par2, par4, par6, par8, par9);
    }
}
