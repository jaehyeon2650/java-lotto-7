package lotto.utils;

import static lotto.constant.LottoConstant.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class Parser {
    private static final String DELIMITER = ",";

    public static int getCount(String input) {
        Validator.validateIntegerType(input);
        int money = Integer.parseInt(input);
        Validator.validateMoneyUnit(money);
        return money / LOTTO_PRICE.getNumber();
    }

    public static List<Integer> getNumbers(String input) {
        Validator.validateNumbers(input);
        return Arrays.stream(input.split(DELIMITER)).map(word -> {
            word = word.trim();
            return Integer.parseInt(word);
        }).toList();
    }

    public static int getBonusNumber(String input) {
        Validator.validateIntegerType(input);
        return Integer.parseInt(input);
    }

    private static class Validator {
        private static final String EXPRESSION = "\\s*\\d+\\s*([,]\\s*\\d+\\s*)*";

        public static void validateIntegerType(String money) {
            if (!money.matches("\\d+")) {
                throw LottoException.from(ErrorMessage.INVALID_INTEGER_TYPE);
            }
        }

        public static void validateMoneyUnit(int money) {
            if (money < 0 || money % LOTTO_PRICE.getNumber() != 0 || money / LOTTO_PRICE.getNumber() == 0) {
                throw LottoException.from(ErrorMessage.INVALID_MONEY_UNIT);
            }
        }

        public static void validateNumbers(String input) {
            boolean matches = Pattern.matches(EXPRESSION, input);
            if (!matches) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_INPUT);
            }
        }

    }
}
