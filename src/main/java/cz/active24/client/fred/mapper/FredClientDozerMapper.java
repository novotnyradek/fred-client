package cz.active24.client.fred.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.Collections;

/**
 * Mapper class with connection to mapping file.
 */
public class FredClientDozerMapper {

    private static String DEFAULT_MAPPING_XML = "fred-client-dozerBeanMapping.xml";
    private static FredClientDozerMapper dozerMapper;
    private Mapper mapper;

    private FredClientDozerMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    private FredClientDozerMapper(String mappingXmlName) {
        this(createDozerMapper(mappingXmlName));
    }

    private FredClientDozerMapper() {
        this(DEFAULT_MAPPING_XML);
    }

    public static FredClientDozerMapper getInstance() {
        if (dozerMapper == null) {
            //thread safe
            synchronized (FredClientDozerMapper.class) {
                if (dozerMapper == null) {
                    dozerMapper = new FredClientDozerMapper();
                }
            }
        }
        return dozerMapper;
    }

    private static Mapper createDozerMapper(String configFileName) {
        return new DozerBeanMapper(Collections.singletonList(configFileName));
    }

    public <T> T map(Object object, Class<T> targetClass) {
        return mapper.map(object, targetClass);
    }

    public <T> T map(Object object, Class<T> targetClass, String mappingId) {
        return mapper.map(object, targetClass, mappingId);
    }
 }
