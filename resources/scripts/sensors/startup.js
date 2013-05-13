// fist script / configuration script......

function onStopSensor(){Logger.info("sensor stops");}
function getName() {return "firstYear";}

function onStartSensor()
{
	//DEBUG, UNTIL THE REST IS BUILD
	memory.save("goal","none");

	/*var villager = sensors.getClosestVillager();
	if (villager == null)
	{
		memory.save("father",self); // or maybe null
		memory.save("Mother",self);
	}
	else
	{
		var gender = communcation.ask(villager,Question.Gender);
		if (gender == "m")
		{
			memory.save("father",villager);
			var mother = communication.ask(villager,Question.Partner);
			memory.save("mother",mother);
		}
		else
		{
			memory.save("mother",villager);
			var father = communication.ask(villager,Question.Partner);
			memory.save("father",mother);
		}
	}*/
	Logger.info("this is a log message from the " + getName() + "script");
	// search for my village block.
	/*
		the villager will use this later to find all other villagers and possible issue work to others (for example if he needs something and there are idle villagers..)
		also for finding a partner and for others like building houses ect.
	*/
	// villagerproxy.getVillageAt will search for a village at this coord, if not found it will create a village at that point and return the new created one.
//	var village = villageProxy.getVillageAt(self.x,self.y,self.z);
	//memory.save("myVillage",village);
	
	/*
		now we know the village of us, we register ourself to it.
	*/
	//village.registerMember(self.reference); // self.reference references to the java object of the villager, but because of obfuscration it's ill advised to call functions on it.
	
	/*
		1. who are my parents.
		2. where do I live?
		
		and now
		3. what can I do?
	*/
//	var goal = JobHelper.createAtomicalJob("none"); // get an EMPTY job, basically it says ther isn't a job
//	memory.save("goal",goal)// save this goal so you know of it the next script cycle.
}

function onUpdateSensor()
{
	// basic goal searcher, if no goal make new goal
	var goal = memory.load("goal");
	if (goal.getName() == "none")
	{
	
	}
}


/*
function searchJobs()
{
	var village = memory.load("myVillage");
	var BuildingList = village.getBuildingList();
	var amountOfVillagers = village.getVillagers();
	for() // search all resident capacity
	
}*/

