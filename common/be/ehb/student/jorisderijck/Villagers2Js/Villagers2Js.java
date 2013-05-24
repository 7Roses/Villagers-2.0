package be.ehb.student.jorisderijck.Villagers2Js;

import java.util.logging.Logger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.common.MinecraftForge;
import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.Villagers2Js.proxy.CommonProxy;
import be.ehb.student.jorisderijck.engine.core.ai.AIEngine;
import be.ehb.student.jorisderijck.engine.core.ai.memory.MemorySaveHandler;
import be.ehb.student.jorisderijck.engine.core.ai.village.VillageManager;
import be.ehb.student.jorisderijck.engine.core.script.ScriptCache;
import be.ehb.student.jorisderijck.engine.core.script.ScriptManager;
import be.ehb.student.jorisderijck.engine.core.script.ScriptProcessor;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
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
public class Villagers2Js {

	public static final boolean DEBUG = true;

	private static final Logger log = Logger.getLogger(Reference.MOD_ID);
	//my reference to the mod for forge
	@Instance(Reference.MOD_ID)
	public static Villagers2Js instance;
	
	// my scriptmanager instance
	public ScriptManager scriptmanager;
	
	public VillageManager villagemanager;
	
	// my AIEngine instance
	public static AIEngine aiEngine;
	
	/** my MemorySaveHandler instance*/
	public static MemorySaveHandler memorySaveHandler;
	
	@SidedProxy(clientSide=Reference.CLIENTSIDEPROXY,serverSide=Reference.SERVERSIDEPROXY)
	public static CommonProxy proxy;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		final int updateFrequency = 3;// in 1/20th of a second units.
		EntityRegistry.registerModEntity(GenericVillager.class, "genericvillager", 1, this,
				Reference.RENDERDISTANCE, updateFrequency, true);
		registerEntityEgg(GenericVillager.class, 0x55ff55, 0x0000ff);
		
		// creating the script cache, the script processor, the script manager and give it the needed references to each other
		ScriptCache scriptcache = new ScriptCache();
		ScriptProcessor scriptEngine = new ScriptProcessor();
		this.scriptmanager = new ScriptManager(scriptcache,scriptEngine); // fill in the scriptmanager
		this.scriptmanager.loadScripts(); // call the scriptmanager so it loads the default scripts
		aiEngine = new AIEngine(scriptcache,scriptEngine);
		memorySaveHandler = new MemorySaveHandler();
		
		//add VillageManager to the mod:
		this.villagemanager = new VillageManager();
		//TickRegistry.registerTickHandler(new VillageTick(), Side.SERVER); // not needed, get World.getTotalTime() for time, so don't us a tick for counting the ticks a village exists.
		
		MinecraftForge.EVENT_BUS.register(new Villagers2JSEventHandler());
		
		log.info("mod succesfull loaded");
	}
	
	// some helpfull codes for me:
	static int entityId = 256;
	
	public static void registerEntityEgg(Class<? extends Entity> entity,int color1,int color2){
		//find last unused id
		for(;EntityList.getStringFromID(entityId)!=null;entityId++);
		EntityList.addMapping(entity,"GenericVillager", entityId, color1, color2);
	}
	
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
	    
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){}
	

	
}
