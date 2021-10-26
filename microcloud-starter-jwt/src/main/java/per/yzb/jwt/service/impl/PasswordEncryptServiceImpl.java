package per.yzb.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import per.yzb.jwt.config.JWTEncryptConfigProperties;
import per.yzb.jwt.service.PasswordEncryptService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * ClassName: PasswordEncryptServiceImpl
 * Description: 进行密码的加密处理
 * date: 2021/10/26 23:56
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public class PasswordEncryptServiceImpl implements PasswordEncryptService {

    @Autowired
    private JWTEncryptConfigProperties jwtEncryptConfigProperties;
    private static MessageDigest MD5_DIGEST;
    private final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();

    static {
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encryptPassword(String password) {
        String saltPassword = "【" + this.jwtEncryptConfigProperties.getSalt() + "】" + password;
        for (int i = 0; i < this.jwtEncryptConfigProperties.getRepeat(); i++) {
            saltPassword = BASE64_ENCODER.encodeToString(MD5_DIGEST.digest(password.getBytes()));
        }
        return saltPassword;
    }
}
