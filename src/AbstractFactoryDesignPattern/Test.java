package AbstractFactoryDesignPattern;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Notification notifiction = NotificationFactory.createNotifiction(new SMSNotificationFactory());
        notifiction.sendNotification(List.of("Dhruv","ankit"),"how are teams");
    }
}
