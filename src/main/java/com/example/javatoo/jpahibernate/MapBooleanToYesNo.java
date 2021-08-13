package com.example.javatoo.jpahibernate;
/*
DROP TABLE author;

CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  best_selling varchar(3) NOT NULL,
  genre varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

insert into author (age, name, genre, best_selling, id) values (23, "Mark Janel", "Anthology", "No", 1);

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attr) {
        System.out.println("Convert boolean to yes/no ...");
        return attr == null ? "No" : "Yes";
    }
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        System.out.println("Convert yes/no to boolean ...");
        return !"No".equals(dbData);
    }
}

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String name;
    private String genre;
    //@Convert(converter = BooleanConverter.class)
    @NotNull
    private Boolean bestSelling;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean isBestSelling() {
        return bestSelling;
    }

    public void setBestSelling(Boolean bestSelling) {
        this.bestSelling = bestSelling;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", age=" + age
                + ", name=" + name + ", genre=" + genre
                + ", bestSelling=" + bestSelling + '}';
    }

}

 */
public class MapBooleanToYesNo {
}
