package com.wollit.jelly.screen.identification_station;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jelly.JellyMod;
import com.wollit.jelly.blocks.entity.IdentificationStationBlockEntity;
import com.wollit.jelly.network.CrystalChangePacket;
import com.wollit.jelly.setup.Messages;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

public class IdentificationStationScreen extends AbstractContainerScreen<IdentificationStationMenu> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/gui/identification_station_gui.png");
    private static IdentificationStationBlockEntity entity;

    public IdentificationStationScreen(IdentificationStationMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        entity = menu.blockEntity;
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new Button(0, 0, 100, 20, new TextComponent("Well"), (n) -> {
            Random rand = new Random();
            CompoundTag tag = new CompoundTag();
            tag.putInt("magic_sword.crystals", rand.nextInt(5) + 1);
            Messages.sendToServer(new CrystalChangePacket(entity, tag));
        }));
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
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

    }

    @Override
    public List<Component> getTooltipFromItem(ItemStack p_96556_) {
        return super.getTooltipFromItem(p_96556_);
    }

}
