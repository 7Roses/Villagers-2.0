package be.jrose.villagers2;

import java.util.logging.Logger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import be.jrose.scriptEngine.core.ScriptCache;
import be.jrose.scriptEngine.core.ScriptManager;
import be.jrose.scriptEngine.core.ScriptProcessor;
import be.jrose.villagers2.aiengine.core.AIEngine;
import be.jrose.villagers2.entity.GenericVillager;
import be.jrose.villagers2.lib.Reference;
import be.jrose.villagers2.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;


@Mod(
        modid=Reference.MOD_ID,
        name=Reference.MOD_NAME,
        version=Reference.VERSION
        )

@NetworkMod(
        clientSideRequired=true,
        serverSideRequired=false)// if installed on server, then needed on client. if installed on client, not needed on server (mod will just not work.)
public class Villagers2 {

	public static final boolean DEBUG = true;

	private static final Logger log = Logger.getLogger(Reference.MOD_ID);
	//my reference to the mod for forge
	@Instance("Villagers2.0")
	public static Villagers2 instance;
	
	// my scriptmanager instance
	public ScriptManager scriptmanager;
	
	// my AIEngine instance
	public static AIEngine aiEngine;
	
	@SidedProxy(clientSide="be.jrose.villagers2.proxy.ClientProxy",serverSide="be.jrose.villagers2.ServerProxy")
	public static CommonProxy proxy;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		final int mcTrackingRange = 8;//amount of block higher and lower than the current level pathfinding will look at
		final int updateFrequency = 3;// in 1/20th of a second units.
		EntityRegistry.registerModEntity(GenericVillager.class, "genericvillager", 1, this,
				mcTrackingRange, updateFrequency, true);
		registerEntityEgg(GenericVillager.class, 0x55ff55, 0x0000ff);
		
		// creating the script cache, the script processor, the script manager and give it the needed references to each other
		ScriptCache scriptcache = new ScriptCache();
		ScriptProcessor scriptEngine = new ScriptProcessor();
		this.scriptmanager = new ScriptManager(scriptcache,scriptEngine); // fill in the scriptmanager
		this.scriptmanager.loadScripts(); // call the scriptmanager so it loads the default scripts
		aiEngine = new AIEngine(scriptcache,scriptEngine);
		
	}
	
	// some helpfull codes for me:
	static int entityId = 256;
	
	public static void registerEntityEgg(Class<? extends Entity> entity,int color1,int color2){
		//find last unused id
		for(;EntityList.getStringFromID(entityId)!=null;entityId++);
		EntityList.addMapping(entity,"testString", entityId, color1, color2);
	}
	
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
	    
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){}
	
	
    @ForgeSubscribe
    public void worldLoad(WorldEvent.Load event)
    {
        if(event.world.isRemote) return;
        this.scriptmanager.loadWorldScripts(event.world);
    }
    
    @ForgeSubscribe
    public void worldUnload(WorldEvent.Unload event)
    {
        if(event.world.isRemote) return;
        // no code should be run or should it?still setting the hook for the moment (will be cleared once cleanup.
    }
	
	
}
