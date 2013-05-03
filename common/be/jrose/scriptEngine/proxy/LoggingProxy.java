package be.jrose.scriptEngine.proxy;

import java.util.logging.Logger;

import be.jrose.scriptEngine.proxy.scriptSideInterface.IScriptLoggerProxy;
import be.jrose.villagers2.Villagers2;

public class LoggingProxy implements IScriptLoggerProxy {


	
	private Logger log = Logger.getLogger(Villagers2.class.getName());
	
	public void info(Object obj)
	{
		log.info(obj.toString());
	}
	public void debug(Object obj)
	{
		log.finest(obj.toString());
	}

}
