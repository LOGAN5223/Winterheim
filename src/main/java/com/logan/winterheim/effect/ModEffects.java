package com.logan.winterheim.effect;

import com.logan.winterheim.WinterheimMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, WinterheimMod.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> WARMTH =
            MOB_EFFECTS.register("warmth", WarmthEffect::new);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}