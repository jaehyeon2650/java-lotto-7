package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private static class Validator {
        public static void validate(int number) {
            if (number < 1 || number > 45) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_RANGE);
            }
        }
    }
}
