package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoAnswerTest {
    @Test
    @DisplayName("로또와 보너스 숫자가 겹치면 예외가 발생한다.")
    void makeLottoAnswer_Fail() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(1);
        // when & then
        assertThatThrownBy(() -> new LottoAnswer(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("lottoResult - 정상적인 로또 결과를 반환한다.")
    void lottoResult() {
        // given
        LottoAnswer lottoAnswer = new LottoAnswer(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
        List<Lotto> lottoList = new ArrayList<>();
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        lottoList.add(lotto1);
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        lottoList.add(lotto2);
        Lottos lottos = new Lottos(lottoList);
        // when
        Map<Result, Integer> result = lottoAnswer.lottoResult(lottos);
        // then
        assertThat(result.getOrDefault(Result.FIRST, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(Result.SECOND, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(Result.THIRD, 0)).isEqualTo(0);
        assertThat(result.getOrDefault(Result.FOURTH, 0)).isEqualTo(0);
        assertThat(result.getOrDefault(Result.FIFTH, 0)).isEqualTo(0);
    }

}