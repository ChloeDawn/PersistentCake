package net.insomniakitten.cake.compat.harvestcraft;

import net.insomniakitten.cake.PersistentCake;
import net.insomniakitten.cake.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = PersistentCake.ID)
public final class HarvestcraftCompat {

    private HarvestcraftCompat() {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        registerCake(event, "carrotcake", "carrotcakeitem", 4);
        registerCake(event, "cheesecake", "cheesecakeitem", 5);
        registerCake(event, "cherrycheesecake", "cherrycheesecakeitem", 6);
        registerCake(event, "chocolatesprinklecake", "chocolatesprinklecakeitem", 4);
        registerCake(event, "holidaycake", "holidaycakeitem", 5);
        registerCake(event, "lamingtoncake", "lamingtonitem", 3);
        registerCake(event, "pavlovacake", "pavlovaitem", 5);
        registerCake(event, "pineappleupsidedowncake", "pineappleupsidedowncakeitem", 5);
        registerCake(event, "pumpkincheesecake", "pumpkincheesecakeitem", 4);
        registerCake(event, "redvelvetcake", "redvelvetcakeitem", 6);
    }

    private static void registerCake(RegistryEvent.Register<Block> event, String blockName, String itemName, int maxBites) {
        ModContainer container = RegistryHelper.findContainerFor("harvestcraft");
        CreativeTabs tab = RegistryHelper.findCreativeTabFor("harvestcraft");
        if (container != null && tab != null) {
            Block block = new BlockHarvestcraftCake(itemName, maxBites).setUnlocalizedName(blockName).setCreativeTab(tab);
            event.getRegistry().register(RegistryHelper.withRegistryName(block, container, blockName));
        }
    }

}
