package be.jrose.villagers2.village;

import java.util.ArrayList;

import be.jrose.villagers2.aiengine.Message;
import be.jrose.villagers2.aiengine.core.Job.AbstractJob;
import be.jrose.villagers2.entity.GenericVillager;


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
