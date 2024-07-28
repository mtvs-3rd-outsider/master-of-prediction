package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class SseEmitters {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

    public void sendActivity(ActivityUserSubjectDTO activityData){
        log.info("sendActivity called");
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("activity")
                        .data(activityData));
            } catch (Exception e) {
                emitter.complete();
                // log.error("error occurred", e);
            }
        });
    }

}