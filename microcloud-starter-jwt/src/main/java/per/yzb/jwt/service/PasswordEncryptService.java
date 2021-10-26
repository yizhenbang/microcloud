package per.yzb.jwt.service;

public interface PasswordEncryptService {
    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/27 0:04
     * @Description //TODO 对密码进行加密处理
     * @param password: 传入需要加密
     * @return java.lang.String 返回加密后的密码
     * @Since version-1.0
    **/
    String encryptPassword(String password);
}
