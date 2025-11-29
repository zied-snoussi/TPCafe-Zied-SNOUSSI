package tn.esprit.tpcafeziedsnoussi.services.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.config.scheduling.SchedulingProperties;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class SampleScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(SampleScheduledTask.class);
    private final SchedulingProperties props;

    // a simple counter to demonstrate work done by the task
    private final AtomicLong counter = new AtomicLong(0);

    @Scheduled(fixedRateString = "${scheduling.sample-task-fixed-rate-ms:2}")
    public void run() {
        long id = counter.incrementAndGet();
        Instant now = Instant.now();

        if (props.isSampleTaskLogEnabled()) {
            long every = Math.max(1, props.getSampleTaskLogEveryRuns());
            if (id % every == 0) {
                log.info("[SampleScheduledTask] runId={} ts={} rateMs={}", id, now, props.getSampleTaskFixedRateMs());
            } else if (log.isDebugEnabled()) {
                log.debug("[SampleScheduledTask] tick runId={}", id);
            }
        } else if (log.isDebugEnabled()) {
            log.debug("[SampleScheduledTask] tick runId={}", id);
        }

    }
}
