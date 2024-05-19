package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.jwn.mod.networking.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TurnSyncS2CPacket {

    public TurnSyncS2CPacket() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public TurnSyncS2CPacket(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_turn", true);
        });
        return true;
    }
}
