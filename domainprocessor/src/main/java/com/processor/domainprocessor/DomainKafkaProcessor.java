package com.processor.domainprocessor;
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean;
import org.processor.Domain;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Function;

@Configuration
public class DomainKafkaPorcesssor {
    @Bean
    public Function<KStream<String, Domain>, <KStream<String, Domain>> domainProcessor()
    {
        return kstream -> kstream.filter((key, domain) -> {
            if(domain.isDead()) {
                System.out.println("Inactive Domain: " +domain.getDomain());
            }
            else{
                System.out.println("Active Domain: "+domain.getDomain());
            }
            return !domain.isDead();
        });
    }
}
