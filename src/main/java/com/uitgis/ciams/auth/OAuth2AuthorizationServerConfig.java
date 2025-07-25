package com.uitgis.ciams.auth;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.uitgis.ciams.config.CustomInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Configuration
public class OAuth2AuthorizationServerConfig {


	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int REFRESH_TOKEN_VALIDITY_SECONDS = 12 * 60 * 60;

	@Value("${security.oauth2.authorization.jwt.key-store}")
	private String keyStorePath;

	@Value("${security.oauth2.authorization.jwt.key-alias}")
	private String keyStoreAlias;

	@Value("${security.oauth2.authorization.jwt.key-password}")
	private String keyStorePass;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Value("${security.oauth2.client.resource-ids}")
	private String resourceIds;

	private static final String[] PERMIT_ALL = {
			"/kras",
			"/rsa/key",
			"/error/**",
			"/cmm/signUp",
			"/api/v1/login/**",
			"/api/v1/config/**",
			"/api/v1/file/down/**",
			"/api/v1/plan/area/list",
	};

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsService userDetailService;
	private final CustomInterceptor customInterceptor;
	private final CustomAuthenticationFilter customFilter;


	@Bean
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.formLogin(form -> form // 람다 기반 설정
//				.loginPage("/login")
//				.permitAll());

		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

		Customizer <OAuth2AuthorizationServerConfigurer> authorizationServerConfigurerCustomizer = (authorizationServer) -> {

			AuthenticationConverter authenticationConverter = getOAuth2AuthenticationConverter();
			Customizer<OAuth2TokenEndpointConfigurer> tokenEndpointCustomizer = (tokenEndpoint) -> tokenEndpoint.accessTokenRequestConverter(authenticationConverter);

			authorizationServer.tokenEndpoint(tokenEndpointCustomizer)
					.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0

		};


//		http
//				.securityMatcher(endpointsMatcher)
//				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//						.requestMatchers(antMatcher("/")).permitAll()
//						.anyRequest().authenticated()
//				)
//				.formLogin(form -> form
//						.loginPage("/login")
//						.permitAll()
//				)
//				.headers(headers -> headers
//						.frameOptions(options -> options.sameOrigin())
//				);


		http
//				.securityMatcher(endpointsMatcher)
				.securityMatcher("/oauth2/**") // <-- 인증 서버에서 관리하는 경로 지정
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.anyRequest().authenticated()
				)
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers(PERMIT_ALL).permitAll() // 허용된 엔드포인트
//						.requestMatchers("/api/**").authenticated() // `/api/**` 인증 필요
//				)
				.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
//				.formLogin(form -> form
//						.loginPage("/login")
//						.permitAll()
//				)
//				.exceptionHandling(exceptionHandling -> exceptionHandling
//						.authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 커스텀 EntryPoint 설정
//				)
//				.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPointss))
				.formLogin(AbstractHttpConfigurer::disable)
				.userDetailsService(userDetailService)
				.addFilterBefore(customFilter, BasicAuthenticationFilter.class)
//				.addFilterAfter(customFilter, BearerTokenAuthenticationFilter.class)
//				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.with(authorizationServerConfigurer, authorizationServerConfigurerCustomizer)
						.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint()));


		Customizer <FormLoginConfigurer<HttpSecurity>> formLoginCustomizer = Customizer.withDefaults();
		SecurityFilterChain securityFilterChain = http.formLogin(formLoginCustomizer).build();

		/**
		 * Custom configuration for Resource Owner Password grant type. Current implementation has no support for Resource Owner
		 * Password grant type
		 */
		addCustomOAuth2ResourceOwnerPasswordAuthenticationProvider(http);

		return securityFilterChain;
	}
	private AuthenticationConverter getOAuth2AuthenticationConverter() {

		List<AuthenticationConverter> delegates = List.of(
				new OAuth2AuthorizationCodeAuthenticationConverter(),
				new OAuth2RefreshTokenAuthenticationConverter(),
				new OAuth2ClientCredentialsAuthenticationConverter(),
				new OAuth2ResourceOwnerPasswordAuthenticationConverter()
		);

		return new DelegatingAuthenticationConverter(delegates);

	}

	@Bean
	public AuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@SuppressWarnings("unchecked")
	private void addCustomOAuth2ResourceOwnerPasswordAuthenticationProvider(HttpSecurity http) {

		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
		OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider =
				new OAuth2ResourceOwnerPasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);

		// This will add new authentication provider in the list of existing authentication providers.
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);

	}
	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder()
//				.tokenEndpoint("/oauth/token") // 토큰 엔드포인트 사용자 지정
				.issuer("http://localhost:8080/ciams") // Change to your server's base URL
				.build();
	}


//	@Bean
//	public AuthorizationServerSettings authorizationServerSettings() {
//		return AuthorizationServerSettings.builder()
//				.tokenEndpoint("/ciams/oauth/token") // 토큰 엔드포인트 사용자 지정
//				.issuer("http://localhost:8080")
//				.build();
//	}

	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId(clientId)
				.clientSecret(passwordEncoder.encode(clientSecret))
				.authorizationGrantType(AuthorizationGrantType.PASSWORD)
//				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // 기존 PASSWORD 제거
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope("read")
				.scope("write")
				.tokenSettings(tokenSettings())
				.build();

		return new InMemoryRegisteredClientRepository(registeredClient);
	}

	@Bean
	public TokenSettings tokenSettings() {
		return TokenSettings.builder()
				.accessTokenTimeToLive(Duration.ofHours(1))
				.refreshTokenTimeToLive(Duration.ofHours(12))
				.build();
	}


	@Bean
	public JwtEncoder jwtEncoder() {
		KeyPair keyPair = getKeyPair();
		RSAKey jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
				.privateKey((RSAPrivateKey) keyPair.getPrivate())
				.build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new com.nimbusds.jose.jwk.JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey((RSAPublicKey) getKeyPair().getPublic()).build();
	}

//	private KeyPair keyPair() {
//		try {
//			var keyStoreFactory = new org.springframework.security.oauth2.jose.jws.KeyStoreFactory();
//			var keyStore = keyStoreFactory.load(new ClassPathResource(keystore), keyPassword.toCharArray());
//			return keyStore.getKeyPair(keyAlias);
//		} catch (Exception ex) {
//			throw new IllegalStateException("Failed to load key pair", ex);
//		}
//	}
//

	private KeyPair getKeyPair() {

		KeyPair keyPair = null;
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			try (InputStream is = new ClassPathResource(keyStorePath).getInputStream()) {
				keyStore.load(is, keyStorePass.toCharArray());

				Key key = keyStore.getKey(keyStoreAlias, keyStorePass.toCharArray());
				if (key instanceof PrivateKey) {
					Certificate cert = keyStore.getCertificate(keyStoreAlias);
					PublicKey publicKey = cert.getPublicKey();

					keyPair = new KeyPair(publicKey, (PrivateKey) key);
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return keyPair;
	}



	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
		return context -> {
			if (context.getTokenType().getValue().equals("access_token")) {
				Authentication principal = context.getPrincipal();

				if (principal instanceof UsernamePasswordAuthenticationToken authenticationToken) {
					Object userPrincipal = authenticationToken.getPrincipal();

					if (userPrincipal instanceof UserDetails userDetails) {
						context.getClaims().claim("user_name", userDetails.getUsername());
						context.getClaims().claim("authorities", userDetails.getAuthorities());
					}
				}
			}
		};
	}

}
