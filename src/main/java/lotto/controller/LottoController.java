package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;
import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final OutputView outputView;
    private final InputView inputView;
    private final LottoService lottoService;

    public LottoController(OutputView outputView, InputView inputView, LottoService lottoService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.lottoService = lottoService;
    }

    public void start() {
        Lottos buyLotto = buyLotto();
        showLotto(buyLotto);
        Lotto lotto = getAnswerLotto();
        ResultDto result = getResult(buyLotto, lotto);
        printResult(result);
    }

    private Lottos buyLotto() {
        while (true) {
            try {
                int count = inputView.getCount();
                return lottoService.buyLotto(count);
            } catch (LottoException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void showLotto(Lottos lottos) {
        outputView.printBuyLottos(LottosDto.from(lottos));
    }

    private Lotto getAnswerLotto() {
        while (true) {
            try {
                List<Integer> numbers = inputView.getNumbers();
                return lottoService.makeAnswerLotto(numbers);
            } catch (LottoException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private ResultDto getResult(Lottos butLottos, Lotto lotto) {
        while (true) {
            try {
                int bonusNumber = inputView.getBonusNumber();
                return lottoService.getResult(butLottos, lotto, bonusNumber);
            } catch (LottoException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void printResult(ResultDto result) {
        outputView.printResult(result);
    }
}
