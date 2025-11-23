package FactoryDesignPattern;

import java.util.List;

public class PushNotification implements Notification{
    @Override
    public void sendNotification(List<String> users, String message) {
        for(String user :users){
            System.out.println("Send push:" +user+"  "+message);
        }
    }
}
