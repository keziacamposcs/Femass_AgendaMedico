package br.edu.femass.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.femass.model.PlanoDeSaude;

public class PlanoDeSaudeDao extends Persist implements Dao<PlanoDeSaude> {

    public PlanoDeSaudeDao() {
        super("planodesaude.json");
    }

    public boolean gravar(PlanoDeSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoDeSaude> planos = buscar();
        boolean gravou = planos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    public boolean excluir(PlanoDeSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoDeSaude> planos = buscar();
        for (PlanoDeSaude planoSelecionado : planos) {
            if (planoSelecionado.equals(objeto)) {
                planoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return true;
    }

    public Set<PlanoDeSaude> buscar() throws DatabindException {
        try {
            Set<PlanoDeSaude> planos = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoDeSaude>>() {
            });
            PlanoDeSaude.atualizarUltimoId(planos);
            return planos;
        } catch (IOException ex) {
            return new HashSet<PlanoDeSaude>();
        }
    }

    public List<PlanoDeSaude> buscarAtivos() throws DatabindException {
        Set<PlanoDeSaude> planos = buscar();

        List<PlanoDeSaude> planosAtivos = planos
                .stream()
                .filter(plano -> plano.getAtivo().equals(true))
                .collect(Collectors.toList());

        return planosAtivos;

    }
}
