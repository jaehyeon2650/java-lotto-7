package lotto.domain;

import java.util.Collections;
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

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int getMatchedCount(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        return (int) lottoNumbers.stream().filter(this::hasNumber).count();
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
}
