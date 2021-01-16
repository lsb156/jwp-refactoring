package kitchenpos.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kitchenpos.dto.OrderTableChangeNumberOfGuestsRequest;
import kitchenpos.dto.OrderTableCreateRequest;
import kitchenpos.dto.OrderTableEmptyChangeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

/**
 * @author : leesangbae
 * @project : kitchenpos
 * @since : 2021-01-09
 */
class TableRestControllerTest extends BaseControllerTest {

    @DisplayName("테이블 생성 테스트")
    @Test
    void tableCreateTest() throws Exception {

        OrderTableCreateRequest orderTable = new OrderTableCreateRequest(1L, 4, true);

        mockMvc.perform(post("/api/tables")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(orderTable)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("테이블 조회 테스트")
    @Test
    void tableListTest() throws Exception  {
        mockMvc.perform(get("/api/tables"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("테이블 상태 변경 테스트")
    @Test
    void tableChangeEmptyTest() throws Exception  {

        OrderTableEmptyChangeRequest request = new OrderTableEmptyChangeRequest(false);

        mockMvc.perform(put("/api/tables/1/empty")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("empty")
                        .value(false));
    }

    @DisplayName("테이블 인원 변경 테스트")
    @Test
    void tableChangeNumberOfGuestTest() throws Exception  {
        OrderTableChangeNumberOfGuestsRequest request = new OrderTableChangeNumberOfGuestsRequest(8);

        mockMvc.perform(put("/api/tables/2/empty")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        mockMvc.perform(put("/api/tables/2/number-of-guests")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("numberOfGuests")
                        .value(8));
    }

}
