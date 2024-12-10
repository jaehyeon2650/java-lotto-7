package lotto.domain;

import static lotto.constant.LottoConstant.MAX_NUMBER;
import static lotto.constant.LottoConstant.MIN_NUMBER;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        Validator.validate(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private static class Validator {
        public static void validate(int number) {
            if (number < MIN_NUMBER.getNumber() || number > MAX_NUMBER.getNumber()) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_RANGE);
            }
        }
    }
}
