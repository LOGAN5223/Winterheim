package com.logan.winterheim.block;

import com.logan.winterheim.WinterheimMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(WinterheimMod.MOD_ID);

    public static final DeferredBlock<Block> FROZEN_BLOCK = BLOCKS.registerBlock(
            "frozen_block",
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(3.0f, 6.0f)
                    .requiresCorrectToolForDrops()
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
