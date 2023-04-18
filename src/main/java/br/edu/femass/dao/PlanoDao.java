package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Plano;

public class PlanoDao extends Persist implements Dao<Plano> {

    public PlanoDao() {
        super("plano.json");
    }

    public boolean gravar(Plano objeto) throws StreamWriteException, IOException {
        Set<Plano> planos = buscar();
        boolean gravou = planos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    public boolean excluir(Plano objeto) throws StreamWriteException, IOException {
        Set<Plano> planos = buscar();
        for (Plano planoSelecionado : planos) {
            if (planoSelecionado.equals(objeto)) {
                planoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return true;
    }

    public Set<Plano> buscar() throws DatabindException {
        try {
            Set<Plano> planos = objectMapper.readValue(arquivo, new TypeReference<Set<Plano>>() {
            });
            Plano.atualizarUltimoId(planos);
            return planos;
        } catch (IOException ex) {
            return new HashSet<Plano>();
        }
    }

    public List<Plano> buscarAtivos() throws DatabindException {
        Set<Plano> planos = buscar();

        List<Plano> planoAtivo = planos
                .stream()
                .filter(plano -> plano.getAtivo().equals(true))
                .collect(Collectors.toList());

        return planoAtivo;
    }
}
