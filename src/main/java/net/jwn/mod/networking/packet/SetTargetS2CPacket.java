package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SetTargetS2CPacket {
    UUID uuid;

    // target 에게 toast 보냄
    public SetTargetS2CPacket(UUID uuid) {
        this.uuid = uuid;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(uuid);
    }

    public SetTargetS2CPacket(FriendlyByteBuf buf) {
        uuid = buf.readUUID();
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_toast", true);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_toast_x", 0);
            Minecraft.getInstance().player.getPersistentData().putUUID(Main.MOD_ID + "_sender", uuid);
        });
        return true;
    }
}
