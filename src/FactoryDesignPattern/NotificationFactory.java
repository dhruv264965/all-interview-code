package FactoryDesignPattern;

public class NotificationFactory {
    public static Notification createNotification(String channel){
        if(channel.equalsIgnoreCase("SMS")){
            return new SMSNotification();
        }
        else if(channel.equalsIgnoreCase("Email")){
            return new EmailNotification();
        }
        else if (channel.equalsIgnoreCase("Push")){
            return new PushNotification();
        }
        else {
            return null;
        }
    }
}
