package be.ehb.student.jorisderijck.Villagers2Js;

import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class Villagers2JSEventHandler {
    
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    /**
     * combined for easier finding of all my worldload event listners.
     * */ // !! will be changed into sepperate classes later on.
    @ForgeSubscribe
    public void worldLoad(WorldEvent.Load event)
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            Villagers2Js.instance.scriptmanager.loadWorldScripts(event.world);
            Villagers2Js.instance.villagemanager.loadWorldVillages(event.world);
        }
    }
    
    @ForgeSubscribe
    public void worldSaves(WorldEvent.Save event)
    {
        log.info("saving should have worked? ");
    }
    
    @ForgeSubscribe
    public void worldUnload(WorldEvent.Unload event)
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            Villagers2Js.instance.villagemanager.unloadWorldVillages(event.world);
        }
    }
}
