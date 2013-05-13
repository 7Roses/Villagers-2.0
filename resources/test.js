function onStartSensor(){Logger.info("sensor starts");}
function onUpdateSensor()
{
	//Logger.info("sensor updates");
	/*if (memory.has("goal"))
	{
		var goal = memory.load("goal");
		Logger.info("I hava a goal, current goal: " + goal.toString());
	} 
	else
	{*/
		//Logger.info("I don't hava a goal, gathering data for goal selection!") 
		// here the main script will gather extra information about the village I'm close by and about what's missing here or is already been reported
		
	/*}*/
}
function onStopSensor(){Logger.info("sensor stops");}
function getName(){return "testscript";}