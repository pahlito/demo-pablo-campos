package com.ey.evaluation.demo.util;

import com.ey.evaluation.demo.exception.NotValidPasswordException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@Slf4j
public final class PasswordUtils {


    public static void validatePassword(String password) {
        final Pattern lowerCaseCheck = Pattern.compile(".*[a-z].*");
        final Pattern upperCaseCheck = Pattern.compile(".*[A-Z].*");
        final Pattern hasTwoDigitsCheck = Pattern.compile(("(.*\\d.*){2,}"));

        boolean correctPassword = lowerCaseCheck.matcher(password).matches() &&
                upperCaseCheck.matcher(password).matches() &&
                hasTwoDigitsCheck.matcher(password).matches();

        if (!correctPassword) {
            throw new NotValidPasswordException();
        }

    }

    public static String getHash(String password) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            final byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private PasswordUtils() {
        //
    }
}
