package kitchenpos.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kitchenpos.application.creator.ProductHelper;
import kitchenpos.dto.ProductCreateRequest;
import kitchenpos.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : leesangbae
 * @project : kitchenpos
 * @since : 2021-01-10
 */
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @DisplayName("상품 등록 테스트")
    @Test
    void productCreateTest() {
        ProductCreateRequest product = ProductHelper.createRequest("Test", 10_000);

        ProductDto savedProduct = productService.create(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Test");
        assertThat(savedProduct.getPrice().longValue()).isEqualTo(10_000L);
    }

    @DisplayName("상품 등록시 가격이 0원 미만인 경우")
    @Test
    void productCreateWithMinusPriceTest() {
        ProductCreateRequest product = ProductHelper.createRequest("Test", -1);

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);

    }

}
