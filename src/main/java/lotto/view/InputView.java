package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.utils.Parser;

public class InputView {
    public int getCount() {
        System.out.println("구입 금액을 입력해 주세요.");
        String money = Console.readLine();
        return Parser.getCount(money);
    }

    public List<Integer> getNumbers() {
        System.out.println("당첨 번호를 입력해주세요.");
        String numbers = Console.readLine();
        return Parser.getNumbers(numbers);
    }

    public int getBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        String bonusNumber = Console.readLine();
        return Parser.getBonusNumber(bonusNumber);
    }
}
