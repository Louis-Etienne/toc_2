package ca.ulaval.lemes3.infra.audio_sharing;

import ca.ulaval.lemes3.application.TokenGenerator;
import ca.ulaval.lemes3.application.dto.Token;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

public class JsonWebTokenGenerator implements TokenGenerator {

    private final static SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Override
    public Token generate(PartyId partyId, ListenerId listenerId) {
        String jwtString = Jwts.builder().claim("partyId", partyId.value()).claim("listenerId", listenerId).signWith(SECRET_KEY).compact();

        return new Token(jwtString);
    }
}
