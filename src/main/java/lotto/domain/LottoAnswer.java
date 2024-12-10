package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class LottoAnswer {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public LottoAnswer(Lotto lotto, BonusNumber bonusNumber) {
        Validator.validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Map<Result, Integer> lottoResult(Lottos lottos) {
        Map<Result, Integer> results = new EnumMap<>(Result.class);
        List<Lotto> buyLottos = lottos.getLottos();
        for (Lotto buyLotto : buyLottos) {
            int matchedCount = lotto.getMatchedCount(buyLotto);
            boolean bonus = buyLotto.hasNumber(bonusNumber.getNumber());
            Result result = Result.findResult(matchedCount, bonus);
            results.put(result, results.getOrDefault(result, 0) + 1);
        }
        return results;
    }

    private static class Validator {
        public static void validateBonusNumber(Lotto lotto, BonusNumber bonusNumber) {
            boolean hasNumber = lotto.hasNumber(bonusNumber.getNumber());
            if (hasNumber) {
                throw LottoException.from(ErrorMessage.INVALID_LOTTO_DUPLICATE);
            }
        }
    }
}
