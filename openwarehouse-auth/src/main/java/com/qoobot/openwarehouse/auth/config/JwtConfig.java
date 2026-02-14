package com.qoobot.openwarehouse.auth.config;

import org.springframework.context.annotation.Configuration;

/**
 * JWT配置
 */
@Configuration
public class JwtConfig {

    /**
     * JWT密钥
     */
    private String secret = "openwarehouse-secret-key-for-jwt-token-generation-2024";

    /**
     * Token过期时间(毫秒) - 默认24小时
     */
    private Long expiration = 86400000L;

    /**
     * Token前缀
     */
    private String tokenPrefix = "Bearer ";

    /**
     * Token请求头名称
     */
    private String headerName = "Authorization";

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
