package panda.gotwood.util;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import panda.gotwood.blocks.tileentities.TileTreeTap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;

public class TreeTapRenderer extends TileEntitySpecialRenderer<TileTreeTap> {
//FROM TIC
	  @Override
	  public void renderTileEntityAt(@Nonnull TileTreeTap te, double x, double y, double z, float partialTicks, int destroyStage) {

		  if(te.sapInBucket == null || !te.hasBucket) {
	      return;
	    }

	    // check how far into the 2nd block we want to render
	    float yMin = 0;
	    IBlockState state = te.getWorld().getBlockState(te.getPos().down());
	    Block block = state.getBlock();


	    yMin = -0.0001f;
	   
	    if(te.direction.getHorizontalIndex() >= 0) {
	      float r = -90f * (2 + te.direction.getHorizontalIndex());
	      float o = 0.5f;
	      // custom rendering for flowing on top
	      RenderUtil.pre(x, y, z);

	      Tessellator tessellator = Tessellator.getInstance();
	      VertexBuffer renderer = tessellator.getBuffer();
	      renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
	      Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	      int color = te.sapInBucket.getFluid().getColor(te.sapInBucket);
	      int brightness = te.getWorld().getCombinedLight(te.getPos(), te.sapInBucket.getFluid().getLuminosity());
	      TextureAtlasSprite flowing = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(te.sapInBucket.getFluid().getFlowing(te.sapInBucket).toString());

	      GlStateManager.translate(o, 0, o);
	      GlStateManager.rotate(r, 0, 1, 0);
	      GlStateManager.translate(-o, 0, -o);

	      double x1 = 0.375;
	      double x2 = 0.625;
	      double y1 = 0.375;
	      double y2 = 0.625;
	      double z1 = 0;
	      double z2 = 0.375;

	      // the stuff in the faucet
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.DOWN,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.NORTH, color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.EAST,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.WEST,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.UP,    color, brightness, true);

	      // the stuff flowing down
	      y1 = 0;
	      z1 = 0.375;
	      z2 = 0.5;
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.DOWN,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.NORTH, color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.EAST,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.SOUTH, color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.WEST,  color, brightness, true);
	      RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.UP,    color, brightness, true);

	      // render in the block beneath
	      if(yMin < 0) {
	        y1 = yMin;
	        y2 = 0;
	        RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.DOWN,  color, brightness, true);
	        RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.NORTH, color, brightness, true);
	        RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.EAST,  color, brightness, true);
	        RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.SOUTH, color, brightness, true);
	        RenderUtil.putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1, EnumFacing.WEST,  color, brightness, true);
	      }

	      tessellator.draw();
	      RenderUtil.post();
	    }
	  }
	}