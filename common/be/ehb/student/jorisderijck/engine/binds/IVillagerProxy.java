package be.ehb.student.jorisderijck.engine.binds;

import net.minecraft.pathfinding.PathNavigate;

public interface IVillagerProxy {

    public abstract boolean setDestination(Integer[] destination);

    public abstract Integer[] getDestination();

    public abstract PathNavigate getNavigator();

    public abstract void buildBlock(int x, int y, int z, int blockId);

}