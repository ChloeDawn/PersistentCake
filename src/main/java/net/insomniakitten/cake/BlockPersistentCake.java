package net.insomniakitten.cake;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockPersistentCake extends BlockCake {

    protected BlockPersistentCake() {
        setSoundType(SoundType.CLOTH);
        setHardness(0.5f);
        setResistance(2.5f);
    }

    @Override
    @Deprecated
    public Material getMaterial(IBlockState state) {
        return Material.CAKE;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (!this.canBlockStay(world, pos)) {
            world.destroyBlock(pos, true);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.CAKE;
    }

    private boolean canBlockStay(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

}
