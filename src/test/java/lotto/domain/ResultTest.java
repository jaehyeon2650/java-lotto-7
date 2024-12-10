package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("findResult - 맞춘 개수와 보너스 결과에 맞는 로또 결과를 반환한다.")
    void findResult(int correctCount, boolean bonus, Result expected) {
        // when
        Result result = Result.findResult(correctCount, bonus);
        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> findResult() {
        return Stream.of(
                Arguments.of(0, true, Result.NONE),
                Arguments.of(0, false, Result.NONE),
                Arguments.of(1, true, Result.NONE),
                Arguments.of(1, false, Result.NONE),
                Arguments.of(2, true, Result.NONE),
                Arguments.of(2, false, Result.NONE),
                Arguments.of(3, true, Result.FIFTH),
                Arguments.of(3, false, Result.FIFTH),
                Arguments.of(4, true, Result.FOURTH),
                Arguments.of(4, false, Result.FOURTH),
                Arguments.of(5, true, Result.SECOND),
                Arguments.of(5, false, Result.THIRD),
                Arguments.of(6, true, Result.FIRST),
                Arguments.of(6, false, Result.FIRST)
        );
    }
}