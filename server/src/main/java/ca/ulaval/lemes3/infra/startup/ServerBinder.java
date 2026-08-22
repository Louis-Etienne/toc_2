package ca.ulaval.lemes3.infra.startup;

import ca.ulaval.lemes3.domain.actions.PlayActionFactory;
import ca.ulaval.lemes3.infra.repository.BoardRepository;
import ca.ulaval.lemes3.infra.repository.BoardRepositoryNoSql;
import org.dizitart.no2.mvstore.MVStoreModule;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ServerBinder extends AbstractBinder {
    @Override
    protected void configure() {
        MVStoreModule store = MVStoreModule.withConfig().filePath("data.db").build();
        bind(store).to(MVStoreModule.class);
        bind(BoardRepositoryNoSql.class).to(BoardRepository.class);
        bind(PlayActionFactory.class).to(PlayActionFactory.class);
    }

}
