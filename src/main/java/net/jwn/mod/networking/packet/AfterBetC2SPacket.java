package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.jwn.mod.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AfterBetC2SPacket {
    int will_bet;

    public AfterBetC2SPacket(int bet) {
        this.will_bet = bet;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(will_bet);
    }

    public AfterBetC2SPacket(FriendlyByteBuf buf) {
        will_bet = buf.readInt();
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerPlayer target = (ServerPlayer) player.level().getPlayerByUUID(
                    player.getPersistentData().getUUID(Main.MOD_ID + "_opponent")
            );

            int bet = player.getPersistentData().getInt(Main.MOD_ID + "_bet");
            int coin = player.getPersistentData().getInt(Main.MOD_ID + "_coin");

            player.getPersistentData().putInt(Main.MOD_ID + "_bet", bet + will_bet);
            player.getPersistentData().putInt(Main.MOD_ID + "_coin", coin - will_bet);

            target.getPersistentData().putBoolean(Main.MOD_ID + "_turn", true);
            ModMessages.sendToPlayer(new TurnSyncS2CPacket(), target);
        });
        return true;
    }
}
