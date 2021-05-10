package net.renfei.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.renfei.config.SystemConfig;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JWT的工具
 *
 * @author renfei
 */
@Component
public class JwtTokenUtil {
    private final SystemConfig systemConfig;

    public JwtTokenUtil(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public String createJwt(String userName) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // header Map
        Map<String, Object> map = new ConcurrentHashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        SecretKey secretKey = generalKey();
        Claims claims = Jwts.claims();
        // jti (JWT ID)，防止jwt被重新发送
        claims.setId(UUID.randomUUID().toString())
                // 主题
                .setSubject(userName)
                // 签发时间
                .setIssuedAt(now)
                // 签发者
                .setIssuer(systemConfig.getJwt().getIssuer());

        JwtBuilder builder = Jwts.builder().setHeader(map)
                // 使用 JSON 实例设置 payload
                .setClaims(claims)
                // 签名算法以及密钥
                .signWith(secretKey, algorithm);
        Date expDate = new Date(System.currentTimeMillis() + systemConfig.getJwt().getExpiration());
        // 过期时间
        builder.setExpiration(expDate);
        return builder.compact();

    }

    public String getUsername(String token) {
        Claims claims = parseJwt(token);
        return claims == null ? null : claims.getSubject();
    }

    public boolean validate(String token) {
        return this.parseJwt(token) != null;
    }

    public Claims parseJwt(String jwt) {
        Claims claims = null;
        try {
            SecretKey secretKey = generalKey();
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception ignored) {
        }
        return claims;
    }

    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(systemConfig.getJwt().getSecret());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }
}
