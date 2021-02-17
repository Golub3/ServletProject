package com.golub.servlet.model.validator;

import java.util.regex.Pattern;

import static com.golub.servlet.model.validator.Regexes.EXPOSITION_REGEXP;
import static com.golub.servlet.model.validator.Regexes.PRICE_REGEXP;

public class ExpositionValidator {

    public static boolean validateTheme(String theme) {
        return theme != null && Pattern.matches(EXPOSITION_REGEXP, theme);
    }

    public static boolean validatePrice(String price) {
        return price != null && Pattern.matches(PRICE_REGEXP, price);
    }

}
