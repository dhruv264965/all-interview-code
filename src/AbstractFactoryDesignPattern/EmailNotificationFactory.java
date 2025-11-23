package AbstractFactoryDesignPattern;

public class EmailNotificationFactory extends NotificationAbstractFactory{
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
