package extras;

import java.util.HashMap;
import java.util.regex.Pattern;

public class FieldValidation {
    public HashMap<String, Boolean> validate(UserDetails userDetails){
        String emailRegex = "^.+@.+\\..+$";
        Pattern pattern = Pattern.compile(emailRegex);
        HashMap<String, Boolean> validationMap= new HashMap<>();
        if (userDetails.getUsername() == null || !pattern.matcher(userDetails.getUsername()).matches()){
            validationMap.put("emailValidation",false);
        }
        else{
            validationMap.put("emailValidation",true);
        }
        if(userDetails.getPassword().length() < 5){
            validationMap.put("passwordValidation",false);
        }
        else{
            validationMap.put("passwordValidation",true);
        }
        if(userDetails.getSecretCode().length() < 3){
            validationMap.put("secretValidation",false);
        }
        else{
            validationMap.put("secretValidation",true);
        }
        return validationMap;
    }
}
