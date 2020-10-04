package pl.yellowduck.netflix90.films;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//
//@Entity
//@Table(name = "category")
//@Getter @Setter
//public class Category {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  private Integer id;
//
//  private String name;
//
//  private String description;
//
//  private Category() {
//  }
//
//  public Category(String name, String description) {
//    this.name = name;
//    this.description = description;
//  }
//}

public enum Category {

  COMEDY, // orinal value 0

  FANTASY,

  DRAMA,  // orinal value 1

  HORROR, // orinal value 2

  MUSICAL, // orinal value 3

  ACTION; // orinal value 4
}