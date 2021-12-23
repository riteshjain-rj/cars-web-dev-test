package demo.com.cars.demo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

/**
 * Created by ritjain on 12/20/2021.
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
        "word",
        "score"
})
public class CarScore {
    @JsonProperty("word")
    public String word;

    @JsonProperty("score")
    public String score;
}
