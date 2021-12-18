package ua.module7.hibernate.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class MapperService {

    private static final ModelMapper modelMapper;
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public static <From, To> To convert(From from, Class<To> toClass) {
        return modelMapper.map(from, toClass);
    }

    public static <From, To> List<To> convertList(List<From> fromList, Class<To> toClass) {
        return fromList
                .stream()
                .map(item -> modelMapper.map(item, toClass))
                .collect(Collectors.toList());
    }
}
