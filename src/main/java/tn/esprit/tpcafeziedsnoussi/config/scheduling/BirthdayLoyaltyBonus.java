package tn.esprit.tpcafeziedsnoussi.config.scheduling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BirthdayLoyaltyBonus {

    private final ClientRepository clientRepository;
    private final SchedulingProperties schedulingProperties;

    @Scheduled(cron = "${scheduling.birthday-cron}")
    @Transactional
    public void applyBirthdayLoyaltyBonus() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        List<Client> birthdayClients = clientRepository.findByBirthMonthAndDayWithCardAndAddress(month, day);
        if (birthdayClients == null || birthdayClients.isEmpty()) {
            log.info("[BirthdayBonus] No clients have birthday today ({}/{})", day, month);
            return;
        }

        List<Client> toSave = new ArrayList<>();
        for (Client client : birthdayClients) {
            if (client.getCarteFidelite() == null) {
                log.info("[BirthdayBonus] Client id={} {} {} has no loyalty card, skipping.", client.getIdClient(), client.getNom(), client.getPrenom());
                continue;
            }

            int oldPoints = client.getCarteFidelite().getPointsAccumules();
            int increase = Math.round(oldPoints * 0.10f);

            if (oldPoints == 0) {
                increase = schedulingProperties.getBirthdayDefaultBonusWhenZero();
            } else if (increase == 0) {
                increase = schedulingProperties.getBirthdayMinIncreaseForNonzero();
            }

            int newPoints = oldPoints + increase;
            client.getCarteFidelite().setPointsAccumules(newPoints);
            toSave.add(client);

            log.info("[BirthdayBonus] Will apply +10% to client id={} {} {}: oldPoints={} increase={} newPoints={}",
                    client.getIdClient(), client.getNom(), client.getPrenom(), oldPoints, increase, newPoints);
        }

        if (!toSave.isEmpty()) {
            clientRepository.saveAll(toSave);
            log.info("[BirthdayBonus] Persisted {} updated clients.", toSave.size());
        } else {
            log.info("[BirthdayBonus] No clients were eligible for update.");
        }
    }

}
