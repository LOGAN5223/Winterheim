package com.logan.winterheim.attachment;

import com.logan.winterheim.WinterheimMod;
import com.logan.winterheim.temperature.TemperatureData;
import com.logan.winterheim.temperature.TemperatureDataSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WinterheimMod.MOD_ID);

    public static final Supplier<AttachmentType<TemperatureData>> TEMPERATURE =
            ATTACHMENT_TYPES.register("temperature",
                    () -> AttachmentType.builder(TemperatureData::new)
                            .serialize(new TemperatureDataSerializer())
                            .build()
            );

    public static void register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }
}
