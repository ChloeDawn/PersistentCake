package net.insomniakitten.cake;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PersistentCake.ID, name = PersistentCake.NAME, version = PersistentCake.VERSION)
@Mod.EventBusSubscriber(modid = PersistentCake.ID)
public final class PersistentCake {

    public static final String ID = "persistentcake";
    public static final String NAME = "Persistent Cake";
    public static final String VERSION = "%VERSION%";

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockPersistentCake());
    }

}
