package com.logan.winterheim.temperature;

import com.logan.winterheim.attachment.ModAttachments;
import com.logan.winterheim.block.HeaterBlock;
import com.logan.winterheim.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class TemperatureEventHandler {
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player.level().isClientSide()) return;

        TemperatureData data = player.getData(ModAttachments.TEMPERATURE);
        float currentTemp = data.getTemperature();

        float delta = calculateTemperatureDelta(player);
        data.setTemperature(Math.clamp((currentTemp + delta), -100f, 100f));

        handleColdDamage(player, data);

        if (player.level().getGameTime() % 20 == 0) {
            player.displayClientMessage(
                    Component.literal("Температура: " + String.format("%.1f", data.getTemperature()) + "°C"),
                    true
            );
        }

        if(data.getTemperature() < -50f){
            //TODO: Добавить урон и прочие эффекты
        }

    }

    private void handleColdDamage(Player player, TemperatureData data){
        float temp = data.getTemperature();
        if (temp > -20f){
            data.setHurtTimer(0);
            player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            return;
        }

        int slownessLevel = 0;
        if (temp <= -60f) {
            slownessLevel = 3;
        } else if (temp <= -40f) {
            slownessLevel = 2;
        } else if (temp < 20f) {
            slownessLevel = 1;
        }

        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                MobEffectInstance.INFINITE_DURATION,
                slownessLevel - 1, // уровень эффекта: 0 = slowness I, 1 = slowness II и т.д.
                false, false, true // ambient=false, visible=false, showIcon=true
        ));

        int timer = data.getHurtTimer();
        if (timer > 0){
            data.setHurtTimer(timer - 1);
            return;
        }

        int nextDelay = 0;
        float damageAmount = 0;

        if (temp <= -60f){
            nextDelay = 20;
            damageAmount = 2f;
        }
        else if (temp <= -40f){
            nextDelay = 40;
            damageAmount = 1.5f;
        }
        else if (temp < 20f) {
            nextDelay = 0;
            damageAmount = 0f;
        }

        player.hurt(player.damageSources().freeze(), damageAmount);

        data.setHurtTimer(nextDelay);
    }

    private float calculateTemperatureDelta(Player player){
        float delta = 0f;

        float ambientCooling = -0.05f;
        float biomCooling = 1f;

        if (player.hasEffect(ModEffects.WARMTH)) {
            int amplifier = player.getEffect(ModEffects.WARMTH).getAmplifier() + 1; // 1, 2, 3...
            // Каждый уровень эффекта добавляет +0.03 к дельте (можно настроить)
            delta += 0.03f * amplifier;
        }

        delta += ambientCooling * biomCooling;

        BlockPos playerPos = player.blockPosition();
        int radius = 5;
        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-radius, -2, -radius),
                playerPos.offset(radius, 3, radius))) {
            if (player.level().getBlockState(pos).getBlock() instanceof HeaterBlock){
                double dist = Math.sqrt(pos.distSqr(playerPos));
                if (dist <= radius) {
                    delta += (float) ((0.1f * (1.0f - dist / radius)));
                }
            }
        }

        // TODO: учесть близость источников тепла, одежду, еду, погоду, время суток

        return delta;
    }
}
