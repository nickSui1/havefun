package priv.nick.cbs.topgun.security.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import priv.nick.cbs.topgun.constant.RedisKeyConstants;
import priv.nick.cbs.topgun.constant.SecurityConstants;
import priv.nick.cbs.topgun.dao.ClientMapper;
import priv.nick.cbs.topgun.dao.UserMapper;
import priv.nick.cbs.topgun.integration.RedisService;
import priv.nick.cbs.topgun.security.dto.RefreshTokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenResponse;
import priv.nick.cbs.topgun.security.service.CustomUserDetails;
import priv.nick.cbs.topgun.security.service.CustomUserDetailsService;
import priv.nick.cbs.topgun.security.service.TokenService;
import priv.nick.cbs.topgun.util.FormatUtils;
import priv.nick.cbs.topgun.util.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final FormatUtils formatUtils;
    private final RedisService redisService;
    private final static List<String> token_black_list = new ArrayList<>();

    @Autowired
    public TokenServiceImpl(UserMapper userMapper,
                            ClientMapper clientMapper,
                            JwtTokenProvider jwtTokenProvider,
                            AuthenticationManager authenticationManager,
                            FormatUtils formatUtils,
                            CustomUserDetailsService customUserDetailsService,
                            RedisService redisService) {
        this.userMapper = userMapper;
        this.clientMapper = clientMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.formatUtils = formatUtils;
        this.customUserDetailsService = customUserDetailsService;
        this.redisService = redisService;
    }

    @Override
    public TokenResponse generateToken(TokenRequest tokenRequest) {
        String username = tokenRequest.getUsername();
        String password = tokenRequest.getPassword();

        if(username.isBlank()){
            throw new IllegalArgumentException("username is blank");
        }
        if(password.isBlank()){
            throw new IllegalArgumentException("password is blank");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
        Map<String,List<String>> authorities = customUserDetails.getRoleAndPermissions();

        List<String> rolesAndPermissions = formatUtils.convertMapToList(authorities);

        String access_token = jwtTokenProvider.generateToken(
                username, rolesAndPermissions, SecurityConstants.ACCESS_TOKEN);
        String refresh_token = jwtTokenProvider.generateToken(
                username, rolesAndPermissions, SecurityConstants.REFRESH_TOKEN);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(access_token);
        tokenResponse.setRefreshToken(refresh_token);
        tokenResponse.setUsername(username);
        tokenResponse.setRoles(rolesAndPermissions);

        return tokenResponse;
    }

    @Override
    public TokenResponse refreshToken(RefreshTokenRequest refreshToken) {
        if(!jwtTokenProvider.validateToken(refreshToken.getRefreshToken())){
            throw new IllegalArgumentException("Invalid refresh token");
        }
        TokenResponse tokenResponse = new TokenResponse();
        String username = jwtTokenProvider.getUsername(refreshToken.getRefreshToken());
        CustomUserDetails customUserDetails = (CustomUserDetails)customUserDetailsService
                .loadUserByUsername(username);
        Map<String,List<String>> authorizes = customUserDetails.getRoleAndPermissions();
        List<String> rolesAndPermissions = formatUtils.convertMapToList(authorizes);

        String access_token = jwtTokenProvider.generateToken(
                username,rolesAndPermissions,SecurityConstants.ACCESS_TOKEN);

        tokenResponse.setAccessToken(access_token);
        tokenResponse.setRefreshToken(refreshToken.getRefreshToken());
        tokenResponse.setUsername(username);
        tokenResponse.setRoles(rolesAndPermissions);

        return tokenResponse;
    }

    @Override
    public void invalidateToken(String token) {
        token_black_list.add(token);
        redisService.lPush(RedisKeyConstants.REDIS_BLACK_LIST_KEY, Collections.singletonList(token));
    }
}
