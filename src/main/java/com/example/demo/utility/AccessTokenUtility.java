package com.example.demo.utility;

import com.example.demo.model.AccessToken;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class AccessTokenUtility {

    /*
    //L'AccessToken sara cosi composto :
    //value : stringa composta da :
    12 caratteri iniziali composti da lettere
    1 separatore '.'
    28 caratteri equivalente all'username (email) in formato ash
    1 separatore '.' dopo l'username
    ultime 13 caratteri che equivalgono alla data e ora in istanti

     */
    private static final Character SEPARATOR = '.';

    //Metodo per creare l'access token senza l'utente settato
    public AccessToken getAccessToken(String email) {
        //Creiamo una stringa casuale contenente solo lettere lunga 12 caratteri
        String randomString = RandomStringUtils.random(12, true, false);

        //Creiamo lo string builder per collegare tutti i pezzi che comporranno il token
        StringBuilder sb = new StringBuilder(randomString);

        sb.append(SEPARATOR);

        //Facciamo un encode dell'username da appendere allo string builder
        String encodedUsername = encodeString(email);
        sb.append(encodedUsername);

        sb.append(SEPARATOR);

        //Otteniamo l'istante in cui verra creato il token e lo appendiamo allo string builder alla fine
        Instant instant = Instant.now();
        sb.append(instant.toEpochMilli());

        ZoneId zoneId = ZoneId.of("Europe/Rome"); // Zona oraria dell'Italia

        LocalDateTime tokenCreationDateTime = instant.atZone(zoneId).toLocalDateTime();
        return createAccessToken(sb.toString(), tokenCreationDateTime);
    }

    private String encodeString(String stringToDecrypt) {
        return new String(Base64.encodeBase64(stringToDecrypt.getBytes()));
    }

    private AccessToken createAccessToken(String value, LocalDateTime tokenCreationDateTime) {

        return AccessToken.builder()
                .value(value)
                .creationTokenDateTime(tokenCreationDateTime)
                .isActive(true)
                .build();
    }
}
