package com.logan.winterheim.item;

import com.logan.winterheim.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class JagermeisterItem extends Item {
    public JagermeisterItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity){
        if (!level.isClientSide && entity instanceof Player player){
            player.addEffect(new MobEffectInstance(ModEffects.WARMTH, 1200, 1, false, true));
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack){
        return ItemUseAnimation.DRINK;
    }
}
