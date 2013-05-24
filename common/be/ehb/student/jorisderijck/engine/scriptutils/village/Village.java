package be.ehb.student.jorisderijck.engine.scriptutils.village;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import net.minecraft.util.ChunkCoordinates;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.AIEngineConstants;
import be.ehb.student.jorisderijck.engine.core.ai.village.IBuildingRegister;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;
import be.ehb.student.jorisderijck.engine.scriptutils.jobs.AbstractJob;
import be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building;


/**
 * class representing an village for the mod Villagers2
 * 
 * */
public class Village implements IBuildingRegister, Serializable, IVillage{

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private UUID villageId;
    /**
     * the reference of the world where this Village exists in.
     * */
    
    private ChunkCoordinates centerOfTheVillage;
    
    
	/**
	 * array with the current buildings fully functional in this village
	 * */
	private ArrayList<Building> buildings;
	
	/**
	 * array with the current building under construction
	 * */
	private ArrayList<Building> construction;
	
	/**
	 * Queue for Messages that couldn't be delivered to a villager (only in case it asked a specified UUID)
	 * this message won't be saved when the server closes down!
	 * */
	private transient ArrayList<AbstractJob> queedMessages;
	
	/**
	 * array of registered villagers
	 * */
	private HashSet<GenericVillager> villagers;
	
	
	public Village(ChunkCoordinates chunkCoordinates) {
	    this.villageId = UUID.randomUUID();
        this.centerOfTheVillage = chunkCoordinates;
        this.buildings = new ArrayList<Building>();
        this.construction = new ArrayList<Building>();
        this.queedMessages = new ArrayList<AbstractJob>(); // be aware if loaded from file this won't be done!
    }

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#getVillageId()
     */
	@Override
    public UUID getVillageId(){return this.villageId;}

    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#registerVillager(be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager)
     */
	@Override
    public void registerVillager(GenericVillager villager)
	{
	    villagers.add(villager);
	}
	
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#unregisterVillager(be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager)
     */
	@Override
    public void unregisterVillager(GenericVillager villager)
	{
	    villagers.remove(villager);
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#getAmountOfVillagers()
     */
	@Override
    public int getAmountOfVillagers()
	{
		int ret = 0;
		for(GenericVillager v:villagers)
		{
			if(!v.isDead)
			{
				ret++;
			}
		}
		return ret;
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#broadcast(be.ehb.student.jorisderijck.engine.scriptutils.communication.Message)
     */
	@Override
    public void broadcast(Message message)
    {
        for(GenericVillager v:villagers)
        {
            v.getMemory().addMessage(message);
        }
    }
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#broadcast(be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager, be.ehb.student.jorisderijck.engine.scriptutils.communication.Message)
     */
	@Override
    public void broadcast(GenericVillager sender,Message message)
	{
		for(GenericVillager v:villagers)
		{
			if (sender.getPersistentID().equals(v.getPersistentID())) continue; // Don't send to yourself!
			    v.getMemory().addMessage(message);
		}
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#sendMailTo(be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager, be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager, be.ehb.student.jorisderijck.engine.scriptutils.communication.Message)
     */
	@Override
    public String sendMailTo(GenericVillager sender,GenericVillager reciever,Message message)
	{
	    if (this.villagers.contains(reciever))
	    {
	        return sendMail(sender,reciever,message);
	    } else {
	        return sendMailTo(sender,reciever.getPersistentID(),message);
	    }
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#sendMailTo(be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager, java.util.UUID, be.ehb.student.jorisderijck.engine.scriptutils.communication.Message)
     */
	@Override
    public String sendMailTo(GenericVillager sender,UUID recieverId,Message message)
	{
	    GenericVillager foundVillager = null;
	    for(GenericVillager v:villagers)
	    {
	        if (v.getPersistentID().equals(recieverId))
	        {
	            foundVillager = v;
	        }
	    }
	    
	    if (foundVillager != null)
	    {
	        return sendMail(sender,foundVillager,message);
	    }
	    return AIEngineConstants.MSG_VILLAGERNOTFOUND;
	}
	
	private String sendMail(GenericVillager sender,GenericVillager reciever,Message message)
	{
	    String exception = AIEngineConstants.MSG_MESSAGEDELIVERED;
	    reciever.getMemory().addMessage(message);
	    return exception;
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#distanceToVillage(int, int, int)
     */
	@Override
    public float distanceToVillage(int x, int y, int z)
	{
	    return this.centerOfTheVillage.getDistanceSquared(x, y, z);
	}
    
	
    // FUNCTIONS STILL IN NEED OF IMPLEMENTATION
    // this will probably in the next release

    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#registerBuilding(be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building)
     */
    @Override
    public boolean registerBuilding(Building building)
    {
        // TODO Auto-generated method stub
        return false;
    }


    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#unregisterBuilding(be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building)
     */
    @Override
    public boolean unregisterBuilding(Building building)
    {
        // TODO Auto-generated method stub
        return false;
    }


    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#getBuildingOfType(java.lang.String)
     */
    @Override
    public List<Building> getBuildingOfType(String buildingType)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#registerConstruction(be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building)
     */
    @Override
    public boolean registerConstruction(Building building)
    {
        // TODO Auto-generated method stub
        return false;
    }


    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#endConstruction(be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building)
     */
    @Override
    public boolean endConstruction(Building building)
    {
        // TODO Auto-generated method stub
        return false;
    }


    /* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.scriptutils.village.IVillage#unregisterConstruction(be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building)
     */
    @Override
    public boolean unregisterConstruction(Building building)
    {
        // TODO Auto-generated method stub
        return false;
    }
	

	
}
