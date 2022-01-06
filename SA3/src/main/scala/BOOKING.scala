import akka.actor.{ActorSystem, Props}
import java.text.SimpleDateFormat
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
object BOOKING extends App {
  val system = ActorSystem("Booking")
  val searchActor = system.actorOf(Props[Search], "search")
  val format = new SimpleDateFormat("yyyy-MM-dd")
  val date = format.parse("2022-09-11")
  val searchHotel = system.actorOf(Props(new SystemService(date, Hotel, "null", "null", "null", 0, searchActor)), "searchForHotel")
  Thread.sleep(3000)
  val searchApartment = system.actorOf(Props(new SystemService(date, Apartment, "null", "null", "null", 0, searchActor)), "searchForApartment")
  Thread.sleep(3000)
  val searchResort = system.actorOf(Props(new SystemService(date, Resort, "null", "null", "null", 0, searchActor)), "searchForResort")
  Thread.sleep(3000)
  val searchName = system.actorOf(Props(new SystemService(date, NON, "Eram", "null", "null", 0, searchActor)), "searchForName")
  Thread.sleep(3000)
  val search4 = system.actorOf(Props(new SystemService(date, NON, "null", "Tehran", "Iran", 5, searchActor)), "search4")
  Thread.sleep(3000)
  val search5 = system.actorOf(Props(new SystemService(date, NON, "null", "null", "null", 5, searchActor)), "search5")
  Thread.sleep(3000)
  system.terminate()
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
