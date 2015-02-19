package com.godzar.rpgmod.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelModChest extends ModelBase
{
    ModelRenderer tabletop;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
  
  public ModelModChest()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      tabletop = new ModelRenderer(this, 0, 0);
      tabletop.addBox(0F, 0F, 0F, 16, 3, 16);
      tabletop.setRotationPoint(-8F, 12F, -8F);
      tabletop.setTextureSize(64, 32);
      tabletop.mirror = true;
      setRotation(tabletop, 0F, 0F, 0F);
      leg1 = new ModelRenderer(this, 16, 19);
      leg1.addBox(0F, 0F, 0F, 4, 9, 4);
      leg1.setRotationPoint(-8F, 15F, -8F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 19);
      leg2.addBox(0F, 0F, 0F, 4, 9, 4);
      leg2.setRotationPoint(-8F, 15F, 4F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 48, 19);
      leg3.addBox(0F, 0F, 0F, 4, 9, 4);
      leg3.setRotationPoint(4F, 15F, 4F);
      leg3.setTextureSize(64, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 32, 19);
      leg4.addBox(0F, 0F, 0F, 4, 9, 4);
      leg4.setRotationPoint(4F, 15F, -8F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    tabletop.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
  }
  
  public void renderModel(float f)
  {
	    tabletop.render(f);
	    leg1.render(f);
	    leg2.render(f);
	    leg3.render(f);
	    leg4.render(f);
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