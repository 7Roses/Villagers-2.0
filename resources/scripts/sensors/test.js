function getName() {return "testbuildRoad";}
function onStartSensor()
{
	Logger.info("sensor starts");
	self.save("test",-1);
	var loc = sensors.getPosition();
	loc[0] = 3;
	loc[1] = 4;
	loc[2] = 5;
	self.setDestination(loc);
}
function onUpdateSensor()
{
	//log.log("sensor updates");
	var age = self.age();
	//Logger.debug("WORLD TIME: "+sensors.getWorldTime());
	var time = self.getWorldTime();
	//log.log("world time: "+ time);
	var test = self.load("test");
	if (test ==-1)
	{
		
		test = 1;
		self.save("test",test);
		Logger.info("some single use code");

	}
	var loc = self.getPosition();
	//log.log("location: x="+loc[0]+"\ty="+loc[1]+"\tz="+loc[2]);
	if (loc[0] == 3 && loc[1] == 4 && loc[2] == 5)
	{
		self.buildBlock(loc[0],loc[1]-1,loc[2],1);
		self.buildBlock(loc[0]-1,loc[1]-1,loc[2],1);
		self.buildBlock(loc[0]+1,loc[1]-1,loc[2],1);
	}
	var dest = self.getDestination();
	//log.info("destination: x="+dest[0]+"\ty="+dest[1]+"\tz="+dest[2]);
	

	//log.log(test);
}
function onStopSensor(){Logger.info("sensor stops");}

//onUpdateSensor();


//BeBornJob()
// this job will find the closest other villager and request some data from it.
// then it will save those data in it's self

/*

function onStartJob()
{
	// to be shure the variable exists in the agent's self I register my fariables at startup.
	self.register("my_father","Integer"); // all those values will be ID's of the existing objects (or not existing anymore...)
	self.register("my_mother","Integer");
	self.register("my_village","Intger");
}

function onUpdateJob()
{
	system.registerEventHandler("scripts/eventHandlers/Communicate.js",communcate.EVENTID); // this has a function onEvent(event) wich will handle events of kind communication
	var villager = sensors.getClosestVillager(); // you gona be one of my parents!
	var sexOfFirstParrent = communicate.sendMessage(villager,"your sex?"); // communicate api will send messages to all the registered eventHandlers for type Event.COMMUNICATION
	if (sexOfFirstParrent == "v")
	{
		self.save("my_mother",villager);
		var village = communicate.sendMessage(village,"your village?");
		self.save("my_village",village);

	}
	
	villager = communicate.sendMessage(villager,"who's your partner?");
	self.save("my_father",villager);
	
	// if you would want to stop the current script itself use follow code: system.stop(SCRIPTNAME); // SCRIPTNAME is a variable bind to the current <T extends Script>.getSriptLocation()
}

// when a job is finished, just before it's deleted from the agent's job list.
function onStopJob()
{

}

// if a job awakes from sleeping
function onAwakeJob()
{
	
}

// if a job is put to sleep
function onSleepJob()
{
}

//*/