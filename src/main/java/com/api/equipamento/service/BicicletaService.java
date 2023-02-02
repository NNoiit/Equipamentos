package com.api.equipamento.service;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepRede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.repositori.RepBicicleta;
import java.util.List;
import java.util.UUID;
import com.api.equipamento.repositori.RepTranca;

@Service
public class BicicletaService{
    @Autowired
    private Erro mensage;

    @Autowired
    private RepBicicleta bicicletaRep;

    @Autowired
    private RepRede repRede;

    @Autowired
    private RepTranca repTranca;
    @Autowired
    private StatusService statusService;

    public Bicicleta cadastrar(Bicicleta bicicleta){

        if(bicicleta.getModelo().equals("")
                || bicicleta.getAno().equals("")
                || bicicleta.getMarca().equals("")
                || !bicicleta.getStatus().equals(Status.NOVA)
                ){
            return null;
        }else {
            return bicicletaRep.save(bicicleta);
        }
    }

    public List<Bicicleta> listarBicicletas(){
        return bicicletaRep.findAll();
    }

    public Bicicleta bicicletaFindId(UUID id){
        if(bicicletaRep.countByUuid(id) == 0){
            return null;
        }else {
            return bicicletaRep.findByUuid(id);
        }
    }

    public Bicicleta alterarBicicleta(Bicicleta bicicletaAlterada, UUID id){
        Bicicleta bicicletaNoSistema = bicicletaRep.findByUuid(id);
        if(bicicletaNoSistema != null) {
            if (bicicletaAlterada.getMarca().equals("")
                    || bicicletaAlterada.getModelo().equals("")
                    || bicicletaAlterada.getAno().equals("")
            ) {
                return null;
            } else {

                bicicletaNoSistema.setMarca(bicicletaAlterada.getMarca());
                bicicletaNoSistema.setModelo(bicicletaAlterada.getModelo());
                bicicletaNoSistema.setAno(bicicletaAlterada.getAno());

                return bicicletaRep.save(bicicletaNoSistema);
            }
        } else {
            return null;
        }
    }

    public Erro excluirBicicleta(UUID id){
        
        if(bicicletaRep.countByUuid(id)==0){
            mensage.setMensage("Não encontrado");
            mensage.setMensage("NOT FOUD");
            return mensage;
        }

        Bicicleta bicicletaParaExclusao = bicicletaRep.findByUuid(id);
        if(bicicletaParaExclusao.getStatus().equals(Status.APOSENTADA)
        && repTranca.countByBicicleta(bicicletaParaExclusao.getUuid()) == 0) {

            bicicletaRep.delete(bicicletaParaExclusao);
            mensage.setMensage("Dados removidos");
            mensage.setMensage("OK");

        }
        return mensage;
    }

    public Erro alterarStatusBicicleta(UUID idBicicleta, Status status) {
        if (bicicletaRep.countByUuid(idBicicleta) == 1) {
            Bicicleta bicicleta1 = bicicletaRep.findByUuid(idBicicleta);
            bicicleta1.setStatusBike(status);
            bicicletaRep.save(bicicleta1);
            mensage.setMensage("Ação bem sucedida");
            return mensage;
        } else {
            mensage.setMensage("Não encontrado");
            return mensage;
        }
    }

    public boolean integrarNaRede(IdsEquipamentos dados){
        Bicicleta bicicletaInserir = bicicletaRep.findByUuid(dados.getBicicleta());
        if (bicicletaInserir.getStatus().equals(Status.NOVA) || bicicletaInserir.getStatus().equals(Status.EM_REPARO)) {
            List<Rede> listaTotens = repRede.findAll();
            for (int i = 0; listaTotens.size() > i; i++) {
                Rede totem = listaTotens.get(i);
                List<UUID> listaTranca = totem.getIdTranca();
                for (int j = 0; listaTranca.size() > j; j++) {
                    if (listaTranca.get(j).equals(dados.getTranca())) {
                        //busca a tranca e salva o id da bicicleta na tranca
                        statusService.inserirBicicletaTranca(dados.getTranca(), dados.getBicicleta());
                        //salva o id da bicicleta no totem
                        List<UUID> listaBicicleta = totem.getIdBicicleta();
                        listaBicicleta.add(dados.getBicicleta());
                        alterarStatusBicicleta(dados.getBicicleta(), Status.DISPONIVEL);
                        repRede.save(totem);
                        return true;
                    }
                }
            }
        } else if (bicicletaInserir.getStatus().equals(Status.EM_USO)) {
            statusService.inserirBicicletaTranca(dados.getTranca(), dados.getBicicleta());
            bicicletaInserir.setStatusBike(Status.DISPONIVEL);
            return true;
        }

        return false;
    }

    public boolean retirarDaRede(IdsEquipamentos dados) {

        List<Rede> listaTotens = repRede.findAll();
            for (int i = 0; listaTotens.size() > i; i++) {
                Rede totem = listaTotens.get(i);
                List<UUID> listaTranca = totem.getIdTranca();
                for (int j = 0; listaTranca.size() > j; j++) {
                    if (listaTranca.get(j).equals(dados.getTranca())) {
                        List<UUID> listaBicicleta = totem.getIdBicicleta();
                        for (int h = 0; listaBicicleta.size() > h; h++) {
                            if (listaBicicleta.get(h).equals(dados.getBicicleta())) {
                                listaBicicleta.remove(listaBicicleta.get(h));
                                statusService.alterarStatusTranca(dados.getTranca(), Acao.DESTRANCAR);
                                alterarStatusBicicleta(dados.getBicicleta(), Status.EM_REPARO);
                                repRede.save(totem);
                                return true;
                            }
                        }
                    }
                }
            }

        return false;
    }
}
