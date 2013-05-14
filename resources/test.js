function onStartSensor(){
	Logger.info("sensor starts");
	Logger.info("forgeting the save value \'test\'");
	self.forget("test");
}
function onUpdateSensor()
{
	var goal = self.load("goal");
	if (goal != null)
	{
		//Logger.info("I hava a goal, current goal: " + goal.toString());
		processGoalStep(goal)
	} else {
		// place to be for all calculations of a next goal.
		goal = utils.createAtomicJob("sayHellow");
	}
	
	var testvar = self.load("test"); // load some var in
	if (testvar!=null)
	{
		Logger.info("I found" + testvar);
		
	}
	else
	{
		Logger.debug("teststring is been saved into test !");
		self.save("test","teststring");
	}
}
function onStopSensor(){Logger.info("sensor stops");}
function getName(){return "testscript";}

function processGoalStep(goal)
{
	Logger.debug(goal.getName());
	//Logger.debug("test Debug");
}