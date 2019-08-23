package server.data.service;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import server.data.service.Actors.*;
import server.data.service.Core.Interface.Stock;
import server.data.service.Core.Services.DBQueries;

public class StartServer extends AllDirectives {

    private final UserRoutes userRoutes;

    public StartServer(ActorSystem system, ActorRef userRegistryActor,ActorRef marketActor,ActorRef gameLobbyActor,ActorRef brokerActor) {
        userRoutes = new UserRoutes(system, userRegistryActor,marketActor,gameLobbyActor,brokerActor);
    }


    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("helloAkkaHttpServer");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        ActorRef playerRegistryActor = system.actorOf(PlayerRegistryActor.props(), "userRegistryActor");
        ActorRef marketActor = system.actorOf(MarketActor.props(), "marketActor");
        ActorRef gameLobbyActor = system.actorOf(GameLobbyActor.props(), "gameLobbyActor");
        ActorRef brokerActor = system.actorOf(BrokerActor.props(), "brokerActor");


        StartServer app = new StartServer(system, playerRegistryActor,marketActor,gameLobbyActor,brokerActor);
        System.out.println("Establishing Database connections......");
        DBManger st = new DBManger();
        st.getdbConnect();

        System.out.println("Established Database connections.");

        System.out.println("************************************************");



//       System.out.println("Market" + a);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/");
        //#http-server
    }

    protected Route createRoute() {
        return userRoutes.routes();
    }
}   