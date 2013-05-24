package be.ehb.student.jorisderijck.engine.binds;

import java.util.UUID;

import be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage;
import be.ehb.student.jorisderijck.engine.scriptutils.village.Village;

public interface IWorldVillageFinder {

    public abstract Village getClossestVillage(int x, int y, int z);

    /**
     * same as {@link #getClossestVillage(int, int, int)} except that if no village found within parameter range you will get a new created village
     * */
    public abstract IVillage getOrCreateVillageNear(int x, int y, int z,
            int range);

    public abstract IVillage getVillageById(UUID villageId);

}