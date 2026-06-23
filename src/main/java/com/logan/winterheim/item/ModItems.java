package com.logan.winterheim.item;

import com.logan.winterheim.WinterheimMod;
import com.logan.winterheim.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WinterheimMod.MOD_ID);

    public static final DeferredItem<Item> FROZEN_SHARD = ITEMS.register("frozen_shard",
            key -> {
                ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key);
                return new Item(new Item.Properties().setId(itemKey));
            });

    public static final DeferredItem<BlockItem> FROZEN_BLOCK = ITEMS.register("frozen_block",
            key -> {
                ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key);
                return new BlockItem(ModBlocks.FROZEN_BLOCK.get(), new Item.Properties().setId(itemKey));
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
