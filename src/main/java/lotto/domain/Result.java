package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    FIRST(6, false, 2000000000, (matchedCount, bonus) -> matchedCount == 6),
    SECOND(5, true, 30000000, (matchedCount, bonus) -> matchedCount == 5 && bonus),
    THIRD(5, false, 1500000, (matchedCount, bonus) -> matchedCount == 5 && !bonus),
    FOURTH(4, false, 50000, (matchedCount, bonus) -> matchedCount == 4),
    FIFTH(3, false, 5000, (matchedCount, bonus) -> matchedCount == 3),
    NONE(0, false, 0, (matchedCount, bonus) -> matchedCount < 3);

    private final int matchedCount;
    private final boolean bonus;
    private final int money;
    private final BiPredicate<Integer, Boolean> check;

    Result(int matchedCount, boolean bonus, int money, BiPredicate<Integer, Boolean> check) {
        this.matchedCount = matchedCount;
        this.bonus = bonus;
        this.money = money;
        this.check = check;
    }

    public static Result findResult(int matchedCount, boolean bonus) {
        return Arrays.stream(Result.values()).filter(result ->
                result.check.test(matchedCount, bonus)
        ).findAny().orElse(NONE);
    }

    public int getMoney() {
        return money;
    }

    public int getMatchedCount() {
        return matchedCount;
    }
}
