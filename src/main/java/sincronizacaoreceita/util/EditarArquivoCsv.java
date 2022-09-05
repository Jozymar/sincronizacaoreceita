package sincronizacaoreceita.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sincronizacaoreceita.domain.ContaCsv;
import sincronizacaoreceita.service.ReceitaService;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Jozymar<jozymarsoares@gmail.com>
 */
@Component
@Log4j2
public class EditarArquivoCsv {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ApplicationArguments args;

    // Método que recebe o arquivo passado como argumento, faz a leitura dos dados e gera um novo arquivo contendo o resultado
    @Bean
    public void atualizarArquivo() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        if (args.getSourceArgs().length != 0) {

            // Recupera o arquivo passado como argumento
            Reader reader = Files.newBufferedReader(Paths.get(args.getSourceArgs()[0]));

            // Realiza o parse das informações do arquivo para uma lista do tipo da classe de mapeamento
            List<ContaCsv> contas = new CsvToBeanBuilder(reader)
                    .withSeparator(';')
                    .withType(ContaCsv.class)
                    .build()
                    .parse();

            // Gera uma nova lista contendo o camṕo referente ao resultado do processamento
            List<ContaCsv> contasAtualizadas =
                    contas.stream().map(this::atualizarResultadoConta).toList();

            // Gera um novo arquivo chamado "contas-atualizadas.csv" que contém os dados das contas atualizados
            Writer writer = Files.newBufferedWriter(Paths.get("contas-atualizadas.csv"));
            StatefulBeanToCsv<ContaCsv> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar('\u0000')
                    .withSeparator(';')
                    .build();

            beanToCsv.write(contasAtualizadas);

            writer.flush();
            writer.close();

        } else {
            log.info("Informe o arquivo.");
        }

    }

    // Método que atualiza os dados da conta e adiciona o resultado do processamento
    private ContaCsv atualizarResultadoConta(ContaCsv contaCsv) {
        try {
            contaCsv.setResultado(receitaService.atualizarConta(contaCsv.getAgencia(), formatarConta(contaCsv),
                    formatarSaldo(contaCsv), contaCsv.getStatus()));
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        return contaCsv;
    }

    // Método que converte o valor do saldo para o tipo esperado
    private double formatarSaldo(ContaCsv contaCsv) {
        return Double.parseDouble(contaCsv.getSaldo().replace(",", "."));
    }

    // Método que remove caracteres desnecessários do número da conta
    private String formatarConta(ContaCsv contaCsv) {
        return contaCsv.getConta().replace("-", "");
    }
}
