package pbl.project.ggumimstudioBack.common.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@AllArgsConstructor
public class StringListConverter implements AttributeConverter<List<String>, String>
{

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<String> dataList)
    {
        try
        {
            return objectMapper.writeValueAsString(dataList);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String data)
    {
        try
        {
            return objectMapper.readValue(data, List.class);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
