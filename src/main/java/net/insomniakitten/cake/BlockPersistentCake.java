package net.insomniakitten.cake;

/*
 * This file was created at 21:39 on 13 Jul 2017 by InsomniaKitten
 *
 * It is distributed as part of the PersistentCake mod.
 * Source code is visible at: https://github.com/InsomniaKitten/PersistentCake
 *
 * Copyright (c) InsomniaKitten 2017. All Rights Reserved.
 */

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

    public BlockPersistentCake() {
        setRegistryName("minecraft:cake");
        setSoundType(SoundType.CLOTH);
        setHardness(0.5f);
        setResistance(2.5f);
    }

    @Override
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

    private boolean canBlockStay(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    @Override @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.CAKE;
    }

}
