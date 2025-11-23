package AbstractFactoryDesignPattern;

import java.util.List;

public interface Notification {
     void sendNotification(List<String> users, String message);
}
