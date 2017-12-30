package net.insomniakitten.cake.compat.harvestcraft;

import net.insomniakitten.cake.PersistentCake;
import net.insomniakitten.cake.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = PersistentCake.ID)
@GameRegistry.ObjectHolder("harvestcraft")
public final class HarvestcraftCompat {

    public static final Block CARROTCAKE = Blocks.AIR;
    public static final Block CHEESECAKE = Blocks.AIR;
    public static final Block CHERRYCHEESECAKE = Blocks.AIR;
    public static final Block CHOCOLATESPRINKLESCAKE = Blocks.AIR;
    public static final Block HOLIDAYCAKE = Blocks.AIR;
    public static final Block LAMINGTONCAKE = Blocks.AIR;
    public static final Block PAVLOVACAKE = Blocks.AIR;
    public static final Block PINEAPPLEUPSIDEDOWNCAKE = Blocks.AIR;
    public static final Block PUMPKINCHEESECAKE = Blocks.AIR;
    public static final Block REDVELVETCAKE = Blocks.AIR;

    private HarvestcraftCompat() {}

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        ModContainer harvestcraft = RegistryHelper.findContainerFor("harvestcraft");
        if (harvestcraft != null) {
            event.getRegistry().registerAll(
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "carrotcake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "cheesecake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(6), harvestcraft, "cherrycheesecake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "chocolatesprinklecake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "holidaycake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(3), harvestcraft, "lamingtoncake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "pavlovacake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "pineappleupsidedowncake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "pumpkincheesecake"),
                    RegistryHelper.withRegistryName(new BlockHarvestcraftCake(6), harvestcraft, "redvelvetcake")
            );
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        ModContainer harvestcraft = RegistryHelper.findContainerFor("harvestcraft");
        if (harvestcraft != null) {
            event.getRegistry().registerAll(
                    RegistryHelper.withRegistryName(new ItemBlock(CARROTCAKE), harvestcraft, "carrotcake"),
                    RegistryHelper.withRegistryName(new ItemBlock(CHEESECAKE), harvestcraft, "cheesecake"),
                    RegistryHelper.withRegistryName(new ItemBlock(CHERRYCHEESECAKE), harvestcraft, "cherrycheesecake"),
                    RegistryHelper.withRegistryName(new ItemBlock(CHOCOLATESPRINKLESCAKE), harvestcraft, "chocolatesprinklecake"),
                    RegistryHelper.withRegistryName(new ItemBlock(HOLIDAYCAKE), harvestcraft, "holidaycake"),
                    RegistryHelper.withRegistryName(new ItemBlock(LAMINGTONCAKE), harvestcraft, "lamingtoncake"),
                    RegistryHelper.withRegistryName(new ItemBlock(PAVLOVACAKE), harvestcraft, "pavlovacake"),
                    RegistryHelper.withRegistryName(new ItemBlock(PINEAPPLEUPSIDEDOWNCAKE), harvestcraft, "pineappleupsidedowncake"),
                    RegistryHelper.withRegistryName(new ItemBlock(PUMPKINCHEESECAKE), harvestcraft, "pumpkincheesecake"),
                    RegistryHelper.withRegistryName(new ItemBlock(REDVELVETCAKE), harvestcraft, "redvelvetcake")
            );
        }
    }

}
