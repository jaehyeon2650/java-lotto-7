package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void lottoSize_Fail(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
    }

    private static Stream<Arguments> lottoSize_Fail() {
        return Stream.of(
                Arguments.of(List.of(1)),
                Arguments.of(List.of(1, 2)),
                Arguments.of(List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("중복되는 숫자가 있으면 예외가 발생한다.")
    void lottoDuplicate_Fail(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_DUPLICATE.getMessage());
    }

    private static Stream<Arguments> lottoDuplicate_Fail() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 2, 3, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 3, 4, 3)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("범위를 벗어난 숫자가 있으면 예외가 발생한다.")
    void lottoRange_Fail(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
    }

    private static Stream<Arguments> lottoRange_Fail() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 0)),
                Arguments.of(List.of(1, 2, 3, 4, 5, -1))
        );
    }

    @Test
    @DisplayName("hasNumber - 숫자를 보유하고 있으면 true를 반환한다.")
    void hasNumber_True() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        // when
        boolean hasNumber = lotto.hasNumber(1);
        // then
        assertThat(hasNumber).isTrue();
    }

    @Test
    @DisplayName("hasNumber - 숫자를 보유하고 있지 않으면 false를 반환한다.")
    void hasNumber_False() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        // when
        boolean hasNumber = lotto.hasNumber(7);
        // then
        assertThat(hasNumber).isFalse();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("getMatchedCount - 맞춘 개수만큼 리턴한다.")
    void getMatchedCount(List<Integer> numbers, int expected) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto butLotto = new Lotto(numbers);
        // when
        int count = lotto.getMatchedCount(butLotto);
        // then
        assertThat(count).isEqualTo(expected);
    }

    private static Stream<Arguments> getMatchedCount() {
        return Stream.of(
                Arguments.of(List.of(1, 7, 8, 9, 10, 11), 1),
                Arguments.of(List.of(12, 7, 8, 9, 10, 11), 0),
                Arguments.of(List.of(1, 7, 8, 2, 10, 11), 2),
                Arguments.of(List.of(1, 7, 8, 2, 3, 11), 3),
                Arguments.of(List.of(1, 7, 8, 2, 3, 4), 4),
                Arguments.of(List.of(1, 2, 8, 3, 4, 5), 5),
                Arguments.of(List.of(1, 2, 6, 3, 4, 5), 6)
        );
    }
}
