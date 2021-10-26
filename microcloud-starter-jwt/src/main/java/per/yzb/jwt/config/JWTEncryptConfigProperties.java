package per.yzb.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yzb.security.password.encrypt")
public class JWTEncryptConfigProperties {
    private int repeat;//重复加密次数
    private String salt;//盐值
}
