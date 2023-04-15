package net.almafsia.fireandblood.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.almafsia.fireandblood.FireAndBlood;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SmelterScreen extends AbstractContainerScreen<SmelterMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FireAndBlood.MOD_ID, "textures/gui/smelter_gui.png");

    public SmelterScreen(SmelterMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init(){
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrows(stack, x, y);
    }

    private void renderProgressArrows(PoseStack stack, int x, int y) {
        if(menu.isSmelting()){
            blit(stack, x+105, y+33, 176, 0, 8, menu.getScaledMetalProgress());
        }
        if(menu.isAdding()){
            blit(stack, x+105, y+33, 176, 0, 8, menu.getScaledAdditionProgress());
        }
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        renderTooltip(stack, mouseX, mouseY);
    }
}
