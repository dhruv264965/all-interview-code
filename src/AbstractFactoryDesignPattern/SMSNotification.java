package AbstractFactoryDesignPattern;

import java.util.List;

public class SMSNotification implements Notification {
    @Override
    public void sendNotification(List<String> users, String message) {
        for(String user :users){
            System.out.println("Send SMS:" +user+"  "+message);
        }
    }
}
