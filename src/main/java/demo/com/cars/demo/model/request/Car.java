package demo.com.cars.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ritjain on 12/19/2021.
 */
@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @JsonProperty("id")
    private Integer id;

    ///@JsonProperty("make")
    private String make;

   /// @JsonProperty("model")
    private String model;

   // @JsonProperty("colour")
    private String colour;

 //   @JsonProperty("year")
    private int year;

}
