package com.wollit.jelly.screen.basic_alloy_furnace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jelly.JellyMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BasicAlloyFurnaceScreen extends AbstractContainerScreen<BasicAlloyFurnaceMenu> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/gui/basic_alloy_furnace_gui.png");

    public BasicAlloyFurnaceScreen(BasicAlloyFurnaceMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }


    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        if (menu.isCrafting()) {
            blit(poseStack, x + 153, y + 43, 177, 2, 14, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
