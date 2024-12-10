package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        Validator.validate(numbers);
        this.numbers = numbers;
    }

    private static class Validator {
        public static void validate(List<Integer> numbers) {
            validateSize(numbers);
            validateRange(numbers);
            validateDuplicate(numbers);
        }

        private static void validateSize(List<Integer> numbers) {
            if (numbers.size() != 6) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_SIZE);
            }
        }

        private static void validateRange(List<Integer> numbers) {
            numbers.forEach(number -> {
                if (number < 1 || number > 45) {
                    throw LottoException.from(ErrorMessage.INVALID_LOTTO_RANGE);
                }
            });
        }

        private static void validateDuplicate(List<Integer> numbers) {
            Set<Integer> numberSet = new HashSet<>(numbers);
            if (numberSet.size() != numbers.size()) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_DUPLICATE);
            }
        }

    }

    // TODO: 추가 기능 구현
}
