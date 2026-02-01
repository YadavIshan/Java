package Questions;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;

public class GenerateOTP {
    public static void main(String[] args) {
        System.out.println("Random OTP: " + generateOTP());
        System.out.println("Secure OTP: " + generateSecureOTP());
    }

    public static String generateOTP() {
        return new Random().ints(6,0,10).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    public static String generateSecureOTP() {
        return new SecureRandom().ints(6,0,10).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
