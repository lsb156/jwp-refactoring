package kitchenpos.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import kitchenpos.dto.OrderTableDto;
import kitchenpos.dto.TableGroupDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

/**
 * @author : leesangbae
 * @project : kitchenpos
 * @since : 2021-01-09
 */
class TableGroupRestControllerTest extends BaseControllerTest {

    @DisplayName("테이블 그룹 생성 테스트")
    @Test
    void tableGroupCreateTest() throws Exception {
        OrderTableDto table01 = new OrderTableDto();
        table01.setId(3L);
        OrderTableDto table02 = new OrderTableDto();
        table02.setId(4L);

        TableGroupDto group = new TableGroupDto();
        group.setCreatedDate(LocalDateTime.now());
        group.setOrderTables(Arrays.asList(table01, table02));

        mockMvc.perform(post("/api/table-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(group)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }

    @DisplayName("테이블 그룹 해제 테스트")
    @Test
    void tableGroupUngroupTest() throws Exception {
        OrderTableDto table01 = new OrderTableDto();
        table01.setId(5L);
        OrderTableDto table02 = new OrderTableDto();
        table02.setId(6L);

        TableGroupDto group = new TableGroupDto();
        group.setCreatedDate(LocalDateTime.now());
        group.setOrderTables(Arrays.asList(table01, table02));

        String location = mockMvc.perform(post("/api/table-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(group)))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        mockMvc.perform(delete(location))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
