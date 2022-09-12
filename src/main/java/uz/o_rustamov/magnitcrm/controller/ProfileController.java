package uz.o_rustamov.magnitcrm.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.annotation.CurrentUser;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.fcm.FirebaseMessagingService;
import uz.o_rustamov.magnitcrm.fcm.Notification;
import uz.o_rustamov.magnitcrm.payload.ChangePasswordDto;
import uz.o_rustamov.magnitcrm.service.ProfileServiceImpl;

import static uz.o_rustamov.magnitcrm.Constants.SUCCESS;

@Transactional
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    ProfileServiceImpl profileService;
    FirebaseMessagingService fcm;

    public ProfileController(ProfileServiceImpl profileService, FirebaseMessagingService messagingService) {
        this.profileService = profileService;
        this.fcm = messagingService;
    }

    @GetMapping("/get-me")
    public HttpEntity<ApiResponse> getMe(@CurrentUser User user) {
        return profileService.getMe(user);
    }

    @PostMapping("/change-password")
    public HttpEntity<ApiResponse> changePassword(@CurrentUser User user, @RequestBody ChangePasswordDto dto) {
        return profileService.changePassword(user, dto);
    }

    @PostMapping("/set-fcm-token")
    public HttpEntity<ApiResponse> setFcmToken(@CurrentUser User user, @RequestParam String fcm_token) {
        return profileService.setFirebaseDeviceToken(user, fcm_token);
    }

    //test
    @PostMapping("/send-notification")
    public HttpEntity<ApiResponse> sendNoti(@RequestBody Notification notification) {
        try {
            fcm.sendNotification(notification.getTitle(), notification.getBody(),
                    "dwrA9euSQiSl3Pu6lp-9pF:APA91bGdh_VEQ4PRmVQOe-AiSs60c8T-cxLFwzmz3k3M_IztfLyjkr69lkbIHlFk1XT7sgqZdv4C6LXpXUvjZ_xOIckCaT8I2fb-Uhcm7hRm4dZiWGhzWs8BaRztkCjt5gR8ryyWpjOy");
            return SUCCESS;
        } catch (FirebaseMessagingException | IllegalArgumentException e) {
            return ResponseEntity.status(406).body(new ApiResponse(e.getMessage(), 406, false, null));
        }

    }
}
