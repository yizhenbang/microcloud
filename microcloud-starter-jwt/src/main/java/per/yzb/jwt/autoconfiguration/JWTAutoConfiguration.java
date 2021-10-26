package per.yzb.jwt.autoconfiguration;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.yzb.jwt.config.JWTConfigProperties;
import per.yzb.jwt.config.JWTEncryptConfigProperties;
import per.yzb.jwt.service.PasswordEncryptService;
import per.yzb.jwt.service.TokenService;
import per.yzb.jwt.service.impl.PasswordEncryptServiceImpl;
import per.yzb.jwt.service.impl.TokenServiceImpl;

@Configuration
@EnableConfigurationProperties({JWTConfigProperties.class, JWTEncryptConfigProperties.class})
public class JWTAutoConfiguration {
    @Bean("tokenService")
    public TokenService getTokenService() {
        return new TokenServiceImpl();
    }

    @Bean("passwordEncrypt")
    public PasswordEncryptService getPasswordEncryptService() {
        return new PasswordEncryptServiceImpl();
    }
}
