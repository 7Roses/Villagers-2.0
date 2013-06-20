package be.ehb.student.jorisderijck.engine.scriptutils.communication;

public class MessageUtils implements IMessageUtils {

    @Override
    public Message createMessage(String message, int time)
    {
        return new Message(message,time);
    }

}
