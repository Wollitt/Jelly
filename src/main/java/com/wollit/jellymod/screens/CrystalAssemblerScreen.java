package com.wollit.jellymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerMenu;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.PacketCraftItemC2S;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrystalAssemblerScreen extends AbstractContainerScreen<CrystalAssemblerMenu> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/gui/crystal_assembler_gui.png");

    public CrystalAssemblerScreen(CrystalAssemblerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init() {
        super.init();
        net.minecraft.client.gui.components.Button button = Button.builder(Component.translatable("Assemble"), (n) -> {
            ModNetwork.sendToServer(new PacketCraftItemC2S(menu.blockEntity));
        }).pos(getXSize() / 2, getYSize() / 2).build();

        this.addRenderableWidget(button);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
    }
}
