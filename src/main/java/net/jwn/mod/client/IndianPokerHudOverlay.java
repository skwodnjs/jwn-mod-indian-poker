package net.jwn.mod.client;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class IndianPokerHudOverlay {
    private static final ResourceLocation INDIAN_POKER = new ResourceLocation(Main.MOD_ID, "textures/hud/indian_poker.png");

    public static final IGuiOverlay INDIAN_POKER_HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Player player = Minecraft.getInstance().player;
        if (player.getPersistentData().getBoolean(Main.MOD_ID + "_battle")) {
            int coin = player.getPersistentData().getInt(Main.MOD_ID + "_coin");
            int bet = player.getPersistentData().getInt(Main.MOD_ID + "_bet");

            guiGraphics.blit(INDIAN_POKER, screenWidth - 22, 6, 16, 21, 16, 21,
                    256, 256);
            guiGraphics.blit(INDIAN_POKER, screenWidth - 40, 33, 0, 42, 11, 11,
                    256, 256);

            guiGraphics.drawString(Minecraft.getInstance().font, "Dev...",
                    screenWidth - 24 - Minecraft.getInstance().font.width("Dev..."), 21, 0x000000, false);
            guiGraphics.drawString(Minecraft.getInstance().font, ": " + coin,
                    screenWidth - 27, 35, 0x000000, false);

            guiGraphics.blit(INDIAN_POKER, (screenWidth - 103) / 2, 6, 0, 53, 100, 22,
                    256, 256);

            guiGraphics.drawString(Minecraft.getInstance().font, "YOU:",
                    screenWidth / 2
                            - Minecraft.getInstance().font.width("vs") / 2
                            - 4
                            - Minecraft.getInstance().font.width("50")
                            - 2
                            - Minecraft.getInstance().font.width("YOU:"), 13, 0xFFFFFF);
            guiGraphics.drawString(Minecraft.getInstance().font, String.valueOf(bet),
                    screenWidth / 2
                            - 4
                            - Minecraft.getInstance().font.width("vs") / 2
                            - Minecraft.getInstance().font.width("50"), 13, 0xFFFFFF);
            guiGraphics.drawCenteredString(Minecraft.getInstance().font, "vs", screenWidth / 2, 13, 0xFFFFFF);
            guiGraphics.drawString(Minecraft.getInstance().font, "41",
                    screenWidth / 2
                            + 18, 13, 0xFFFFFF);
        }

    });
}
