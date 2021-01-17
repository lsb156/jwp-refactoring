package kitchenpos.repository;

import java.util.List;
import java.util.Optional;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderStatus;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(idClass = Long.class, domainClass = Order.class)
public interface OrderDao {
    Order save(Order entity);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    boolean existsByOrderTableIdAndOrderStatusIn(Long orderTableId, List<OrderStatus> orderStatuses);

    boolean existsByOrderTableIdInAndOrderStatusIn(List<Long> orderTableIds, List<OrderStatus> orderStatuses);
}
