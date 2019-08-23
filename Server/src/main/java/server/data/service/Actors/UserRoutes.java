package server.data.service.Actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.*;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import server.data.service.Actors.PlayerRegistryActor.*;
import server.data.service.Core.Interface.BrokerMessage;
import server.data.service.Core.Interface.GameLobbyMessage;
import server.data.service.Core.Interface.MarketMessages;
import server.data.service.Core.Interface.MarketMessages.*;
import server.data.service.Core.Interface.PlayerRegistryMessages;
import server.data.service.Core.Interface.PlayerRegistryMessages.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import static ch.megard.akka.http.cors.javadsl.CorsDirectives.cors;
import static ch.megard.akka.http.cors.javadsl.CorsDirectives.corsRejectionHandler;

public class UserRoutes extends AllDirectives {
    //#user-routes-class
    final private ActorRef userRegistryActor;
    final private ActorRef marketActor;
    final private ActorRef gameLobbyActor;
    final private ActorRef brokerActor;
    final private LoggingAdapter log;


    public UserRoutes(ActorSystem system, ActorRef userRegistryActor, ActorRef marketActor, ActorRef gameLobbyActor,ActorRef brokerActor) {
        this.userRegistryActor = userRegistryActor;
        this.marketActor = marketActor;
        this.gameLobbyActor = gameLobbyActor;
        this.brokerActor = brokerActor;
        log = Logging.getLogger(system, this);
    }

    // Required by the `ask` (?) method below
    Duration timeout = Duration.ofSeconds(5l); // usually we'd obtain the timeout from the system's configuration

    public Route routes() {
        // Your CORS settings are loaded from `application.conf`

        // Your rejection handler
        final RejectionHandler rejectionHandler = corsRejectionHandler().withFallback(RejectionHandler.defaultHandler());

        // Your exception handler
        final ExceptionHandler exceptionHandler = ExceptionHandler.newBuilder()
                .match(NoSuchElementException.class, ex -> complete(StatusCodes.NOT_FOUND, ex.getMessage()))
                .build();

        // Combining the two handlers only for convenience
        final Function<Supplier<Route>, Route> handleErrors = inner -> Directives.allOf(
                s -> handleExceptions(exceptionHandler, s),
                s -> handleRejections(rejectionHandler, s),
                inner
        );

        return handleErrors.apply(() -> cors(() -> handleErrors.apply(() -> route(pathPrefix("signup", () ->
                        route(
                                PlayerRegistry()
                        )
                ), pathPrefix("profile", () ->
                        route(
                                path(PathMatchers.segment(), name -> route(
                                        getUser(name)
                                        )
                                )
                        )
                ), pathPrefix("turn", () ->
                        route(
                                path(PathMatchers.segment(), turn -> route(
                                        getMarketTurn(turn)
                                        )
                                )
                        )
                ), pathPrefix("gameroom", () ->
                        route(
                                MakeGameRoom()

                        )
                ),
                pathPrefix("getgamerooms", () ->
                        route(
                                getAllRooms()

                        )
                ),
                pathPrefix("joingame", () ->
                        route(
                                JoinGame()

                        )
                ),
                pathPrefix("buy", () ->
                        route(
                                BuyStock()

                        )
                ),
                pathPrefix("sell", () ->
                        route(
                                SellStock()
                        )
                ),
                pathPrefix("roomplayers", () ->
                        route(
                                path(PathMatchers.segment(), turn -> route(
                                        getRoomPlayers(turn)
                                        )
                                )
                        )
                )
        ))));
    }

    private Route PlayerRegistry() {
        return pathEnd(() ->
                route(
                        post(() ->
                                entity(
                                        Jackson.unmarshaller(Player.class),
                                        user -> {
                                            CompletionStage<ActionPerformed> userCreated = Patterns
                                                    .ask(userRegistryActor, new PlayerRegistryMessages.CreateUser(user), timeout)
                                                    .thenApply(ActionPerformed.class::cast);
                                            return onSuccess(() -> userCreated,
                                                    performed -> {
                                                        log.info("Created user [{}]: {}", user.getName(), performed.getPlayer());
                                                        return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                                                    });
                                        }))
                )
        );
    }

    private Route getUser(String name) {
        return get(() -> {
            CompletionStage<GetProfile> maybeUser = Patterns
                    .ask(userRegistryActor, new PlayerRegistryMessages.GetUser(name), timeout)
                    .thenApply(GetProfile.class::cast);
            return onSuccess(() -> maybeUser,
                    performed -> {
                        log.info("Get Users[{}]: {}", performed.getName(), performed.getPlayerId());
                        return complete(StatusCodes.OK, performed, Jackson.marshaller());
                    }
            );
        });
    }


