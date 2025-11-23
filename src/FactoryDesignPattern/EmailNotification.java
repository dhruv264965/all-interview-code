package FactoryDesignPattern;

import java.util.List;

public class EmailNotification implements Notification{
    @Override
    public void sendNotification(List<String> users, String message) {
        for(String user :users){
            System.out.println("Send Email:" +user+"  "+message);
        }
    }
}
