package kitchenpos.dto;

import kitchenpos.domain.model.MenuProduct;

public class MenuProductRequest {

    private Long productId;

    private long quantity;

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    protected MenuProductRequest() {
    }

    public MenuProductRequest(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public MenuProduct toEntity() {
        return new MenuProduct(productId, quantity);
    }

}
