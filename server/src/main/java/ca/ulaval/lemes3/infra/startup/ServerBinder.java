package ca.ulaval.lemes3.infra.startup;

import ca.ulaval.lemes3.application.*;
import ca.ulaval.lemes3.application.PartyRepository;
import ca.ulaval.lemes3.infra.audio_sharing.*;
import ca.ulaval.lemes3.infra.audio_sharing.socket_io.InMemoryUserToSocketRepository;
import ca.ulaval.lemes3.infra.audio_sharing.socket_io.SocketIoService;
import ca.ulaval.lemes3.infra.audio_sharing.socket_io.WebRTCConnectionHandler;
import ca.ulaval.lemes3.ui.rest.audio_sharing.assembler.PartyAssembler;
import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ServerBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(InMemoryPartyRepository.class).to(PartyRepository.class).in(Singleton.class);

        bind(UUIDIdGenerator.class).to(IdGenerator.class).in(Singleton.class);

        bindAsContract(AudioSharingService.class).in(Singleton.class);

        bindAsContract(PartyFactory.class).in(Singleton.class);

        bindAsContract(PartyAssembler.class).in(Singleton.class);

        bind(JsonWebTokenGenerator.class).to(TokenGenerator.class).in(Singleton.class);

        bind(SocketIoService.class).to(SocketService.class).in(Singleton.class);

        bind(InMemoryUserToSocketRepository.class).to(UserToSocketRepository.class).in(Singleton.class);

        bindAsContract(WebRTCConnectionHandler.class).in(Singleton.class);
    }
}
