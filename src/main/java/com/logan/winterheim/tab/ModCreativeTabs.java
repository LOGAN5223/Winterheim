package com.logan.winterheim.tab;

import com.logan.winterheim.WinterheimMod;
import com.logan.winterheim.block.ModBlocks;
import com.logan.winterheim.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WinterheimMod.MOD_ID);

    public static final Supplier<CreativeModeTab> WINTERHEIM_TAB = CREATIVE_MODE_TABS.register(
            "winterheim_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.winterheim"))
                    .icon(() -> new ItemStack(ModItems.FROZEN_SHARD.get()))
                    .displayItems((params, outputs) ->{
                        outputs.accept(ModItems.FROZEN_SHARD.get());
                        outputs.accept(ModItems.JAGERMEISTER.get());
                        outputs.accept(ModBlocks.FROZEN_BLOCK.asItem());
                        outputs.accept(ModBlocks.HEATER_BLOCK.asItem());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
