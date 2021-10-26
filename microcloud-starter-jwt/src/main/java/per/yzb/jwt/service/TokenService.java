package per.yzb.jwt.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import java.util.Map;

public interface TokenService {
    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/26 23:10
     * @Description //TODO 获取token的key
     * @return javax.crypto.SecretKey 返回一个加密后的key
     * @Since version-1.0
    **/
    SecretKey generateKey();//获取加密key

    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/26 22:56
     * @Description //TODO 创建Token数据
     * @param id: token的唯一ID
     * @param subject: token的附加信息
     * @return java.lang.String 生成的token
     * @Since version-1.0
    **/
    String createToken(String id, Map<String, Object> subject) throws JsonProcessingException;

    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/26 23:07
     * @Description //TODO 解析Token
     * @param token: 传入Token字符串
     * @return io.jsonwebtoken.Jws<io.jsonwebtoken.Claims> 在解析的过程中可能会抛出异常
     * @Since version-1.0
    **/
    Jws<Claims> parseToken(String token) throws JwtException;

    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/26 23:09
     * @Description //TODO 验证Token是否正常
     * @param token: 传入token数据
     * @return boolean 是否有效
     * @Since version-1.0
    **/
    boolean verifyToken(String token) throws JsonProcessingException;

    /**
     * @Author ZhenBang-Yi
     * @Date 2021/10/26 23:10
     * @Description //TODO 根据已有的token信息进行刷新
     * @param token: 传入有效的token数据
     * @return java.lang.String 返回新的token字符串
     * @Since version-1.0
    **/
    String refreshToken(String token);
}
