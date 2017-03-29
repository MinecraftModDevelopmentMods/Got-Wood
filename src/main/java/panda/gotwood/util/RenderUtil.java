package panda.gotwood.util;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

public final class RenderUtil {

  private RenderUtil() {
  }

  public static float FLUID_OFFSET = 0.005f;

  protected static Minecraft mc = Minecraft.getMinecraft();



  public static void putTexturedQuad(VertexBuffer renderer, TextureAtlasSprite sprite, double x, double y, double z, double w, double h, double d, EnumFacing face,
                                     int color, int brightness, boolean flowing) {
    int l1 = brightness >> 0x10 & 0xFFFF;
    int l2 = brightness & 0xFFFF;

    int a = color >> 24 & 0xFF;
    int r = color >> 16 & 0xFF;
    int g = color >> 8 & 0xFF;
    int b = color & 0xFF;

    putTexturedQuad(renderer, sprite, x, y, z, w, h, d, face, r, g, b, a, l1, l2, flowing);
  }

  // x and x+w has to be within [0,1], same for y/h and z/d
  public static void putTexturedQuad(VertexBuffer renderer, TextureAtlasSprite sprite, double x, double y, double z, double w, double h, double d, EnumFacing face,
                                     int r, int g, int b, int a, int light1, int light2, boolean flowing) {
    // safety
    if(sprite == null) {
      return;
    }
    double minU;
    double maxU;
    double minV;
    double maxV;

    double size = 16f;
    if(flowing) {
      size = 8f;
    }

    double x1 = x;
    double x2 = x + w;
    double y1 = y;
    double y2 = y + h;
    double z1 = z;
    double z2 = z + d;

    double xt1 = x1%1d;
    double xt2 = xt1 + w;
    while(xt2 > 1f) xt2 -= 1f;
    double yt1 = y1%1d;
    double yt2 = yt1 + h;
    while(yt2 > 1f) yt2 -= 1f;
    double zt1 = z1%1d;
    double zt2 = zt1 + d;
    while(zt2 > 1f) zt2 -= 1f;

    // flowing stuff should start from the bottom, not from the start
    if(flowing) {
      double tmp = 1d - yt1;
      yt1 = 1d - yt2;
      yt2 = tmp;
    }

    switch(face) {
      case DOWN:
      case UP:
        minU = sprite.getInterpolatedU(xt1 * size);
        maxU = sprite.getInterpolatedU(xt2 * size);
        minV = sprite.getInterpolatedV(zt1 * size);
        maxV = sprite.getInterpolatedV(zt2 * size);
        break;
      case NORTH:
      case SOUTH:
        minU = sprite.getInterpolatedU(xt2 * size);
        maxU = sprite.getInterpolatedU(xt1 * size);
        minV = sprite.getInterpolatedV(yt1 * size);
        maxV = sprite.getInterpolatedV(yt2 * size);
        break;
      case WEST:
      case EAST:
        minU = sprite.getInterpolatedU(zt2 * size);
        maxU = sprite.getInterpolatedU(zt1 * size);
        minV = sprite.getInterpolatedV(yt1 * size);
        maxV = sprite.getInterpolatedV(yt2 * size);
        break;
      default:
        minU = sprite.getMinU();
        maxU = sprite.getMaxU();
        minV = sprite.getMinV();
        maxV = sprite.getMaxV();
    }

    switch(face) {
      case DOWN:
        renderer.pos(x1, y1, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y1, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        break;
      case UP:
        renderer.pos(x1, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y2, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        break;
      case NORTH:
        renderer.pos(x1, y1, z1).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y1, z1).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        break;
      case SOUTH:
        renderer.pos(x1, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        break;
      case WEST:
        renderer.pos(x1, y1, z1).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x1, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        break;
      case EAST:
        renderer.pos(x2, y1, z1).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
        renderer.pos(x2, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
        break;
    }
  }

  public static void pre(double x, double y, double z) {
    GlStateManager.pushMatrix();

    RenderHelper.disableStandardItemLighting();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

    if(Minecraft.isAmbientOcclusionEnabled()) {
      GL11.glShadeModel(GL11.GL_SMOOTH);
    }
    else {
      GL11.glShadeModel(GL11.GL_FLAT);
    }

    GlStateManager.translate(x, y, z);
  }

  public static void post() {
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
    RenderHelper.enableStandardItemLighting();
  }

}