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

import br.edu.femass.model.Cliente;

public class ClienteDao extends Persist implements Dao<Cliente> {

    public ClienteDao() {
        super("clientes.json");
    }

    public boolean gravar(Cliente objeto) throws StreamWriteException, IOException {
        Set<Cliente> clientes = buscar();
        boolean gravou = clientes.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, clientes);
        return gravou;
    }

    public boolean excluir(Cliente objeto) throws StreamWriteException, IOException {
        Set<Cliente> clientes = buscar();
        for (Cliente clienteSelecionado : clientes) {
            if (clienteSelecionado.equals(objeto)) {
                clienteSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, clientes);
        return true;
    }

    public Set<Cliente> buscar() throws DatabindException {
        try {
            Set<Cliente> clientes = objectMapper.readValue(arquivo, new TypeReference<Set<Cliente>>() {
            });
            Cliente.atualizarUltimoId(clientes);
            return clientes;
        } catch (IOException ex) {
            return new HashSet<Cliente>();
        }
    }

    public List<Cliente> buscarAtivos() throws DatabindException {
        Set<Cliente> clientes = buscar();

        List<Cliente> clientesAtivos = clientes
                .stream()
                .filter(cliente -> cliente.getAtivo().equals(true))
                .collect(Collectors.toList());

        return clientesAtivos;

    }
}
