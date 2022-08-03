package per.yzb.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import per.yzb.jwt.JWTStarter;
import per.yzb.jwt.config.JWTConfigProperties;
import per.yzb.jwt.config.JWTEncryptConfigProperties;
import per.yzb.jwt.service.PasswordEncryptService;
import per.yzb.jwt.service.TokenService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = JWTStarter.class)
public class JWTPasswordTest {

    @Autowired
    private PasswordEncryptService passwordEncryptService;

    @Autowired
    private JWTEncryptConfigProperties jwtEncryptConfigProperties;

    @Test
    public void refreshToken() throws JsonProcessingException {
        // System.out.println(jwtEncryptConfigProperties);
        System.out.println(passwordEncryptService);
    }
}