    private Route getMarketTurn(String turn) {
        return get(() -> {
            CompletionStage<MarketActor.StockTurns> getMarket = Patterns
                    .ask(marketActor, new MarketMessages.GetTurn(turn), timeout)
                    .thenApply(MarketActor.StockTurns.class::cast);
            return onSuccess(() -> getMarket,
                    performed -> {
                        log.info("Get Stocks[{}]: {}", performed.getStocks(), performed.getStocks());
                        return complete(StatusCodes.OK, performed, Jackson.marshaller());
                    }
            );
        });
    }

    private Route MakeGameRoom() {
        return post(() -> entity(Jackson.unmarshaller(GameLobbyActor.InitGameRoom.class),
                game -> {
                    CompletionStage<GameLobbyMessage.ActionPerformed> getGameName = Patterns
                            .ask(gameLobbyActor, new GameLobbyMessage.GetGameRoom(game), timeout)
                            .thenApply(GameLobbyMessage.ActionPerformed.class::cast);
                    return onSuccess(() -> getGameName,
                            performed -> {
                                log.info("Get getGameName[{}]: {}", performed.getDescription(), performed.getDescription());
                                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                            }
                    );
                }));
    }
    private Route BuyStock() {
        return post(() -> entity(Jackson.unmarshaller(BrokerActor.StockBroker.class),
                stock -> {
                    CompletionStage<BrokerMessage.ActionPerformed> getResponce = Patterns
                            .ask(brokerActor, new BrokerMessage.BuyStock(stock), timeout)
                            .thenApply(BrokerMessage.ActionPerformed.class::cast);
                    return onSuccess(() -> getResponce,
                            performed -> {
                                log.info("Get getGameName[{}]: {}", performed.getDescription(), performed.getDescription());
                                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                            }
                    );
                }));
    }
    private Route SellStock() {
        return post(() -> entity(Jackson.unmarshaller(BrokerActor.StockBroker.class),
                stock -> {
                    CompletionStage<BrokerMessage.ActionPerformed> getResponce = Patterns
                            .ask(brokerActor, new BrokerMessage.SellStock(stock), timeout)
                            .thenApply(BrokerMessage.ActionPerformed.class::cast);
                    return onSuccess(() -> getResponce,
                            performed -> {
                                log.info("Get getGameName[{}]: {}", performed.getDescription(), performed.getDescription());
                                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                            }
                    );
                }));
    }
    private Route JoinGame() {
        return post(() -> entity(Jackson.unmarshaller(GameLobbyActor.InitGameRoom.class),
                game -> {
                    CompletionStage<GameLobbyMessage.ActionPerformed> getGameName = Patterns
                            .ask(gameLobbyActor, new GameLobbyMessage.JoinGameRoom(game), timeout)
                            .thenApply(GameLobbyMessage.ActionPerformed.class::cast);
                    return onSuccess(() -> getGameName,
                            performed -> {
                                log.info("Get getGameName[{}]: {}", performed.getDescription(), performed.getDescription());
                                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                            }
                    );
                }));
    }
    private Route getAllRooms() {
        return get(() -> {
            CompletionStage<GameLobbyActor.GameRooms> getGameName = Patterns
                    .ask(gameLobbyActor, new GameLobbyMessage.GetAllGameRooms(), timeout)
                    .thenApply(GameLobbyActor.GameRooms.class::cast);
            return onSuccess(() -> getGameName,
                    performed -> {
                        log.info("Get getGameName[{}]: {}", performed.getGameRooms(), performed.getGameRooms());
                        return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                    }
            );
        });
    }
    private Route getRoomPlayers(String name) {
        return get(() -> {
            CompletionStage<GameLobbyActor.GameRoomPlayers> maybeUser = Patterns
                    .ask(gameLobbyActor, new GameLobbyMessage.GetRoomPlayers(name), timeout)
                    .thenApply(GameLobbyActor.GameRoomPlayers.class::cast);
            return onSuccess(() -> maybeUser,
                    performed -> {
                        log.info("Get Users[{}]: {}", performed.getGameRoomPlayers(), performed.getGameRoomPlayers());
                        return complete(StatusCodes.OK, performed, Jackson.marshaller());
                    }
            );
        });
    }
}