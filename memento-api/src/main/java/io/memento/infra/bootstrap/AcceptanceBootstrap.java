package io.memento.infra.bootstrap;

import io.memento.infra.util.DataGenerator;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Profile("acceptance")
public class AcceptanceBootstrap extends ApplicationBootstrap {

    @Inject
    private DataGenerator dataGenerator;

    @Override
    void bootstrap() {
        dataGenerator.generateData();
    }
}
