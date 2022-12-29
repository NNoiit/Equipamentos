package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.Status;
import com.api.equipamento.repositori.RepBicicleta;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BicicletaServiceTest {

    @InjectMocks
    private BicicletaService service;

    @Mock
    private Bicicleta bicicleta;

    @Mock
    private RepBicicleta bicicletaRep;

    @Mock
    private Mensage mensage;
    @Test
    public void cadastroBikeTest() {
        inciarBike();
        System.out.println(service.cadastrar(bicicleta));
        //assertEquals(service, bicicleta);
    }
    @Test
    public void listarBicicletasTest(){
        List<Bicicleta> listaTest = service.listarBicicletas();
        System.out.println(listaTest);
    }

    @Test
    public void bicicletaFindId(){
        System.out.println(service.bicicletaFindId(1));

    }

    @Test
    public void alterarBicicleta(){
    }

    @Test
    public void excluirBicicleta(){
    }

    private void inciarBike() {
        bicicleta = new Bicicleta();

        bicicleta.setMarca("ola");
        bicicleta.setNumero(9);
        bicicleta.setAno("ola");
        bicicleta.setModelo("ola");

        bicicleta.setStatusBike(Status.LIVRE);

    }
}
