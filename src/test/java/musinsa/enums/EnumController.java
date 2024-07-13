package musinsa.enums;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import musinsa.config.EnumType;
import musinsa.product.domain.enums.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/category")
    public EnumResponseDto<EnumDocs> getCategoryEnum() {

        Map<String, String> category =
                Arrays.stream(Category.values()).collect(Collectors.toMap(EnumType::getName, EnumType::getDescription));


        return EnumResponseDto.of(EnumDocs.builder().category(category).build());
    }
}
