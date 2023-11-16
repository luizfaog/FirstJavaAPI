package com.api.luizfaog.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.luizfaog.demo.models.MensagemModel;
import com.api.luizfaog.demo.services.MensagemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    @Autowired
    private MensagemService _mensagemService;
    @GetMapping(value="")
    public ResponseEntity<List<MensagemModel>> getAll() {
        List<MensagemModel> mensagens = _mensagemService.GetAll();
        return new ResponseEntity<>(mensagens, HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<MensagemModel> get(@PathVariable("id") int id) {
        Optional<MensagemModel> mensagem = _mensagemService.Get(id);
        if(!mensagem.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(mensagem.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/novaMensagem")
    public ResponseEntity<Void> add(@RequestBody MensagemModel mensagemModel) {
        _mensagemService.AddOrUpdate(mensagemModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/editarMensagem")
    public ResponseEntity<MensagemModel> update(@RequestBody MensagemModel mensagemModel) {
        Optional<MensagemModel> msg = _mensagemService.Get(mensagemModel.getId());
        if(!msg.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _mensagemService.AddOrUpdate(mensagemModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        Optional<MensagemModel> mensagemExistente = _mensagemService.Get(id);
        if (!mensagemExistente.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _mensagemService.Delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}