package be.ehb.student.jorisderijck.engine.binds;

import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;

public interface IMemoryProxy {

    public abstract void forget(String variableName);

    public abstract void save(String variableName, Object value);

    public abstract Object load(String variableName);

    public abstract Boolean has(String variableName);

    public abstract Message getNextMessage(int time);
    
}