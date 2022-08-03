package per.yzb.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import per.yzb.jwt.JWTStarter;
import per.yzb.jwt.config.JWTConfigProperties;
import per.yzb.jwt.service.TokenService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = JWTStarter.class)
public class JWTServiceTest {

    @Autowired
    private TokenService tokenService;

    private String token = "eyJhdXRob3IiOiLlsI_mmJPlkIzlraYiLCJhbGciOiJIUzI1NiIsImRlc2MiOiLmtYvor5VKV1TnmoTlrp7pmYXkvb_nlKjmiooifQ.eyJzdWIiOiJ7XCLpmYTliqDkv6Hmga9cIjpcIuaIkeaYr-mZhOWKoOS_oeaBr1wifSIsInNpdGUiOiJwZXIueXpiLnlpU3R1ZHkiLCJpc3MiOiJaaGVuQmFuZ1lpIiwidGltZSI6MTYzNTI2NjU2MjExNSwiZXhwIjoxNjM1MjY3NTYyLCJpYXQiOjE2MzUyNjY1NjIsImp0aSI6IjcyMzZhMDM5LTFlMjAtNGUxZC04YjYzLTJkOTY3NWI1NTM1NiJ9.1yxmghrAWAJzUJTVZvMNMt5lITqHcrfeJZslLqEA7vg";

    @Autowired
    private JWTConfigProperties jwtConfigProperties;

    @Test
    public void createToken() throws JsonProcessingException {
        //String id = UUID.randomUUID().toString();
        //Map<String, Object> subject = new HashMap<>();
        //subject.put("附加信息","我是附加信息");
        //System.out.println(tokenService.createToken(id, subject));
        System.out.println(tokenService);
    }

    @Test
    public void parseToken() throws JsonProcessingException {
        System.out.println(tokenService.parseToken(token));
    }

    @Test
    public void refreshToken() throws JsonProcessingException {
        System.out.println(tokenService.refreshToken(token));
    }
}
