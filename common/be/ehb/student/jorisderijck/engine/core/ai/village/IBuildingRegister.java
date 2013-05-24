package be.ehb.student.jorisderijck.engine.core.ai.village;

import java.util.List;

import be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building;

/**
 * every class who implements these will be able to register buildings. unregister them and register constructions
 * */
public interface IBuildingRegister {

    /**
     * registers an building, returns true if successfull, false otherwise
     * */
    public boolean registerBuilding(Building building);
    /** unregister an building, returns true if succeed.*/
    public boolean unregisterBuilding(Building building);
    /** returns an list of buildings for the given type, returns null if non where found*/
    public List<Building> getBuildingOfType(String buildingType); // maybe an enumeration is better? although less generic
    
    
    
    public boolean registerConstruction(Building building);
    public boolean endConstruction(Building building);
    public boolean unregisterConstruction(Building building);
    
    
}
