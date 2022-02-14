package extras;

import javafx.scene.control.CustomMenuItem;

import java.util.Base64;

public class CustomEncrypt {
    public String encrypt(String text){
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(text.getBytes());
    }
    public String decrypt(String text){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(text));
    }
}
