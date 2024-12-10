package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Result;
import lotto.dto.LottosDto;
import lotto.dto.LottosDto.LottoDto;
import lotto.dto.ResultDto;

public class OutputView {
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
        System.out.println("---");
        Map<Result, Integer> result = resultDto.result();
        System.out.printf("%d개 일치 (%,d원) - %d개\n", Result.FIFTH.getMatchedCount(), Result.FIFTH.getMoney(),
                result.getOrDefault(Result.FIFTH, 0));
        System.out.printf(
                "%d개 일치 (%,d원) - %d개", Result.FOURTH.getMatchedCount(), Result.FOURTH.getMoney(),
                result.getOrDefault(Result.FOURTH, 0));
        System.out.printf("%d개 일치 (%,d원) - %d개\n", Result.THIRD.getMatchedCount(), Result.THIRD.getMoney(),
                result.getOrDefault(Result.THIRD, 0));
        System.out.printf(
                "%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n", Result.SECOND.getMatchedCount(),
                Result.SECOND.getMoney(),
                result.getOrDefault(Result.SECOND, 0));
        System.out.printf("%d개 일치 (%,d원) - %d개\n", Result.FIRST.getMatchedCount(), Result.FIRST.getMoney(),
                result.getOrDefault(Result.FIRST, 0));
        System.out.printf("총 수익률은 %.1f%%입니다.", resultDto.rate());
    }

    private void printLotto(LottoDto lottoDto) {
        System.out.print("[");
        String lotto = lottoDto.numbers().stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.print(lotto);
        System.out.println("]");
    }
}
