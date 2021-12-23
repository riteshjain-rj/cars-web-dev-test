package demo.com.cars.demo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

/**
 * Created by ritjain on 12/19/2021.
 */
@Getter
@Setter
@JsonSerialize
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "make",
        "model",
        "colour",
        "year",
        "score"
})
public class CarDetails {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("make")
    private String make;

    @JsonProperty("model")
    private String model;

    @JsonProperty("colour")
    private String colour;

    @JsonProperty("year")
    private int year;

    @JsonProperty("score")
    private String score;

}
