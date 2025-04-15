package configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtException;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    private String secretKey;

    private static final long EXPIRATION_TIME = 3600000;

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch(JwtException | IllegalArgumentException e){
            throw new RuntimeException("Invalid Jwt Token",e);
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles",String.class).split(","))
                .map(SimpleGrantedAuthority :: new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(),"", authorities);
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Authentication authentication){

        Claims claims = (Claims) Jwts.claims().setSubject(authentication.getName());
        claims.put("roles",authentication.getAuthorities().stream()
                .map(authority ->authority.getAuthority())
                .collect(Collectors.joining(",")));
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) // Corrected here
                .compact();
    }


    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

}
