package be.ehb.student.jorisderijck.engine.core.script;

public class ScriptContainer<T> {

	private T script;
	private String scriptName;
	private String scriptPath;
	// next tree values will not be needed in the first release, those will be used for auto-updating the scripts while playing
	// (or for servers while running)
	private int currentHash;
	private boolean needsCompiling;
	private boolean invalidFile = false; // when true, will be removed from the valid list of scripts.
	
	/**
	 * constructor for empty creation
	 * 
	 * 
	 * */
	public ScriptContainer()
	{
		this.needsCompiling = false;
		this.currentHash = 0;
		this.scriptName = "empty";
		this.scriptPath = "";
		
	}
	
	public ScriptContainer(String scriptName,String scriptPath)
	{
		this.scriptName = scriptName;
		this.scriptPath = scriptPath;
		this.currentHash = makeHash(this);
		this.needsCompiling = true;
	}

	public String getName(){return this.scriptName;}
	public T getScript(){return this.script;}
	public String getPath(){return this.scriptPath;}
	public boolean isValid(){return !this.invalidFile;}
	
	public void setName(String name){this.scriptName = name;}
	public void setScript(T script){this.script = script;}
	public void setPath(String path){this.scriptPath = path;}
	public void invalidate(){this.invalidFile = true;}
	
	private static int makeHash(ScriptContainer subject){return 0;}
}
