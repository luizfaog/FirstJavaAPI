package com.api.luizfaog.demo.services;

import com.api.luizfaog.demo.models.MensagemModel;
import com.api.luizfaog.demo.repositories.MensagemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class MensagemService {
    @Autowired
    private MensagemRepository _mensagemRepository;

    public Optional<MensagemModel> Get(int id){
        return _mensagemRepository.findById(id);
    }

    public List<MensagemModel> GetAll(){
        return _mensagemRepository.findAll();
    }

    public void AddOrUpdate(MensagemModel mensagemModel) {
        _mensagemRepository.save(mensagemModel);
    }

    public void Delete(int id) {
        Optional<MensagemModel> mensagem = this.Get(id);

        if(mensagem != null)
            _mensagemRepository.delete(mensagem.get());
    }


}

