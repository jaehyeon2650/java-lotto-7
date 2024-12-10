package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.dto.ResultDto;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void before() {
        lottoService = new LottoService();
    }

    @Test
    @DisplayName("buyLotto - 개수 만큼 로또를 구입한다.")
    void buyLotto() {
        // given
        int count = 10;
        // when
        Lottos lottos = lottoService.buyLotto(count);
        // then
        assertThat(lottos.count()).isEqualTo(count);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("makeAnswerLotto - 중복되는 숫자가 있으면 예외가 발생한다.")
    void makeAnswerLottoDuplicate_Fail(List<Integer> numbers) {
        assertThatThrownBy(() -> lottoService.makeAnswerLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_DUPLICATE.getMessage());
    }

    private static Stream<Arguments> makeAnswerLottoDuplicate_Fail() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 2, 3, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 3, 4, 3)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("makeAnswerLotto - 범위를 벗어난 숫자가 있으면 예외가 발생한다.")
    void makeAnswerLottoRange_Fail(List<Integer> numbers) {
        assertThatThrownBy(() -> lottoService.makeAnswerLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
    }

    private static Stream<Arguments> makeAnswerLottoRange_Fail() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 0)),
                Arguments.of(List.of(1, 2, 3, 4, 5, -1))
        );
    }

    @Test
    @DisplayName("makeAnswerLotto - 정상적인 입력에서는 Lotto를 생성한다.")
    void makeAnswerLotto_Success() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        // when
        Lotto lotto = lottoService.makeAnswerLotto(numbers);
        // then
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("getResult - 정상적으로 결과를 반환한다.")
    void getResult() {
        // given
        List<Lotto> lottoList = new ArrayList<>();
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        lottoList.add(lotto1);
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        lottoList.add(lotto2);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 6, 9, 8));
        int bonusNumber = 7;
        Lottos buyLotto = new Lottos(lottoList);
        // when
        ResultDto result = lottoService.getResult(buyLotto, lotto, bonusNumber);
        Map<Result, Integer> totalResult = result.result();
        // then
        assertThat(totalResult.getOrDefault(Result.FIRST, 0)).isEqualTo(0);
        assertThat(totalResult.getOrDefault(Result.SECOND, 0)).isEqualTo(0);
        assertThat(totalResult.getOrDefault(Result.THIRD, 0)).isEqualTo(0);
        assertThat(totalResult.getOrDefault(Result.FOURTH, 0)).isEqualTo(1);
        assertThat(totalResult.getOrDefault(Result.FIFTH, 0)).isEqualTo(1);
        assertThat(result.money()).isEqualTo(55000);
        assertThat(result.rate()).isEqualTo((double) 55000 * 100 / 2000);
    }
}