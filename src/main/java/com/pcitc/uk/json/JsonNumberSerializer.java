package com.pcitc.uk.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonNumberSerializer extends JsonSerializer<Boolean> {

	@Override
	public void serialize(Boolean arg, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {

		generator.writeNumber(arg ? 1 : 0);
	}

}
