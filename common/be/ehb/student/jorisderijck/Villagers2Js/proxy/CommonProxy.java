package be.ehb.student.jorisderijck.Villagers2Js.proxy;

import be.ehb.student.jorisderijck.Villagers2Js.client.render.RenderVillager;
import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CommonProxy {

	
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(GenericVillager.class, new RenderVillager(0.6F));
	}
}
