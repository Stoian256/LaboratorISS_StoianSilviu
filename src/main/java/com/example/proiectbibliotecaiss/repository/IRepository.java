package com.example.proiectbibliotecaiss.repository;

import java.util.Collection;

/**
 * @param <E> tipul entitatilor salvate in repository
 */
public interface IRepository<E> {

    /**Metoda ce adauga entitatea data ca parametru
     * @param entity entitatea ce se vrea adaugata
     */
    int add(E entity);

    /**Metoda ce returneaza o entitate pe baza username-ului
     * @param username username-ul entitatii de gasit
     * @return entitatea cu username-ul dat
     */
    E findByUsername(String username);

    /**Metoda ce returneaza o entitate pe baza id-ului
     * @param ID id-ul entitatii de gasit
     * @return entitatea cu id-ul dat
     */
    E findOne(int ID);

    /** Metoda ce returneaza toate entitatiile
     * @return entidatiile din repositori
     */
    Collection<E> findAll();

    /**Modifica entitata
     * @param entity entitatea cu noile caracteristici
     */
    void update(E entity);

    /**Metoda ce sterge o entidate pe baza id-ului dat ca argument
     * @param ID id-ul entitatii de sters
     */
    void delete(int ID);
}
