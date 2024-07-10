package musinsa;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.out.ProductRepositoryPort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class InitDataProcess implements CommandLineRunner {

    private final ProductRepositoryPort productRepositoryPort;


    @Override
    public void run(String... args) throws IOException {
        File jsonFile = new ClassPathResource("product.json").getFile();

        Gson gson = new Gson();

        List<SaveProductCommand> saveProductCommandList =
                gson.fromJson(new FileReader(jsonFile), new TypeToken<List<SaveProductCommand>>() {}.getType());

        productRepositoryPort.saveProductList(saveProductCommandList);
    }
}
