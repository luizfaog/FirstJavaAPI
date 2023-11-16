package com.api.luizfaog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.luizfaog.demo.models.MensagemModel;


public interface MensagemRepository extends JpaRepository<MensagemModel, Integer>{

}
