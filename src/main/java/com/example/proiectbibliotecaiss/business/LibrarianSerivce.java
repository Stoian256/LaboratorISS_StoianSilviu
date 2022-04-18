package com.example.proiectbibliotecaiss.business;
import com.example.proiectbibliotecaiss.domain.Librarian;
import com.example.proiectbibliotecaiss.repository.LibrarianDbRepository;
import java.util.Collection;

public class LibrarianSerivce {
    private final LibrarianDbRepository librarianDbRepository;

    public LibrarianSerivce(LibrarianDbRepository librarianDbRepository) {
        this.librarianDbRepository = librarianDbRepository;
    }

    public Librarian findLibrarianByUsername(String username){
        return librarianDbRepository.findByUsername(username);
    }

    public Collection<Librarian> findAllLibrarians(){
        return librarianDbRepository.findAll();
    }
}
