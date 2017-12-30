package net.insomniakitten.cake.compat.harvestcraft;

import net.insomniakitten.cake.PersistentCake;
import net.insomniakitten.cake.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = PersistentCake.ID)
@GameRegistry.ObjectHolder("harvestcraft")
public final class HarvestcraftCompat {

    public static final Block CARROTCAKE = Blocks.AIR;
    public static final Block CHEESECAKE = Blocks.AIR;
    public static final Block CHERRYCHEESECAKE = Blocks.AIR;
    public static final Block CHOCOLATESPRINKLECAKE = Blocks.AIR;
    public static final Block HOLIDAYCAKE = Blocks.AIR;
    public static final Block LAMINGTONCAKE = Blocks.AIR;
    public static final Block PAVLOVACAKE = Blocks.AIR;
    public static final Block PINEAPPLEUPSIDEDOWNCAKE = Blocks.AIR;
    public static final Block PUMPKINCHEESECAKE = Blocks.AIR;
    public static final Block REDVELVETCAKE = Blocks.AIR;

    private HarvestcraftCompat() {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        handleItemBlock(event.getRegistry(), CARROTCAKE, "carrotcakeitem");
        handleItemBlock(event.getRegistry(), CHEESECAKE, "cheesecakeitem");
        handleItemBlock(event.getRegistry(), CHERRYCHEESECAKE, "cherrycheesecakeitem");
        handleItemBlock(event.getRegistry(), CHOCOLATESPRINKLECAKE, "chocolatesprinklecakeitem");
        handleItemBlock(event.getRegistry(), HOLIDAYCAKE, "holidaycakeitem");
        handleItemBlock(event.getRegistry(), LAMINGTONCAKE, "lamingtonitem");
        handleItemBlock(event.getRegistry(), PAVLOVACAKE, "pavlovaitem");
        handleItemBlock(event.getRegistry(), PINEAPPLEUPSIDEDOWNCAKE, "pineappleupsidedowncakeitem");
        handleItemBlock(event.getRegistry(), PUMPKINCHEESECAKE, "pumpkincheesecakeitem");
        handleItemBlock(event.getRegistry(), REDVELVETCAKE, "redvelvetcakeitem");
    }

    private static void handleItemBlock(IForgeRegistry<Item> registry, Block block, String name) {
        ModContainer container = RegistryHelper.findContainerFor("harvestcraft");
        CreativeTabs tab = RegistryHelper.findCreativeTabFor("harvestcraft");
        if (container != null && tab != null) {
            registry.register(RegistryHelper.withRegistryName(
                    new ItemBlock(block) {
                        @Override
                        public String getUnlocalizedName() {
                            return "tile." + name;
                        }

                        @Override
                        public CreativeTabs getCreativeTab() {
                            return tab;
                        }
                    }, container, name)
            );
        }
    }

}
