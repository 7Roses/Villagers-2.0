package be.ehb.student.jorisderijck.engine.core.ai.village;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.util.ChunkCoordinates;
import be.ehb.student.jorisderijck.engine.binds.IWorldVillageFinder;
import be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage;
import be.ehb.student.jorisderijck.engine.scriptutils.village.Village;

/**
 * class for representing all the villages in a certain world save's dimension
 * 
 * also will hold a value for active/inactive villages
 * (a active village is a village where an villager has registered itself in a world not unloaded, unloaded chunks don't count in this status.)
 * 
 * */
public class WorldVillageContainer implements Serializable, IWorldVillageFinder {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Village> villages;
    
    
    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.core.ai.village.IWorldVillageFinder#getClossestVillage(int, int, int)
     */
    @Override
    public Village getClossestVillage(int x, int y, int z)
    {
        Village clossest = null;
        float distance = Float.MAX_VALUE;
        for (Village v:villages)
        {
            float dist = v.distanceToVillage(x, y, z); 
            if (dist< distance){
                distance = dist;
                clossest = v;
            }
        }
        return clossest;
    }
    
     /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.core.ai.village.IWorldVillageFinder#getOrCreateVillageNear(int, int, int, int)
     */
    @Override
    public IVillage getOrCreateVillageNear(int x, int y, int z, int range)
    {
        Village clossest = getClossestVillage(x,y,x);
        if (clossest == null || clossest.distanceToVillage(x, y, z)>range)
        {
            // create new village and add to list
            clossest = new Village(new ChunkCoordinates(x, y, z));
            villages.add(clossest);
        }
        return clossest;
    }
    
    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.core.ai.village.IWorldVillageFinder#getVillageById(java.util.UUID)
     */
    @Override
    public IVillage getVillageById(UUID villageId){
        // run trough all villages, ask for every time for the UUID
        // if found return the village vound
        
        for(IVillage v:villages)
        {
            if (v.getVillageId().equals(villageId)) return v;
        }
        return null;
    }
    
}
