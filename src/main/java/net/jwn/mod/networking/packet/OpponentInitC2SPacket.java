package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpponentInitC2SPacket {

    public OpponentInitC2SPacket() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public OpponentInitC2SPacket(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getPersistentData().remove(Main.MOD_ID + "_opponent");
            player.getPersistentData().putBoolean(Main.MOD_ID + "_battle", false);
        });
        return true;
    }
}
