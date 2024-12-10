package lotto.utils;

import static lotto.constant.LottoConstant.LOTTO_SIZE;
import static lotto.constant.LottoConstant.MAX_NUMBER;
import static lotto.constant.LottoConstant.MIN_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Random {
    public static List<Integer> getRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER.getNumber(), MAX_NUMBER.getNumber(), LOTTO_SIZE.getNumber());
    }
}
