function onStartSensor(){Logger.info("sensor starts");}
function onUpdateSensor()
{
	//Logger.info("sensor updates");
	if (self.has("goal"))
	{
		var goal = self.load("goal");
		//Logger.info("I hava a goal, current goal: " + goal.toString());
	} 
	else
	{
		// place to be for all calculations of a next goal.
	}
}
function onStopSensor(){Logger.info("sensor stops");}
function getName(){return "testscript";}