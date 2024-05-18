package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetTargetS2CPacket {
    public SetTargetS2CPacket() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public SetTargetS2CPacket(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_toast", true);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_toast_x", 0);
        });
        return true;
    }
}
