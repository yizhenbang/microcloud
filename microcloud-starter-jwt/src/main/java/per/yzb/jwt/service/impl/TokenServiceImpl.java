package per.yzb.jwt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import per.yzb.jwt.config.JWTConfigProperties;
import per.yzb.jwt.service.TokenService;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenServiceImpl implements TokenService {

    @Autowired
    private JWTConfigProperties jwtConfigProperties;
    @Autowired
    private ObjectMapper objectMapper;//在容器中默认会给出一个ObjectMapper，但是在测试环境中是不存在的
    @Value("${spring.application.name}")
    private String applicationName;//因为本starter模块会被其他项目引用，为了安全设置一个applicationName
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;//签名加密算法

    @Override
    public SecretKey generateKey() {
        byte[] decodeBase64 = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));
        SecretKey secretKey = new SecretKeySpec(decodeBase64, 0, decodeBase64.length, "HES");
        return secretKey;
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) throws JsonProcessingException {
        LocalDateTime nowDate = LocalDateTime.now();//当前时间
        LocalDateTime expireDate = nowDate.plusSeconds(this.jwtConfigProperties.getExpire());//当前时间+有效时间=失效时间

        Map<String, Object> claims = new HashMap();//Claims数据
        claims.put("site", "per.yzb.yiStudy");
        claims.put("time", convertDate(LocalDateTime.now()));

        Map<String, Object> header = new HashMap<>();
        header.put("author", "小易同学");
        header.put("desc", "测试JWT的实际使用把");

        JwtBuilder jwtBuilder = Jwts.builder();

        jwtBuilder.setClaims(claims)
                .setIssuedAt(convertDate(nowDate))//签发时间
                .setIssuer(this.jwtConfigProperties.getIssuer())//设置签发者
                .setExpiration(convertDate(expireDate))//设置失效时间
                .signWith(this.signatureAlgorithm, this.generateKey())//设置签名
                .setId(id)//设置ID
                .setHeader(header)//设置头信息
                .setSubject(objectMapper.writeValueAsString(subject));//设置附加信息

        return jwtBuilder.compact();//压缩返回
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if (verifyToken(token)) {//如果token正常则可以进行解析
            return Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            // 如果能够正常解析出数据，那就代表token没问题
            Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String refreshToken(String token) {
        if (verifyToken(token)) {
            Jws<Claims> claimsJws = this.parseToken(token);
            //刷新token就是根据原有的token更换下字符串，重新生成
            try {
                return this.createToken(claimsJws.getBody().getId(), objectMapper.readValue(claimsJws.getBody().getSubject(), Map.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Date convertDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
