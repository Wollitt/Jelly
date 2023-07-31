package com.wollit.jellymod.blocks.identification_table;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.PacketIdentifyItemC2S;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class IdentificationTableScreen extends AbstractContainerScreen<IdentificationTableMenu> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/gui/identification_table_gui.png");

    public static IdentificationTableBlockEntity blockEntity;

    public IdentificationTableScreen(IdentificationTableMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        blockEntity = menu.blockEntity;
    }

    @Override
    protected void init() {
        super.init();
        Button button = Button.builder(Component.translatable("Identify"), (n) -> {
            ModNetwork.sendToServer(new PacketIdentifyItemC2S(blockEntity));
        }).pos(100, 20).build();

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

    @Override
    public List<Component> getTooltipFromItem(ItemStack itemStack) {
        return super.getTooltipFromItem(itemStack);
    }
}
