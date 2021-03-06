package kitchenpos.domain.model;

import java.util.Objects;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import kitchenpos.domain.infrastructure.jpa.MoneyConverter;

/**
 * @author : leesangbae
 * @project : kitchenpos
 * @since : 2021-01-15
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    protected Product() {
    }

    public Product(String name, Money price) {
        if (Objects.isNull(price) || price.isLessThen(Money.ZERO)) {
            throw new IllegalArgumentException("`상품`의 가격은 0원 이상이여야 합니다..");
        }
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Money calculate(long quantity) {
        return this.price.times(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
