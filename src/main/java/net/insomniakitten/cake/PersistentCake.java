package net.insomniakitten.cake;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

@Mod(modid = PersistentCake.ID, name = PersistentCake.NAME, version = PersistentCake.VERSION)
@Mod.EventBusSubscriber(modid = PersistentCake.ID)
public final class PersistentCake {

    public static final String ID = "persistentcake";
    public static final String NAME = "Persistent Cake";
    public static final String VERSION = "%VERSION%";

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        ModContainer minecraft = Loader.instance().getMinecraftModContainer();
        event.getRegistry().register(withRegistryName(new BlockPersistentCake(), minecraft, "cake"));

        ModContainer harvestcraft = findContainerFor("harvestcraft");
        if (harvestcraft != null) {
            event.getRegistry().registerAll(
                    withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "carrotcake"),
                    withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "cheesecake"),
                    withRegistryName(new BlockHarvestcraftCake(6), harvestcraft, "cherrycheesecake"),
                    withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "chocolatesprinklecake"),
                    withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "holidaycake"),
                    withRegistryName(new BlockHarvestcraftCake(3), harvestcraft, "lamingtoncake"),
                    withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "pavlovacake"),
                    withRegistryName(new BlockHarvestcraftCake(5), harvestcraft, "pineappleupsidedowncake"),
                    withRegistryName(new BlockHarvestcraftCake(4), harvestcraft, "pumpkincheesecake"),
                    withRegistryName(new BlockHarvestcraftCake(6), harvestcraft, "redvelvetcake")
            );
        }
    }

    private static <V extends IForgeRegistryEntry<V>> V withRegistryName(V entry, ModContainer container, String name) {
        if (container == null) throw new IllegalStateException("ModContainer instance cannot be null!");
        final ResourceLocation id = new ResourceLocation(container.getModId(), name);
        final ModContainer active = Loader.instance().activeModContainer();
        try {
            Loader.instance().setActiveModContainer(container);
            entry.setRegistryName(id);
            return entry;
        } finally {
            Loader.instance().setActiveModContainer(active);
        }
    }

    @Nullable
    private static ModContainer findContainerFor(String modid) {
        for (ModContainer container : Loader.instance().getActiveModList()) {
            if (container != null && modid.equals(container.getModId())) {
                return container;
            }
        }
        return null;
    }

}
