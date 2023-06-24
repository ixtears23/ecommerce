package junseok.snr.ecommerce.coupon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import junseok.snr.ecommerce.coupon.api.dto.IssueCouponRequest;
import junseok.snr.ecommerce.coupon.application.service.ApplyRedisService;
import junseok.snr.ecommerce.coupon.application.service.ApplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class CouponControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApplyRedisService applyRedisService;
    @MockBean
    private ApplyService applyService;

    @Test
    void testIssueCoupon() throws Exception {
        final IssueCouponRequest request = new IssueCouponRequest(1L);
        this.mockMvc.perform(
                post("/coupon/v1/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(
                        document(
                                "issue-coupon",
                                requestFields(
                                        fieldWithPath("id").description("User ID")
                                )
                        )
                );
    }

}
