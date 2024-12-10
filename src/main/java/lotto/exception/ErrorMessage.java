package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1부터 45 사이의 숫자여야합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복이 되면 안됩니다."),
    INVALID_INTEGER_TYPE("숫자만 입력해주세요."),
    INVALID_MONEY_UNIT("금액은 1000원 단위로만 가능합니다(최소 1개)"),
    INVALID_LOTTO_INPUT("로또 번호를 ,를 기준으로 정확히 입력해주세요");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
