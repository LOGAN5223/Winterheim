package com.logan.winterheim;

import com.logan.winterheim.attachment.ModAttachments;
import com.logan.winterheim.block.ModBlocks;
import com.logan.winterheim.effect.ModEffects;
import com.logan.winterheim.item.ModItems;
import com.logan.winterheim.tab.ModCreativeTabs;
import com.logan.winterheim.temperature.TemperatureEventHandler;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
@Mod(WinterheimMod.MOD_ID)
public class WinterheimMod {
    public static final String MOD_ID = "winterheim";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public WinterheimMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new TemperatureEventHandler());

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModAttachments.register(modEventBus);
        ModEffects.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
