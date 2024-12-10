package lotto.controller;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
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
        Lottos buyLotto = retryInvalidInput(this::buyLotto);
        showLotto(buyLotto);
        Lotto lotto = retryInvalidInput(this::getAnswerLotto);
        ResultDto result = retryInvalidInput(this::getResult, buyLotto, lotto);
        printResult(result);
    }

    private Lottos buyLotto() {
        int count = inputView.getCount();
        return lottoService.buyLotto(count);
    }

    private void showLotto(Lottos lottos) {
        outputView.printBuyLottos(LottosDto.from(lottos));
    }

    private Lotto getAnswerLotto() {
        List<Integer> numbers = inputView.getNumbers();
        return lottoService.makeAnswerLotto(numbers);
    }

    private ResultDto getResult(Lottos butLottos, Lotto lotto) {
        int bonusNumber = inputView.getBonusNumber();
        return lottoService.getResult(butLottos, lotto, bonusNumber);
    }

    private void printResult(ResultDto result) {
        outputView.printResult(result);
    }

    private <T> T retryInvalidInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (LottoException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private <T, U, R> R retryInvalidInput(BiFunction<T, U, R> biFunction, T t, U u) {
        while (true) {
            try {
                return biFunction.apply(t, u);
            } catch (LottoException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
