package com.ranjay.rest.repository;

import com.ranjay.rest.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    
}