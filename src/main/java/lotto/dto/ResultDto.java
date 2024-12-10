package lotto.dto;

import java.util.Map;
import lotto.domain.Result;

public record ResultDto(
        Map<Result, Integer> result,
        int money,
        double rate
) {
    public static ResultDto of(Map<Result, Integer> result, int money, double rate) {
        return new ResultDto(result, money, rate);
    }
}
