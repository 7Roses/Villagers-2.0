package be.jrose.villagers2.village;

import java.util.ArrayList;

import be.jrose.villagers2.Coord;
import net.minecraft.util.Vec3;

/**
 * class representation of a village building
 * */
public class Building {

	private String buildingName; // identifies type,...
	private Coord beginPoint; // from wich coord
	private Coord eindPoint; // till which exists this building?
	private byte streetSide; // on what side is the road supposed to be connected?
	
	private ArrayList<BuildingOption> options; // list with options the building has, this can go from residents 1 villager till, has a Chest at...
	
	
}
