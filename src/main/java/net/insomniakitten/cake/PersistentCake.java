package net.insomniakitten.cake;

import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(   modid = PersistentCake.MOD_ID,
        name = PersistentCake.MOD_NAME,
        version = PersistentCake.MOD_VERSION,
        acceptedMinecraftVersions = PersistentCake.MC_VERSION)

@Mod.EventBusSubscriber
public class PersistentCake {

    public static final String MOD_ID = "persistentcake";
    public static final String MOD_NAME = "Persistent Cake";
    public static final String MOD_VERSION = "%mod_version%";
    public static final String MC_VERSION = "[%mc_version%,)";

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCakeUpdate(BlockEvent.BreakEvent event) {
        if (event.getWorld().isRemote) return;
        BlockPos pos = event.getPos().up();
        IBlockState state = event.getWorld().getBlockState(pos);
        if (state == null) return;
        if (state.getBlock().equals(Blocks.CAKE)
                && state.getValue(BlockCake.BITES).equals(0)) {
                InventoryHelper.spawnItemStack(event.getWorld(),
                        pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(Items.CAKE));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCakeBroken(BlockEvent.HarvestDropsEvent event) {
        if (event.getWorld().isRemote || event.getState() == null) return;
        if (event.getState().getBlock().equals(Blocks.CAKE)
                && event.getState().getValue(BlockCake.BITES).equals(0)) {
            event.getDrops().add(new ItemStack(Items.CAKE));
        }
    }

}
