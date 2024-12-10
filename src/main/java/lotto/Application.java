package lotto;

import lotto.config.LottoConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoConfig lottoConfig = new LottoConfig();
        LottoController controller = lottoConfig.lottoController();
        controller.start();
    }
}
