package com.example.project.services;


import com.example.project.models.Person;
import com.example.project.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServices {

    private final PasswordEncoder passwordEncoder;

    private final PersonRepository personRepository;

    @Autowired
    public RegistrationServices(PasswordEncoder passwordEncoder, PersonRepository personRepository) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword())); // шифрование пароля
        person.setRole("ROLE_USER"); // роль для пользователей
        personRepository.save(person);
    }
}