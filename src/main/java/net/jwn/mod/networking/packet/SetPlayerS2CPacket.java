package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SetPlayerS2CPacket {
    // opponent 를 client data 에도 추가, loading 시작
    UUID targetUUID;

    public SetPlayerS2CPacket(UUID targetUUID) {
        this.targetUUID = targetUUID;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(targetUUID);
    }

    public SetPlayerS2CPacket(FriendlyByteBuf buf) {
        this.targetUUID = buf.readUUID();
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().player.getPersistentData().putUUID(Main.MOD_ID + "_opponent", targetUUID);
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_isLoading", true);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_loading", 0);
        });
        return true;
    }
}
