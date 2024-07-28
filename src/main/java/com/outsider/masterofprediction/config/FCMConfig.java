package com.outsider.masterofprediction.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FCMConfig {

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/firebase/master-of-prediction-384d0-firebase-adminsdk-47mce-780f4ac1f3.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);

        return FirebaseMessaging.getInstance(firebaseApp);
    }
}