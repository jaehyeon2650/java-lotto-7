package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_COUNTS("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1에서 45사이의 숫자이여야합니다."),
    INVALID_PURCHASE_TYPE("구매 금액은 숫자로만 입력해주세요."),
    INVALID_PURCHASE_AMOUNT_UNIT("구매 금액은 1000원 단위만 가능합니다."),
    INVALID_PURCHASE_MIN("구매 금액은 1000원 이상이여야합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    public String getMessage() {
        return this.message;
    }

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }
}
