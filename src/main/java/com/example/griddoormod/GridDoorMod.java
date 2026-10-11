package com.example.griddoormod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("griddoormod")
public class GridDoorMod {
    public static final String MODID = "griddoormod";
    
    public GridDoorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }
}
