package be.jrose.scriptEngine.proxy;

import java.util.logging.Logger;

import be.jrose.scriptEngine.proxy.scriptSideInterface.IScriptLoggerProxy;

public class LoggingProxy implements IScriptLoggerProxy {


	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	public void info(Object obj)
	{
		log.info(obj.toString());
	}
	public void debug(Object obj)
	{
		log.finest(obj.toString());
	}
	public void sysout(Object obj){
		System.out.println(obj);
	}

}
