package kitchenpos.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author : leesangbae
 * @project : kitchenpos
 * @since : 2021-01-13
 */
public class Money {

    public static final Money ZERO = new Money(BigDecimal.valueOf(0));

    public final BigDecimal amount;

    private Money(BigDecimal amount) {
        if (amount.longValue() < 0L) {
            throw new IllegalArgumentException("0보다 작은 숫자는 들어올 수 없습니다.");
        }
        this.amount = amount;
    }

    public static Money won(Long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public long getAmountLongValue() {
        return amount.longValue();
    }

    public boolean isLessThen(Money otherMoney) {
        return amount.compareTo(otherMoney.amount) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount.longValue(), money.amount.longValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

}
