package uz.o_rustamov.magnitcrm.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public void sendNotification(String title, String body, String token) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .build();
        firebaseMessaging.send(message);

        String testToken = "dwrA9euSQiSl3Pu6lp-9pF:APA91bGdh_VEQ4PRmVQOe-AiSs60c8T-cxLFwzmz3k3M_IztfLyjkr69lkbIHlFk1XT7sgqZdv4C6LXpXUvjZ_xOIckCaT8I2fb-Uhcm7hRm4dZiWGhzWs8BaRztkCjt5gR8ryyWpjOy";
    }
}
