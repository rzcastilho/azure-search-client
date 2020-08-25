package github.com.rzcastilho.azure.search.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static github.com.rzcastilho.azure.search.Constants.ODATA_CONTEXT;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationResult {

    @JsonProperty(ODATA_CONTEXT)
    private String odataContext;

    private List<IndexResult> value;

}
