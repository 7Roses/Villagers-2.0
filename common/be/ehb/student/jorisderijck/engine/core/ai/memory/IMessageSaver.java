package be.ehb.student.jorisderijck.engine.core.ai.memory;

import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;

public interface IMessageSaver {

    /**
     * get first message that should have been delivered before or equal to the given time
     * and remove this message form the memory.
     * */
    public Message getMessage(int time);
    
    /**
     * add a message for delivery
     * */
    public boolean addMessage(Message message);
    
    /**
     * get the amount of message that needs to be delivered on or before the given time
     * */
    public int getMessageCount(int time);
    /**
     * get the amount of messages saved in the memory.
     * */
    public int getMessageCount();
}
