package tn.esprit.tpcafeziedsnoussi.config.scheduling;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scheduling")
public class SchedulingProperties {

    private long sampleTaskFixedRateMs = 2L;

    // Whether the sample task should log to the application log at INFO level
    private boolean sampleTaskLogEnabled = true;

    // Log once every N runs. If <= 1, log every run (caution: can be very noisy)
    private long sampleTaskLogEveryRuns = 500L;

    // Birthday job cron expression (default daily at midnight)
    private String birthdayCron = "0 0 0 * * *";

    // Default bonus to give when a client has 0 points
    private int birthdayDefaultBonusWhenZero = 10;

    // Minimum increase for non-zero points when 10% rounding yields 0
    private int birthdayMinIncreaseForNonzero = 1;

    public long getSampleTaskFixedRateMs() {
        return sampleTaskFixedRateMs;
    }

    public void setSampleTaskFixedRateMs(long sampleTaskFixedRateMs) {
        this.sampleTaskFixedRateMs = sampleTaskFixedRateMs;
    }

    public boolean isSampleTaskLogEnabled() {
        return sampleTaskLogEnabled;
    }

    public void setSampleTaskLogEnabled(boolean sampleTaskLogEnabled) {
        this.sampleTaskLogEnabled = sampleTaskLogEnabled;
    }

    public long getSampleTaskLogEveryRuns() {
        return sampleTaskLogEveryRuns;
    }

    public void setSampleTaskLogEveryRuns(long sampleTaskLogEveryRuns) {
        this.sampleTaskLogEveryRuns = sampleTaskLogEveryRuns;
    }

    public String getBirthdayCron() {
        return birthdayCron;
    }

    public void setBirthdayCron(String birthdayCron) {
        this.birthdayCron = birthdayCron;
    }

    public int getBirthdayDefaultBonusWhenZero() {
        return birthdayDefaultBonusWhenZero;
    }

    public void setBirthdayDefaultBonusWhenZero(int birthdayDefaultBonusWhenZero) {
        this.birthdayDefaultBonusWhenZero = birthdayDefaultBonusWhenZero;
    }

    public int getBirthdayMinIncreaseForNonzero() {
        return birthdayMinIncreaseForNonzero;
    }

    public void setBirthdayMinIncreaseForNonzero(int birthdayMinIncreaseForNonzero) {
        this.birthdayMinIncreaseForNonzero = birthdayMinIncreaseForNonzero;
    }
}
