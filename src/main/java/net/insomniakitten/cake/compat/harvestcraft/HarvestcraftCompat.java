package net.insomniakitten.cake.compat.harvestcraft;

import net.insomniakitten.cake.PersistentCake;
import net.insomniakitten.cake.PersistentCakeConfig;
import net.insomniakitten.cake.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
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
        if (!PersistentCakeConfig.harvestcraft) return;
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
        Block original = event.getRegistry().getValue(new ResourceLocation("harvestcraft", blockName));
        ModContainer container = RegistryHelper.findContainerFor("harvestcraft");
        if (container != null && original != null && original != Blocks.AIR) {
            Block block = new HarvestcraftCakeBlock(original, itemName, maxBites);
            event.getRegistry().register(RegistryHelper.withRegistryName(block, container, blockName));
        }
    }

}
