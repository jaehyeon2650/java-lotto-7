package lotto.constant;

public enum LottoConstant {
    MAX_NUMBER(45),
    MIN_NUMBER(1),
    LOTTO_SIZE(6),
    LOTTO_PRICE(1000);
    private final int number;

    LottoConstant(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
