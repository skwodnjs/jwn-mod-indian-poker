package net.jwn.mod.event;

import net.jwn.mod.Main;
import net.jwn.mod.client.IndianPokerHudOverlay;
import net.jwn.mod.client.LoadingHudOverlay;
import net.jwn.mod.client.ToastHudOverlay;
import net.jwn.mod.networking.ModMessages;
import net.jwn.mod.networking.packet.AfterBetC2SPacket;
import net.jwn.mod.networking.packet.OpponentInitC2SPacket;
import net.jwn.mod.networking.packet.StopBattleC2SPacket;
import net.jwn.mod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.OUT_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                Player target = Minecraft.getInstance().level.getPlayerByUUID(
                        player.getPersistentData().getUUID(Main.MOD_ID + "_opponent")
                );

                if (player.getPersistentData().getBoolean(Main.MOD_ID + "_battle")) {
                    player.getPersistentData().remove(Main.MOD_ID + "_opponent");
                    player.getPersistentData().putBoolean(Main.MOD_ID + "_battle", false);

                    if (target != null) {
                        target.getPersistentData().remove(Main.MOD_ID + "_opponent");
                        target.getPersistentData().putBoolean(Main.MOD_ID + "_battle", false);
                    }

                    ModMessages.sendToServer(new StopBattleC2SPacket());
                }
            } else if (KeyBinding.UP_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                if (player.getPersistentData().getBoolean(Main.MOD_ID + "_battle")) {
                    if (player.getPersistentData().getBoolean(Main.MOD_ID + "_turn")) {
                        // betting +1 (only client)
                        int betting = player.getPersistentData().getInt(Main.MOD_ID + "_will_bet");
                        player.getPersistentData().putInt(Main.MOD_ID + "_will_bet", betting + 1);
                        Minecraft.getInstance().player.displayClientMessage(Component.literal("will bet: " + (betting + 1)), true);
                    } else {
                        Minecraft.getInstance().player.displayClientMessage(Component.literal("It's not your turn"), true);
                    }
                }
            } else if (KeyBinding.DOWN_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                if (player.getPersistentData().getBoolean(Main.MOD_ID + "_battle")) {
                    if (player.getPersistentData().getBoolean(Main.MOD_ID + "_turn")) {
                        // will_bet -1 (only client)
                        int will_bet = player.getPersistentData().getInt(Main.MOD_ID + "_will_bet");
                        if (will_bet == 0) {
                            Minecraft.getInstance().player.displayClientMessage(Component.literal("can't be minus"), true);
                        } else {
                            player.getPersistentData().putInt(Main.MOD_ID + "_will_bet", will_bet - 1);
                            Minecraft.getInstance().player.displayClientMessage(Component.literal("will bet: " + (will_bet - 1)), true);
                        }
                    } else {
                        Minecraft.getInstance().player.displayClientMessage(Component.literal("It's not your turn"), true);
                    }
                }
            } else if (KeyBinding.BET_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                if (player.getPersistentData().getBoolean(Main.MOD_ID + "_battle")) {
                    if (player.getPersistentData().getBoolean(Main.MOD_ID + "_turn")) {
                        int will_bet = player.getPersistentData().getInt(Main.MOD_ID + "_will_bet");
                        int coin = player.getPersistentData().getInt(Main.MOD_ID + "_coin");
                        if (coin < will_bet) {
                            player.displayClientMessage(Component.literal("can't bet"), true);
                        } else {
                            int bet = player.getPersistentData().getInt(Main.MOD_ID + "_bet");

                            player.getPersistentData().putInt(Main.MOD_ID + "_coin", coin - will_bet);
                            player.getPersistentData().putInt(Main.MOD_ID + "_bet", bet + will_bet);

                            player.getPersistentData().putInt(Main.MOD_ID + "_will_bet", 0);
                            player.getPersistentData().putBoolean(Main.MOD_ID + "_turn", false);

                            ModMessages.sendToServer(new AfterBetC2SPacket(will_bet));
                            Minecraft.getInstance().player.displayClientMessage(Component.literal("will_bet success"), true);
                        }
                    } else {
                        Minecraft.getInstance().player.displayClientMessage(Component.literal("It's not your turn"), true);
                    }
                }
            }
        }
        @SubscribeEvent
        public static void onClientPlayerLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
            ModMessages.sendToServer(new OpponentInitC2SPacket());
        }

        @SubscribeEvent
        public static void onClientPlayerLoggingOut(ClientPlayerNetworkEvent.LoggingOut event) {
            System.out.println("tlqkf");
        }
    }

    @Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.OUT_KEY);
        }

        @SubscribeEvent
        public static void onRegisterGuiOverlaysEvent(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("toast_hud", ToastHudOverlay.TOAST_HUD);
            event.registerAboveAll("loading_hud", LoadingHudOverlay.LOADING_HUD);
            event.registerAboveAll("indian_poker_hud", IndianPokerHudOverlay.INDIAN_POKER_HUD);
        }
    }
}
