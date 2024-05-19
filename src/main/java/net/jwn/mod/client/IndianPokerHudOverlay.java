package net.jwn.mod.client;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class IndianPokerHudOverlay {
    private static final ResourceLocation INDIAN_POKER = new ResourceLocation(Main.MOD_ID, "textures/hud/indian_poker.png");

    public static final IGuiOverlay INDIAN_POKER_HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Player player = Minecraft.getInstance().player;
        guiGraphics.blit(INDIAN_POKER, screenWidth - 22, 6, 16, 21, 16, 21, 256, 256);
        guiGraphics.blit(INDIAN_POKER, screenWidth - 40, 33, 0, 42, 11, 11, 256, 256);

        guiGraphics.drawString(Minecraft.getInstance().font, "Dev...",
                screenWidth - 24 - Minecraft.getInstance().font.width("Dev..."), 21, 0x000000, false);
        guiGraphics.drawString(Minecraft.getInstance().font, ": 50",
                screenWidth - 27, 35, 0x000000, false);
    });
}
