package be.ehb.student.jorisderijck.engine.binds.facades;

import net.minecraft.pathfinding.PathNavigate;
import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.engine.binds.IMemoryProxy;
import be.ehb.student.jorisderijck.engine.binds.ISensorProxy;
import be.ehb.student.jorisderijck.engine.binds.IVillagerProxy;
import be.ehb.student.jorisderijck.engine.binds.MemoryProxy;
import be.ehb.student.jorisderijck.engine.binds.SensorProxy;
import be.ehb.student.jorisderijck.engine.binds.VillagerProxy;
import be.ehb.student.jorisderijck.engine.binds.facades.*;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;

public class ScriptSelfReflection implements IAgentBinder,IMemoryProxy,ISensorProxy,IVillagerProxy {

    public GenericVillager javaref;
    private MemoryProxy memoryproxy;
    private SensorProxy sensorproxy;
    private VillagerProxy villagerproxy;
    
    public ScriptSelfReflection()
    {
        memoryproxy = new MemoryProxy();
        sensorproxy = new SensorProxy();
        villagerproxy = new VillagerProxy();
    }
    
    @Override
    public void setAgent(GenericVillager villager)
    {
        this.memoryproxy.setMemory(villager.getMemory());
        this.sensorproxy.setAgent(villager);
        this.villagerproxy.setAgent(villager);
        this.javaref = villager;
    }

    //IVillagerProxy
    @Override public boolean setDestination(Integer[] destination){
        return villagerproxy.setDestination(destination);
        }
    @Override public Integer[] getDestination() {return villagerproxy.getDestination();}
    @Override public PathNavigate getNavigator() {return villagerproxy.getNavigator();}
    @Override public void buildBlock(int x, int y, int z, int blockId) {villagerproxy.buildBlock(x, y, z, blockId);}

    //ISensorProxy
    @Override public int age() {return sensorproxy.age();}
    @Override public Integer[] getPosition() {return sensorproxy.getPosition();}
    @Override public Long getWorldTime() {return sensorproxy.getWorldTime();}

    //IMemoryProxy
    @Override public void forget(String variableName) {memoryproxy.forget(variableName);}
    @Override public void save(String variableName, Object value) {memoryproxy.save(variableName, value);}
    @Override public Object load(String variableName){return memoryproxy.load(variableName);}
    @Override public Boolean has(String variableName) {return memoryproxy.has(variableName);}
    @Override public Message getNextMessage(int time){return memoryproxy.getNextMessage(time);}
 
}
