package FactoryDesignPattern;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Notification notification=NotificationFactory.createNotification("SMS");
        if(notification!=null){
            notification.sendNotification(List.of("Dhruv","Ankit"),"your course is completed");
        }
        Notification notification1=NotificationFactory.createNotification("Email");
        if(notification1!=null){
            notification1.sendNotification(List.of("Dhruv","Ankit"),"your course is completed");
        }
        Notification notification2=NotificationFactory.createNotification("Push");
        if(notification2!=null){
            notification2.sendNotification(List.of("Dhruvv","Ankit"),"your course is completed");
        }
    }
}
