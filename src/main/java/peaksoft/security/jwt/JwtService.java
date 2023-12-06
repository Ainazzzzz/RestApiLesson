package peaksoft.security.jwt;

import com.auth0.jwt.JWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtService {
    private final String SECRET_KEY = "secret101010101010110";


    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withClaim("username", userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(15).toInstant()))
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateToken(String token) {
        return JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getClaim("username")
                .asString();
    }





}
