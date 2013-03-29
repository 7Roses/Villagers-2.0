package be.jrose.villagers2;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import be.jrose.villagers2.entity.GenericVillager;
import be.jrose.villagers2.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;


@Mod(modid="Villagers2.0",name="Villagers2.0",version="dev-Version 0.0.1")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)// if installed on server, then needed on client. if installed on client, not needed on server (mod will just not work.)
public class Villagers2 {

	//my reference to the mod for forge
	@Instance("Villagers2.0")
	public static Villagers2 instance;
	
	@SidedProxy(clientSide="be.jrose.villagers2.proxy.ClientProxy",serverSide="be.jrose.villagers2.ServerProxy")
	public static CommonProxy proxy;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		final int mcTrackingRange = 80;//in blocks
		final int updateFrequency = 3;// in 1/20th of a second units.
		EntityRegistry.registerModEntity(GenericVillager.class, "genericvillager", 1, this,
				mcTrackingRange, updateFrequency, true);
		registerEntityEgg(GenericVillager.class, 0x55ff55, 0x0000ff);
	}
	
	// some helpfull codes for me:
	static int entityId = 256;
	
	public static void registerEntityEgg(Class<? extends Entity> entity,int color1,int color2){
		//find last unused id
		for(;EntityList.getStringFromID(entityId)!=null;entityId++);
		EntityList.addMapping(entity,"testString", entityId, color1, color2);
	}
}
