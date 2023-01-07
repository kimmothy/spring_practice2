package hello.servlet.web.jwt;


//import com.maproom.api.domain.Room;
import hello.servlet.roomapi.Room;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTManager {
    private static String SECRET_KEY = "chansecret";
    // JWT expiration time
    private static long tokenValidMiliseconds = 100L * 60 * 60;

    public String createToken(String key) {
        Claims claims = Jwts.claims().setId(key);
        claims.put("test", "hello world");
        Date now = new Date();
//        return null;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMiliseconds))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRoomToken(Room room) {
//        Claims claims = Jwts.claims().setId(roomKey);
        Claims claims = Jwts.claims();
        claims.put("roomKey", room.getRoomKey());
        claims.put("longitude", room.getLongitude());
        claims.put("latitude", room.getLatitude());
        claims.put("roomName", room.getRoomName());
        claims.put("endTime", room.getEndTime());
        Date now = new Date();
//        return null;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMiliseconds))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt);
        } catch(SignatureException e) {
            return null;
        }
    }

    public boolean validToken(Jws<Claims> claims){
        return !claims.getBody()
                .getExpiration()
                .before(new Date());
    }

    public String getKey(Jws<Claims> claims){
        return claims.getBody().getId();
    }

    public String getClaims(Jws<Claims> claims, String key){
        return (String) claims.getBody().get(key);
    }

    public static void main(String[] args) {
        JWTManager jwtManager = new JWTManager();
        String token = jwtManager.createToken("tokenkey");
        System.out.println("token = " + token);

        Jws<Claims> claims = jwtManager.getClaims(token);
        if (claims != null && jwtManager.validToken(claims)){
            String id = jwtManager.getKey(claims);
            String test = jwtManager.getClaims(claims, "test");

            System.out.println("id = " + id);
            System.out.println("test = " + test);
        } else {
            System.out.println("erorr");
        }

    }

}
