package net.insomniakitten.cake;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class PersistentCakeBlock extends BlockCake {

    private final Block delegate;

    protected PersistentCakeBlock(Block delegate) {
        this.delegate = delegate;
    }

    protected PersistentCakeBlock() {
        this(Blocks.CAKE);
    }

    @Override
    @Deprecated
    public Material getMaterial(IBlockState state) {
        return delegate.getMaterial(state);
    }

    @Override
    public String getUnlocalizedName() {
        return delegate.getUnlocalizedName();
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn() {
        return delegate.getCreativeTabToDisplayOn();
    }

    @Override
    @Deprecated
    public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
        return delegate.getBlockHardness(state, world, pos);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return delegate.getExplosionResistance(world, pos, exploder, explosion);
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, Entity entity) {
        return delegate.getSoundType(state, world, pos, entity);
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
        return state.getValue(BITES) == 0 ? Items.CAKE : Items.AIR;
    }

    private boolean canBlockStay(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

}
