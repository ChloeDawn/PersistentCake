package net.insomniakitten.cake;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = PersistentCake.ID, name = PersistentCake.NAME)
@Mod.EventBusSubscriber(modid = PersistentCake.ID)
public final class PersistentCakeConfig {

    @Config.Comment("Should Harvestcraft compatibility be enabled?")
    public static boolean harvestcraft = true;

    @Config.Comment("Should vanilla compatibility be enabled?")
    public static boolean vanilla = true;

    private PersistentCakeConfig() {}

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (PersistentCake.ID.equals(event.getModID())) {
            ConfigManager.sync(PersistentCake.ID, Config.Type.INSTANCE);
        }
    }

}