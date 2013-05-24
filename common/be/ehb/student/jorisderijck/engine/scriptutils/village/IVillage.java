package be.ehb.student.jorisderijck.engine.scriptutils.village;

import java.util.List;
import java.util.UUID;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;
import be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building;

public interface IVillage {

    public abstract UUID getVillageId();

    /**
     * register a villager to this village
     * */
    public abstract void registerVillager(GenericVillager villager);

    /**
     * unregister a villagers from this village
     * for example if your villager want's to move to another village.
     * automatic implicit done when closing the game/server cause these information isn't persistent.
     * */
    public abstract void unregisterVillager(GenericVillager villager);

    public abstract int getAmountOfVillagers();

    public abstract void broadcast(Message message);

    public abstract void broadcast(GenericVillager sender, Message message);

    public abstract String sendMailTo(GenericVillager sender,
            GenericVillager reciever, Message message);

    public abstract String sendMailTo(GenericVillager sender, UUID recieverId,
            Message message);

    public abstract float distanceToVillage(int x, int y, int z);

    public abstract boolean registerBuilding(Building building);

    public abstract boolean unregisterBuilding(Building building);

    public abstract List<Building> getBuildingOfType(String buildingType);

    public abstract boolean registerConstruction(Building building);

    public abstract boolean endConstruction(Building building);

    public abstract boolean unregisterConstruction(Building building);

}