package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoConfig {
    private static OutputView outputView;
    private static InputView inputView;
    private static LottoService lottoService;
    private static LottoController lottoController;

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public LottoService lottoService() {
        if (lottoService == null) {
            lottoService = new LottoService();
        }
        return lottoService;
    }

    public LottoController lottoController() {
        if (lottoController == null) {
            lottoController = new LottoController(outputView(), inputView(), lottoService());
        }
        return lottoController;
    }

}
