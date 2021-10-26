package per.yzb.jwt.enums;

import javax.servlet.http.HttpServletResponse;

public enum JWTResponseCode {
    SUCCESS_CODE(HttpServletResponse.SC_OK, "token数据正确，可以正常进行访问"),
    TOKEN_TIMEOUT_CODE(HttpServletResponse.SC_BAD_REQUEST, "token数据失效，无法进行正常访问"),
    TOKEN_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到相关的Token数据信息，请获取Token信息后进行访问");

    private int code;
    private String message;

    private JWTResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
