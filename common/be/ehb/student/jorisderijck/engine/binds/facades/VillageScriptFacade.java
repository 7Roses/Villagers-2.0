package be.ehb.student.jorisderijck.engine.binds.facades;

import java.util.UUID;

import net.minecraft.pathfinding.PathNavigate;
import be.ehb.student.jorisderijck.Villagers2Js.Villagers2Js;
import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.engine.binds.IVillagerProxy;
import be.ehb.student.jorisderijck.engine.binds.IWorldVillageFinder;
import be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage;
import be.ehb.student.jorisderijck.engine.scriptutils.village.Village;

public class VillageScriptFacade implements IAgentBinder, IWorldVillageFinder {
    
    private GenericVillager agent;
    @Override
    public void setAgent(GenericVillager villager)
    {
        this.agent = villager;
    }
    
    @Override
    public Village getClossestVillage(int x, int y, int z)
    {
        return Villagers2Js.instance.villagemanager.getWorldVillageContainer(agent.worldObj).getClossestVillage(x, y, z);
    }
    
    @Override
    public IVillage getOrCreateVillageNear(int x, int y, int z, int range)
    {
        return Villagers2Js.instance.villagemanager.getWorldVillageContainer(agent.worldObj).getOrCreateVillageNear(x, y, z, range);
    }
 
    @Override
    public IVillage getVillageById(UUID villageId)
    {
        return Villagers2Js.instance.villagemanager.getWorldVillageContainer(agent.worldObj).getVillageById(villageId);
    }
}
