package com.uitgis.ciams.config;

import com.uitgis.ciams.util.CipherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class UitPasswordEncoder extends BCryptPasswordEncoder {

    private final String INIT_SKIP_PASSWORD = "userNotFoundPassword";

    @Override
    public String encode(CharSequence rawPassword) {
        String passwordString = rawPassword.toString();

        if (passwordString.equals(INIT_SKIP_PASSWORD)) {
            return null;
        }

        try {
            rawPassword = CipherUtil.decryptRSA(passwordString);
            return super.encode(rawPassword);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            rawPassword = CipherUtil.decryptRSA(rawPassword.toString());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            log.error(e.getMessage());
            return false;
        }
        return super.matches(rawPassword, encodedPassword);
    }
}
