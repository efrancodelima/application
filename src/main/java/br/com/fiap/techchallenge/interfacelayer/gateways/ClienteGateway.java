package br.com.fiap.techchallenge.interfacelayer.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.mappers.ClienteMapper;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.IClienteRepository;

@Component
public class ClienteGateway implements InClienteGateway {

    // Atributos
    @Autowired
    private IClienteRepository clienteJpaRepository;

    // Métodos públicos
    @Override
    public Cliente gravarCliente(Cliente cliente) throws Exception {

        ClienteJpa clienteJpa = ClienteMapper.getClienteJpa(cliente);

        clienteJpa = clienteJpaRepository.save(clienteJpa);

        return ClienteMapper.getCliente(clienteJpa);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) throws Exception {

        ClienteJpa clienteJpa = clienteJpaRepository.findByCpf(cpf.pegarNumeroComDigito());

        if (clienteJpa != null) {
            return ClienteMapper.getCliente(clienteJpa);
        } else {
            return null;
        }
    }

    @Override
    public boolean clienteJaExiste(Cpf cpf) throws Exception {
        return clienteJpaRepository.existsByCpf(cpf.pegarNumeroComDigito());
    }

}
