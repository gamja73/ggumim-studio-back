package pbl.project.ggumimstudioBack.common.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

import java.util.List;

@Converter
@AllArgsConstructor
public class LongListConverter implements AttributeConverter<List<Long>, String>
{

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Long> dataList)
    {
        try
        {
            return objectMapper.writeValueAsString(dataList);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException("Error converting List<Long> to String", e);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String data)
    {
        try
        {
            return objectMapper.readValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, Long.class));
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException("Error converting String to List<Long>", e);
        }
    }
}
