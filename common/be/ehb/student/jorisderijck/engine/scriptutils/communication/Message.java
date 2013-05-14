package be.ehb.student.jorisderijck.engine.scriptutils.communication;

import java.io.Serializable;

public class Message implements Serializable {

    /**
     *  serial id used for version control in the Serializable usage 
     */
    private static final long serialVersionUID = 1L;
 
    private String message;
    private int deliveryTime;
    
    public Message(String message,int deliveryTime)
    {
        this.message = message;
        this.deliveryTime = deliveryTime;
    }
    
    public String getMessageData()
    {
        return this.message;
    }
    
    public int getDeliveryTime(){return deliveryTime;}
}
