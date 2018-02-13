package net.insomniakitten.cake;

import net.insomniakitten.cake.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = PersistentCake.ID,
     name = PersistentCake.NAME,
     version = PersistentCake.VERSION,
     dependencies = PersistentCake.DEPENDENCIES)
@Mod.EventBusSubscriber(modid = PersistentCake.ID)
public final class PersistentCake {

    public static final String ID = "persistentcake";
    public static final String NAME = "Persistent Cake";
    public static final String VERSION = "%VERSION%";
    public static final String DEPENDENCIES = "after:applecore;after:harvestcraft";

    protected static final Logger LOGGER = LogManager.getLogger(ID);

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        if (!PersistentCakeConfig.vanilla) return;
        ModContainer minecraft = Loader.instance().getMinecraftModContainer();
        event.getRegistry().register(RegistryHelper.withRegistryName(new PersistentCakeBlock(), minecraft, "cake"));
    }

}
