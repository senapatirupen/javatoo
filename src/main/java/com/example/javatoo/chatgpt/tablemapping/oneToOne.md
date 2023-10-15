Certainly! In JPA, a one-to-one relationship between two entities can be established using annotations. Here's an example of how to set up a one-to-one relationship between two entities, `Person` and `Passport`, using Spring Data JPA:

1. **Create Entity Classes:**

```java
import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Passport passport;
    
    // Constructors, getters, setters
}
```

```java
import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String passportNumber;
    
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    
    // Constructors, getters, setters
}
```

2. **Establish One-to-One Relationship:**

- In the `Person` entity, use the `@OneToOne` annotation with the `mappedBy` attribute to specify that the relationship is managed by the `passport` field in the `Passport` entity.

- In the `Passport` entity, use the `@OneToOne` annotation with the `@JoinColumn` annotation to specify the column that holds the foreign key to the `Person` entity.

3. **Cascade Operations:**

In the `Person` entity, the `cascade` attribute is set to `CascadeType.ALL`. This means that when you perform operations on a `Person` entity, such as saving or deleting, the corresponding operations will be cascaded to the associated `Passport` entity.

4. **Usage:**

You can then use these entities in your service or controller classes:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public Person savePersonWithPassport(Person person, Passport passport) {
        person.setPassport(passport);
        passport.setPerson(person);
        return personRepository.save(person);
    }
}
```

In this example, the `savePersonWithPassport` method sets up the one-to-one relationship between a `Person` and a `Passport`, and then saves them to the database.

Remember to define repositories for both `Person` and `Passport` entities (`PersonRepository` and `PassportRepository`) that extend `JpaRepository`.

This is a basic example of setting up a one-to-one relationship in JPA using Spring Data JPA. You can expand upon this to include additional fields, relationships, and business logic as needed.