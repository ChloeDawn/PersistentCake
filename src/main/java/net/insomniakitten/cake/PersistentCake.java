package net.insomniakitten.cake;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PersistentCake.MOD_ID, name = PersistentCake.MOD_NAME, version = PersistentCake.MOD_VERSION)
@Mod.EventBusSubscriber(modid = PersistentCake.MOD_ID)
public class PersistentCake {

    public static final String MOD_ID = "persistentcake";
    public static final String MOD_NAME = "Persistent Cake";
    public static final String MOD_VERSION = "%VERSION%";

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getWorld().isRemote) return;
        BlockPos pos = event.getPos().up();
        IBlockState state = event.getWorld().getBlockState(pos);
        if (state == null) return;
        if (state.getBlock().equals(Blocks.CAKE) && state.getValue(BlockCake.BITES).equals(0)) {
            Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(Items.CAKE));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.getWorld().isRemote || event.getState() == null) return;
        if (event.getState().getBlock().equals(Blocks.CAKE)
                && event.getState().getValue(BlockCake.BITES).equals(0)) {
            event.getDrops().add(new ItemStack(Items.CAKE));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getWorld().isRemote || !event.getNotifiedSides().contains(EnumFacing.UP)) return;
        World world = event.getWorld();
        BlockPos pos = event.getPos().up();
        IBlockState state = world.getBlockState(pos);
        boolean solidBelow = world.getBlockState(pos.down()).getMaterial().isSolid();
        if (!solidBelow && state.getBlock() == Blocks.CAKE && state.getValue(BlockCake.BITES).equals(0)) {
            Block.spawnAsEntity(world, pos, new ItemStack(Items.CAKE));
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

}
