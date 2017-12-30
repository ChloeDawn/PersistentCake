package net.insomniakitten.cake.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public final class RegistryHelper {

    private RegistryHelper() {}

    public static <V extends IForgeRegistryEntry<V>> V withRegistryName(V entry, ModContainer container, String name) {
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
    public static ModContainer findContainerFor(String modid) {
        for (ModContainer container : Loader.instance().getActiveModList()) {
            if (container != null && modid.equals(container.getModId())) {
                return container;
            }
        }
        return null;
    }

}
