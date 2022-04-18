package com.example.proiectbibliotecaiss.business;

import com.example.proiectbibliotecaiss.domain.Librarian;
import com.example.proiectbibliotecaiss.domain.Subscriber;
import com.example.proiectbibliotecaiss.repository.SubscriberDbRepository;

import java.util.Collection;

public class SubscriberService {
    private final SubscriberDbRepository subscriberDbRepository;

    public SubscriberService(SubscriberDbRepository subscriberDbRepository) {
        this.subscriberDbRepository = subscriberDbRepository;
    }

    public int addSubscriber(String CNP,String firstName,String lastName,String address,String phone,String password){
        Subscriber subscriber=new Subscriber(CNP,firstName,lastName,address,phone,password);
         return subscriberDbRepository.add(subscriber);
    }

    public Subscriber findSubscriberByUsername(String username){
        return subscriberDbRepository.findByUsername(username);
    }

    public Collection<Subscriber> findAllSubscribers(){
        return subscriberDbRepository.findAll();
    }
}
