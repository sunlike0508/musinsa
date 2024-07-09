package musinsa;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import musinsa.application.port.out.ProductRepositoryPort;
import musinsa.application.port.out.command.SaveProductCommand;
import org.springframework.core.io.ClassPathResource;

@RequiredArgsConstructor
public class InitDataProcess {

    private final ProductRepositoryPort productRepositoryPort;


    public void init() throws IOException {

        File jsonFile = new ClassPathResource("product.json").getFile();

        Gson gson = new Gson();

        List<SaveProductCommand> saveProductCommandList =
                gson.fromJson(new FileReader(jsonFile), new TypeToken<List<SaveProductCommand>>() {}.getType());

        productRepositoryPort.saveProductList(saveProductCommandList);
    }
}
