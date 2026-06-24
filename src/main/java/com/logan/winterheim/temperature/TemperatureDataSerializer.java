package com.logan.winterheim.temperature;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.NotNull;

public class TemperatureDataSerializer implements IAttachmentSerializer<CompoundTag, TemperatureData> {
    @Override
    public @NotNull TemperatureData read(@NotNull IAttachmentHolder holder,
                                         @NotNull CompoundTag tag,
                                         HolderLookup.@NotNull Provider provider) {
        TemperatureData data = new TemperatureData();
        data.setTemperature(tag.getFloat("temperature"));
        data.setHurtTimer(tag.getInt("hurtTimer"));
        return data;
    }

    @Override
    public @NotNull CompoundTag write(@NotNull TemperatureData attachment,
                                      HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("temperature", attachment.getTemperature());
        tag.putInt("hurtTimer", attachment.getHurtTimer());
        return tag;
    }
}
