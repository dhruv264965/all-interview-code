package AbstractFactoryDesignPattern;

public class NotificationFactory {
       public static Notification createNotifiction(NotificationAbstractFactory factory){
          return factory.createNotification();
       }
}
