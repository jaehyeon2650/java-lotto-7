package lotto.view;

import static lotto.domain.Result.FIFTH;
import static lotto.domain.Result.FIRST;
import static lotto.domain.Result.FOURTH;
import static lotto.domain.Result.SECOND;
import static lotto.domain.Result.THIRD;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Result;
import lotto.dto.LottosDto;
import lotto.dto.LottosDto.LottoDto;
import lotto.dto.ResultDto;

public class OutputView {
    private static final String LOTTO_RESULT_WITHOUT_BONUS = "%d개 일치 (%,d원) - %d개\n";
    private static final String LOTTO_RESULT_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n";
    private static final String BENEFIT_RESULT = "총 수익률은 %.1f%%입니다.";
    private static final String DELIMITER = ", ";
    private static final String LINE = "---";
    private static final String OPEN = "[";
    private static final String CLOSED = "]";

    public void printError(String error) {
        System.out.println(error);
    }

    public void printBuyLottos(LottosDto lottosDto) {
        List<LottoDto> lottos = lottosDto.lottos();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(this::printLotto);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println("당첨 통계");
        System.out.println(LINE);
        Map<Result, Integer> result = resultDto.result();
        showLottoResult(result);
        System.out.printf(BENEFIT_RESULT, resultDto.rate());
    }

    private void showLottoResult(Map<Result, Integer> result) {
        printResult(FIFTH, result);
        printResult(FOURTH, result);
        printResult(THIRD, result);
        printResult(SECOND, result);
        printResult(FIRST, result);
    }

    private void printResult(Result result, Map<Result, Integer> results) {
        if (result.isBonus()) {
            System.out.printf(LOTTO_RESULT_WITH_BONUS, result.getMatchedCount(), result.getMoney(),
                    results.getOrDefault(result, 0));
            return;
        }
        System.out.printf(LOTTO_RESULT_WITHOUT_BONUS, result.getMatchedCount(), result.getMoney(),
                results.getOrDefault(result, 0));
    }

    private void printLotto(LottoDto lottoDto) {
        System.out.print(OPEN);
        String lotto = lottoDto.numbers().stream().map(String::valueOf).collect(Collectors.joining(DELIMITER));
        System.out.print(lotto);
        System.out.println(CLOSED);
    }
}
