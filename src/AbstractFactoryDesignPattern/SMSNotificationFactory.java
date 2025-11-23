package AbstractFactoryDesignPattern;

public class SMSNotificationFactory extends NotificationAbstractFactory{
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}
