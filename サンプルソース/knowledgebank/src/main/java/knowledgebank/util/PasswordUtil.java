package knowledgebank.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * パスワードユーティリティクラス
 */
public class PasswordUtil {
    /**
     * パスワードをハッシュ化（SHA-256）して返す。
     * @param value パスワード
     * @return ハッシュ化されたパスワード
     */
    public static String hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
