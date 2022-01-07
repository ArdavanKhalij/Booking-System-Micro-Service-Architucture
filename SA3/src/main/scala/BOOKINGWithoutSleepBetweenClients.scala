import BOOKING.{Apartment, Client, Hotel, NON, Resort, SystemService}
import akka.actor.{ActorSystem, Props}

import java.text.SimpleDateFormat

object BOOKINGWithoutSleepBetweenClients extends App {
  val system = ActorSystem("Booking")
  val searchActor = system.actorOf(Props[SystemService], "search")
  val format = new SimpleDateFormat("yyyy-MM-dd")
  val date = format.parse("2022-09-11")
  val searchHotel = system.actorOf(Props(new Client("Y44986738", date, Hotel, "null", "null",
    "null", 0, searchActor)), "searchForHotel")
  val searchApartment = system.actorOf(Props(new Client("Y44986739", date, Apartment, "null",
    "null", "null", 0, searchActor)), "searchForApartment")
  val searchResort = system.actorOf(Props(new Client("Y44986740", date, Resort, "null",
    "null", "null", 0, searchActor)), "searchForResort")
  val searchName = system.actorOf(Props(new Client("Y44986741", date, NON, "Eram", "null",
    "null", 0, searchActor)), "searchForName")
  val search4 = system.actorOf(Props(new Client("Y44986742", date, NON, "null", "Tehran",
    "Iran", 5, searchActor)), "search4")
  val search5 = system.actorOf(Props(new Client("Y44986743", date, NON, "null", "null",
    "null", 5, searchActor)), "search5")
  Thread.sleep(1000)
  system.terminate()
}
