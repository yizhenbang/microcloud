package per.yzb.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yzb.security.jwt")
public class JWTConfigProperties {
    private String sign;//证书签名
    private String issuer;//证书签发者
    private String secret;//加密秘钥
    private long expire;//有效时间
}
