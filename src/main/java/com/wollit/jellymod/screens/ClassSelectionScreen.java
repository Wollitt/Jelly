package com.wollit.jellymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.client.ClientPlayerClassData;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.PacketClassSelectionC2S;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ClassSelectionScreen extends Screen {

    public static final ResourceLocation WARRIOR_TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/screen/warrior_screen.png");
    public static final ResourceLocation ARCHER_TEXTURE =
            new ResourceLocation(JellyMod.MOD_ID, "textures/screen/archer_screen.png");

    private int classType = 0;
    private String selectedClass = "warrior";

    public ClassSelectionScreen(Component p_96550_) {
        super(p_96550_);
    }

    @Override
    protected void init() {
        super.init();
        Button nextButton = Button.builder(
                Component.literal("->"), (b) -> {
                    if (classType + 1 < 2) {
                        classType++;
                    } else {
                        classType = 0;
                    }
                }
        ).pos(0,0).build();

        Button previousButton = Button.builder(
                Component.literal("<-"), (b) -> {
                    if (classType - 1 >= 0) {
                        classType--;
                    } else {
                        classType = 1;
                    }
                }
        ).pos(0,50).build();

        Button selectButton =  Button.builder(
                Component.literal("Select class"), (b) -> {
                    ClientPlayerClassData.setClassName(selectedClass);
                    ModNetwork.sendToServer(new PacketClassSelectionC2S(classType));
                    this.onClose();
                }
        ).pos(0, 100).build();

        this.addRenderableWidget(nextButton);
        this.addRenderableWidget(previousButton);
        this.addRenderableWidget(selectButton);
    }

    @Override
    public void render(PoseStack poseStack, int p_96563_, int p_96564_, float p_96565_) {
        this.renderBackground(poseStack);
        super.render(poseStack, p_96563_, p_96564_, p_96565_);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());

        int x = width / 2;
        int y = height / 2;

        blit(poseStack, x, y, 0, 0, 256, 144);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private ResourceLocation getTexture() {
        switch (classType) {
            case 0 -> {
                selectedClass = "warrior";
                return WARRIOR_TEXTURE;
            }
            case 1 -> {
                selectedClass = "archer";
                return ARCHER_TEXTURE;
            }
            default -> {
                selectedClass = "null";
                return null;
            }
        }
    }
}
