package musinsa.product.adaptor.in;

import com.waug.auth.common.filter.JwtRequestFilter;
import com.waug.ota.booking.controller.docs.RailEuropeBookingDetailDocuments;
import musinsa.config.RestDocsConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@ImportAutoConfiguration(RestDocsConfiguration.class)
@WebMvcTest(controllers = BookingDTOController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Converter.class, JwtRequestFilter.class})})
class AdminProductController {

    @Autowired
    protected MockMvc mockMvc;


    @Test
    @DisplayName("레일유럽 구간권 Booking 안에 booking detail")
    void getRailEuropePTPBookingDetail() throws Exception {

        this.mockMvc.perform(get("/booking/detail/rail-europe/ptp").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(RailEuropeBookingDetailDocuments.getRailEuropePTPBookingDetail());
    }


}