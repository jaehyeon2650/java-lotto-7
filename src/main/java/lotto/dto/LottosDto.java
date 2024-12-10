package lotto.dto;

import java.util.List;
import lotto.domain.Lottos;

public record LottosDto(
        List<LottoDto> lottos
) {
    public static LottosDto from(Lottos lottos) {
        List<LottoDto> list = lottos.getLottos().stream().map(lotto -> {
            return new LottoDto(lotto.getNumbers());
        }).toList();
        return new LottosDto(list);
    }

    public record LottoDto(
            List<Integer> numbers
    ) {
    }
}
