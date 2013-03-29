package be.jrose.villagers2.aiengine.scriptproxy;


public interface IMemoryProxy {
	
	
	/**
	 *  same as {@link #save(String, Object)} but with an new Boolean(true) as value
	 * */
	public void save(String variableName,Object value);
	public void forget(String variableName);
	
	// following methods are under consideration and could change by next build.
	public Object load(String variableName);
	public Boolean has(String variableName);
}
