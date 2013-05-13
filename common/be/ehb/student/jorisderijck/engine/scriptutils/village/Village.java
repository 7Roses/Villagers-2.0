package be.ehb.student.jorisderijck.engine.scriptutils.village;

import java.util.ArrayList;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;
import be.ehb.student.jorisderijck.engine.scriptutils.jobs.AbstractJob;
import be.ehb.student.jorisderijck.engine.scriptutils.village.buildings.Building;


/**
 * class representing an village for the mod Villagers2
 * 
 * */
public class Village {

	/**
	 * array with the current buildings fully functional in this village
	 * */
	ArrayList<Building> buildings;
	
	/**
	 * array with the current building under construction
	 * */
	ArrayList<Building> construction;
	
	/**
	 * queu for jobs requested to be done on a later date by some villager
	 * (this can be used for distribution of jobs over the hole village population)
	 * */
	ArrayList<AbstractJob> queedJobs;
	
	/**
	 * array of registered villagers
	 * */
	ArrayList<GenericVillager> villagers;
	
	
	public void registerVillager(GenericVillager villager){}
	public void unregisterVillager(GenericVillager villager){}
	
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
	
	public void broadcast(GenericVillager sender,Message mesage)
	{
		for(GenericVillager v:villagers)
		{
			// send the data to them...
		}
	}
	
}
