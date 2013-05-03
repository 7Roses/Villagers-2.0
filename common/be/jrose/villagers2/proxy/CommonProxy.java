package be.jrose.villagers2.proxy;

import be.jrose.villagers2.client.render.RenderVillager;
import be.jrose.villagers2.entity.GenericVillager;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CommonProxy {

	
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(GenericVillager.class, new RenderVillager(0.6F));
	}
}