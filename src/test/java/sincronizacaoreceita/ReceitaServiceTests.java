package sincronizacaoreceita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sincronizacaoreceita.service.ReceitaService;

@SpringBootTest
class ReceitaServiceTests {

	@Autowired
	private ReceitaService receitaService;

	@Test
	void dadosValidos() throws InterruptedException {

		boolean result = receitaService.atualizarConta("0101", "123456", 100.50, "A");
		Assertions.assertTrue(result);
	}

	@Test
	void agenciaInvalida() throws InterruptedException {

		boolean result = receitaService.atualizarConta("01010", "123456", 100.50, "I");
		Assertions.assertFalse(result);
	}

	@Test
	void contaInvalida() throws InterruptedException {

		boolean result = receitaService.atualizarConta("0101", "12345-6", 100.50, "B");
		Assertions.assertFalse(result);
	}

	@Test
	void statusInvalido() throws InterruptedException {

		boolean result = receitaService.atualizarConta("0101", "123456", 100.50, "J");
		Assertions.assertFalse(result);
	}

}
