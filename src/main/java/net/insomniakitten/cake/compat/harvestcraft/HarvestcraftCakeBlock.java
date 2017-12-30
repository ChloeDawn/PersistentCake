package net.insomniakitten.cake.compat.harvestcraft;

import net.insomniakitten.cake.PersistentCakeBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public final class HarvestcraftCakeBlock extends PersistentCakeBlock {

    private final int maxBites;
    private final String itemName;

    private Item cakeItem = null;

    protected HarvestcraftCakeBlock(String item, int maxBites) {
        this.maxBites = Math.min(maxBites, 6);
        this.itemName = item;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            return eatCake(world, pos, state, player);
        } else {
            ItemStack held = player.getHeldItem(hand);
            return eatCake(world, pos, state, player) || held.isEmpty();
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (cakeItem == null) {
            ResourceLocation name = new ResourceLocation("harvestcraft", itemName);
            Item item = ForgeRegistries.ITEMS.getValue(name);
            cakeItem = item != null ? item : Items.AIR;
        }
        return state == getDefaultState() ? cakeItem : Items.AIR;
    }

    private boolean eatCake(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!player.canEat(false)) {
            return false;
        } else {
            player.addStat(StatList.CAKE_SLICES_EATEN);
            player.getFoodStats().addStats(2, 0.1F);
            int bites = state.getValue(BITES);
            if (bites < maxBites) {
                world.setBlockState(pos, state.withProperty(BITES, bites + 1), 3);
            } else world.setBlockToAir(pos);
            return true;
        }
    }

}
