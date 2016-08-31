package restaurant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Random;

/**
 * Created by Yevhen on 20.05.2016.
 */
public class Util {
    private static final String INVALID_FILE_LENGTH_PATTERN =
            "Length of the file <%s> is <%d>, but there only <%d> bytes have been read!";
    private static final int DEFAULT_STRING_LENGTH = 32;
    private static final int DEFAULT_UPPER_BOUND = 1000;
    private static final int DECIMAL_PRECISION = 2;

    static private Random random = new Random();

    private static float round(float value, int decimalPrecision) {
        float decimalPower = (float) Math.pow(10, decimalPrecision);

        return Math.round(value * decimalPower) / decimalPower;
    }

    private static float getRandomFloat(int upperBound) {
        return round(random.nextFloat() * upperBound, DECIMAL_PRECISION);
    }

    public static float getRandomFloat() {
        return getRandomFloat(DEFAULT_UPPER_BOUND);
    }

    public static int getRandomInteger() {
        return random.nextInt(DEFAULT_UPPER_BOUND);
    }

    private static String getRandomString(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    public static String getRandomString() {
        return getRandomString(DEFAULT_STRING_LENGTH);
    }

    public static byte[] readResourceFileToByteArray(String fileName) {
        byte[] result = null;

        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);
        if (resource != null) {
            try {
                String fullFileName = URLDecoder.decode(resource.getFile(), "UTF-8");
                File file = new File(fullFileName);
                if (file.exists()) {
                    int fileLength = (int)file.length();
                    result = new byte[fileLength];

                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        int readLength = fileInputStream.read(result);
                        fileInputStream.close();
                        if (readLength != fileLength) {
                            throw new IOException(String.format(INVALID_FILE_LENGTH_PATTERN, fileName, fileLength, readLength));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
