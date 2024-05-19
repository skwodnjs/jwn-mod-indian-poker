package net.jwn.mod.networking.packet;

import net.jwn.mod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class AcceptPlayerS2CPacket {
    UUID targetUUID;

    public AcceptPlayerS2CPacket(UUID targetUUID) {
        this.targetUUID = targetUUID;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(this.targetUUID);
    }

    public AcceptPlayerS2CPacket(FriendlyByteBuf buf) {
        this.targetUUID = buf.readUUID();
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_battle", true);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_coin", 50);
            Minecraft.getInstance().player.getPersistentData().putBoolean(Main.MOD_ID + "_turn", false);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_bet", 0);
            Minecraft.getInstance().player.getPersistentData().putInt(Main.MOD_ID + "_will_bet", 0);
            Minecraft.getInstance().player.getPersistentData().putUUID(Main.MOD_ID + "_opponent", targetUUID);
        });
        return true;
    }
}
