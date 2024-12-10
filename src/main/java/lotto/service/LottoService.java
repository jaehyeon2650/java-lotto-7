package lotto.service;

import static lotto.constant.LottoConstant.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoAnswer;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.dto.ResultDto;
import lotto.utils.Random;

public class LottoService {

    public Lottos buyLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(Random.getRandomNumbers()));
        }
        return new Lottos(lottos);
    }

    public Lotto makeAnswerLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public ResultDto getResult(Lottos butLottos, Lotto lotto, int bonusNumber) {
        LottoAnswer lottoAnswer = new LottoAnswer(lotto, new BonusNumber(bonusNumber));
        Map<Result, Integer> result = lottoAnswer.lottoResult(butLottos);
        int money = calculateMoney(result);
        int purchase = LOTTO_PRICE.getNumber() * butLottos.count();
        double rate = (double) money * 100 / purchase;
        return ResultDto.of(result, money, rate);
    }

    private int calculateMoney(Map<Result, Integer> results) {
        int money = 0;
        for (Result result : results.keySet()) {
            Integer count = results.getOrDefault(result, 0);
            money += result.getMoney() * count;
        }
        return money;
    }
}
