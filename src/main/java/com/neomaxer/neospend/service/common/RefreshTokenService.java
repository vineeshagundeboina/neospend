package com.neomaxer.neospend.service.common;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.exception.TokenRefreshException;
import com.neomaxer.neospend.models.auth.RefreshToken;
import com.neomaxer.neospend.repositories.auth.RefreshTokenRepository;
import com.neomaxer.neospend.repositories.auth.UserRepo;

@Service
public class RefreshTokenService {

	@Value("${app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepo userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(UUID userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		Date expDate = DateUtils.addMilliseconds(new Date(), refreshTokenDurationMs.intValue());
		refreshToken.setExpiryDate(expDate);
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(new Date()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(UUID userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}

}
