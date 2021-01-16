package kitchenpos.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Money;

public class MenuDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductDto> menuProducts;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductDto> getMenuProducts() {
        return menuProducts;
    }

    public MenuDto() {
    }

    public MenuDto(Long id, String name, BigDecimal price, Long menuGroupId,
            List<MenuProductDto> menuProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public Menu toEntity() {
        List<MenuProduct> menuProductDtoList = menuProducts.stream()
                .map(MenuProductDto::toEntity)
                .collect(Collectors.toList());
        return new Menu(id, name, Money.won(price.longValue()), menuGroupId, menuProductDtoList);
    }

    public static MenuDto of(Menu menu) {
        List<MenuProductDto> menuProducts = menu.getMenuProduct().stream()
                .map(it -> MenuProductDto.of(it, menu.getId()))
                .collect(Collectors.toList());
        BigDecimal price = BigDecimal.valueOf(menu.getPrice().amount);
        return new MenuDto(menu.getId(), menu.getName(), price, menu.getMenuGroupId(), menuProducts);
    }
}
