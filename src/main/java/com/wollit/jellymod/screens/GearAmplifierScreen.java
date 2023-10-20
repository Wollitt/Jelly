package com.wollit.jellymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierMenu;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.PacketAddCrystalsToGearC2S;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class GearAmplifierScreen extends AbstractContainerScreen<GearAmplifierMenu> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_0.png");

    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_button.png");


    public GearAmplifierScreen(GearAmplifierMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        Button button = Button.builder(Component.translatable("Amplify"), (n) -> {
            ModNetwork.sendToServer(new PacketAddCrystalsToGearC2S(menu.blockEntity));
        }).pos(10, 10).build();

        ImageButton button1 = new ImageButton(
                (width - imageWidth) / 2 + 65,
                (height - imageHeight) / 2 + 67,
                46, 13, 0, 0, 13, BUTTON_TEXTURE, 46, 26, (n) -> {
            ModNetwork.sendToServer(new PacketAddCrystalsToGearC2S(menu.blockEntity));
        });

        this.addRenderableWidget(button1);
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
        RenderSystem.setShaderTexture(0, getTexture());

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    private ResourceLocation getTexture() {
        if (this.menu.blockEntity.getItemStackHandler().getStackInSlot(0).getItem() != Items.AIR) {
            int crystalSlots = this.menu.blockEntity.getItemStackHandler().getStackInSlot(0).getOrCreateTag().getInt("crystals_slots_tag");

            return switch (crystalSlots) {
                case 0 -> new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_0.png");
                case 1 -> new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_1.png");
                case 2 -> new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_2.png");
                case 3 -> new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_3.png");
                case 4 -> new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_4.png");
                default -> throw new IllegalStateException("Unexpected value: " + crystalSlots);
            };
        } else {
            return new ResourceLocation(JellyMod.MOD_ID, "textures/gui/gear_amplifier_gui_0.png");
        }
    }

    @Override
    protected void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {
    }

    //TODO: show crystals in slots when they are added to sword

    @Override
    public List<Component> getTooltipFromItem(ItemStack itemStack) {
        return super.getTooltipFromItem(itemStack);
    }
}
