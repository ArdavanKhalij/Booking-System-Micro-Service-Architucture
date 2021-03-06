import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

import java.text.SimpleDateFormat
import java.util.{Date, Random}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
object BOOKING extends App {
  var PROPERTIES = List(
    property(1, "Avenue Louise", Hotel, 4, "Belgium", "Brussels", List(), 70),
    property(2, "Azadi", Hotel, 5, "Iran", "Tehran", List(), 99),
    property(3, "Arora", Resort, 4, "Turkey", "Istanbul", List(), 24),
    property(4, "Diamond", Apartment, 3, "Belgium", "Brussels", List(), 24),
    property(5, "Hilton", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(6, "Dedeman", Resort, 5, "Turkey", "Antalyia", List(), 24),
    property(7, "LUX", Apartment, 4, "USA", "Las Vegas", List(), 24),
    property(8, "SIDE", Resort, 3, "UK", "London", List(), 24),
    property(9, "Khalifa", Hotel, 5, "UAE", "Dubai", List(), 24),
    property(10, "Airbnb", Apartment, 4, "Belgium", "Brussels", List(), 24),
    property(11, "Airbnb", Apartment, 5, "Belgium", "Brussels", List(), 24),
    property(12, "Ferdowsi", Resort, 4, "Iran", "Mashhad", List(), 24),
    property(13, "Pasargad", Hotel, 5, "Iran", "Kish", List(), 24),
    property(14, "Homa", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(15, "Vista hill", Resort, 3, "Turkey", "Antalyia", List(), 24),
    property(16, "LUXA", Apartment, 4, "USA", "Las Vegas", List(), 24),
    property(17, "SID", Resort, 3, "UK", "London", List(), 24),
    property(18, "ARAM", Resort, 5, "UAE", "Dubai", List(), 24),
    property(19, "Kroonlaan", Hotel, 4, "Belgium", "Brussels", List(), 24),
    property(20, "Espinas", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(21, "Cornel", Resort, 4, "Turkey", "Istanbul", List(), 24),
    property(22, "Gold", Apartment, 3, "Belgium", "Brussels", List(), 24),
    property(23, "Taksim", Hotel, 5, "Iran", "Istanbul", List(), 24),
    property(24, "Abbasi", Resort, 5, "Iran", "Isfahan", List(), 24),
    property(25, "Eram", Apartment, 4, "Iran", "Shiraz", List(), 24)
  )
  var CLIENTS = List(
    clients("Ardavan Khalij", 23, "Y44986738"),
    clients("Harry Potter", 32, "Y44986739"),
    clients("Severus Snap", 58, "Y44986740"),
    clients("Albus Dumbledor", 143, "Y44986741"),
    clients("Tony Stark", 55, "Y44986742"),
    clients("Ted Mosby", 47, "Y44986743"),
    clients("Bella Swan", 24, "Y44986744"),
    clients("Ross Geller", 57, "Y44986745"),
    clients("Rachel Green", 56, "Y44986746"),
    clients("Petter Griffin", 68, "Y44986747")
  )
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
sealed trait PropertyType
case object Hotel extends PropertyType
case object Apartment extends PropertyType
case object Resort extends PropertyType
case object NON extends PropertyType
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
case object ReservationIsSuccessfull
case object ReservationIsFailed
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
case class clients(name: String, age: Int, passport_number: String)
case class property(id: Int, name: String, PropertyType: PropertyType, category: Int, country: String, city: String,
                    var NotAvailable: List[Date], Price: Double)
case class AvailableProperty(customerName: String, id: Int, name: String, PropertyType: PropertyType, category: Int,
                             country: String, city: String, price: Double, date: List[Date], actorRef: ActorRef)
case class AvailableProperties(var availableProperties: List[AvailableProperty])
case class SendProperty(customerName: String, date: Date, propertyType: PropertyType, actorRef: ActorRef)
case class SendPropertyName(customerName: String, date: Date, name: String, actorRef: ActorRef)
case class SendPropertyCategory(customerName: String, date: Date, category: Int, actorRef: ActorRef)
case class SendPropertyCountryCity(customerName: String, date: Date, country: String, city: String, actorRef: ActorRef)
case class SendPropertyCountryCityName(customerName: String, date: Date, country: String, city: String, name: String,
                                       actorRef: ActorRef)
case class SendPropertyCountryCityCategory(customerName: String, date: Date, country: String, city: String,
                                           category: Int, actorRef: ActorRef)
case class SendPropertyNameCategory(customerName: String, date: Date, category: Int, name: String, actorRef: ActorRef)
case class SendPropertyTypeName(customerName: String, date: Date, propertyType: PropertyType, name: String,
                                actorRef: ActorRef)
case class SendPropertyTypeCountryCity(customerName: String, date: Date, propertyType: PropertyType, country: String,
                                       city: String, actorRef: ActorRef)
case class SendPropertyTypeCountryCityName(customerName: String, date: Date, propertyType: PropertyType,
                                           country: String, city: String, name: String, actorRef: ActorRef)
case class RandomChosenDate(randomChosen: AvailableProperty, date: Date, replyTo: ActorRef)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Client (PASSPORT: String, DATE: Date, PT: PropertyType, NAME: String, CITY: String, COUNTRY: String,
              CATEGORY: Int, searchActor: ActorRef) extends Actor with ActorLogging {
  var CUSTOMER_NAME = ""
  CLIENTS.foreach { x =>
    if (x.passport_number == PASSPORT) {
      CUSTOMER_NAME = x.name
      println(s"Welcome to the system ${x.name}.")
      println("#######################################################################################################")
    }
  }
  if (DATE != null) {
    if (PT != NON) {
      if (NAME == "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for ${PT.toString}s with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendProperty(CUSTOMER_NAME, DATE, PT, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties with name $NAME , and availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyName(CUSTOMER_NAME, DATE, NAME, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for ${CATEGORY} star properties with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyCategory(CUSTOMER_NAME, DATE, CATEGORY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyCountryCity(CUSTOMER_NAME, DATE, COUNTRY, CITY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, with name $NAME and with availability " +
                s"on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyCountryCityName(CUSTOMER_NAME, DATE, COUNTRY, CITY, NAME, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, and with availability on " +
                s"${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyCountryCityCategory(CUSTOMER_NAME, DATE, COUNTRY, CITY, CATEGORY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for $PT with name, $NAME, and with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyNameCategory(CUSTOMER_NAME, DATE, CATEGORY, NAME, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $CATEGORY star $PT and with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyTypeName(CUSTOMER_NAME, DATE, PT, NAME, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $PT in $CITY, $COUNTRY, and with availability on ${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyTypeCountryCity(CUSTOMER_NAME, DATE, PT, COUNTRY, CITY, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME != "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $PT in $CITY, $COUNTRY, with name $NAME and with availability on " +
                s"${DATE.toString}...")
              println("##############################################################################################" +
                "#########")
              searchActor ! SendPropertyTypeCountryCityName(CUSTOMER_NAME, DATE, PT, COUNTRY, CITY, NAME, self)
            }
          }
        }
      }
    }
  }
  override def receive: Receive = {
    case AvailableProperties(availableProperties) => {
      var i = 1
      availableProperties.foreach { X =>
        println(s"$i- ${X.name} ${X.category.toString} star ${X.PropertyType.toString} with id number ${X.id} in " +
          s"${X.city}, ${X.country} is available on ${DATE} for ${X.price.toString} euros.")
        i = i + 1
      }
      println("#######################################################################################################")
      val random_var = new Random
      val randomChosen = availableProperties(random_var.nextInt(availableProperties.length))
      println(s"${randomChosen.customerName} choose ${randomChosen.name} ${randomChosen.category.toString} star " +
        s"${randomChosen.PropertyType.toString} with id number ${randomChosen.id} in ${randomChosen.city}, " +
        s"${randomChosen.country} for ${randomChosen.price.toString} euros.")
      println("#######################################################################################################")
      randomChosen.actorRef ! RandomChosenDate(randomChosen, DATE, self)
    }
    case ReservationIsFailed => {
      println(s"Reservation has been failed by $CUSTOMER_NAME.")
      println("#######################################################################################################")
      println("")
      println("")
    }
    case ReservationIsSuccessfull => {
      println(s"Reservation has been complete by $CUSTOMER_NAME.")
      println("#######################################################################################################")
      println("")
      println("")
    }
    case _ =>
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class SystemService extends Actor{
  override def receive: Receive = {
    case SendProperty(customerName, date, propertyType, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
              X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyName(customerName, date, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.name == name){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
              X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyCategory(customerName, date, category, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.category == category){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
              X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyCountryCity(customerName, date, country, city, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyCountryCityName(customerName, date, country, city, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              if (X.name == name){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                  X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
              }
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyCountryCityCategory(customerName, date, country, city, category, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              if (X.category == category){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                  X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
              }
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyNameCategory(customerName, date, category, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.category == category){
            if (X.name == name) {
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyTypeName(customerName, date, propertyType, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.name == name) {
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyTypeCountryCity(customerName, date, propertyType, country, city, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.country == country){
              if (X.city == city){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                  X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
              }
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case SendPropertyTypeCountryCityName(customerName, date, propertyType, country, city, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.country == country){
              if (X.city == city){
                if (X.name == name){
                  APS.availableProperties = APS.availableProperties :+ AvailableProperty(customerName, X.id, X.name,
                    X.PropertyType, X.category, X.country, X.city, X.Price, X.NotAvailable, self)
                }
              }
            }
          }
        }
      }
      println(s"Sending back the list for make a reservation by $customerName ...")
      println("#######################################################################################################")
      replyTo ! APS
    }
    case RandomChosenDate(availableProperty, date, replyTo) => {
      val childReservation = context.actorOf(Props(new ReservationService(date, replyTo)))
      childReservation ! availableProperty
    }
    case _ =>
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class ReservationService (DATE: Date, replyTo: ActorRef) extends Actor with ActorLogging {
  override def receive: Receive = {
    case AvailableProperty(customerName, id, name, propertyType, category, country, city, price, date, replyTo2) => {
      var index = PROPERTIES.indexOf(property(id, name, propertyType, category, country, city, date, price))
      if (PROPERTIES(index).NotAvailable.contains(DATE)) {
        replyTo ! ReservationIsFailed
      }
      else{
        PROPERTIES(index).NotAvailable = PROPERTIES(index).NotAvailable :+ DATE
        replyTo ! ReservationIsSuccessfull
      }
      context.stop(self)
    }
    case _ => 
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  val system = ActorSystem("Booking")
  val searchActor = system.actorOf(Props[SystemService], "search")
  val format = new SimpleDateFormat("yyyy-MM-dd")
  val date = format.parse("2022-09-11")
  val searchHotel = system.actorOf(Props(new Client("Y44986738", date, Hotel, "null", "null",
    "null", 0, searchActor)), "searchForHotel")
  Thread.sleep(1000)
  val searchApartment = system.actorOf(Props(new Client("Y44986739", date, Apartment, "null",
    "null", "null", 0, searchActor)), "searchForApartment")
  Thread.sleep(1000)
  val searchResort = system.actorOf(Props(new Client("Y44986740", date, Resort, "null",
    "null", "null", 0, searchActor)), "searchForResort")
  Thread.sleep(1000)
  val searchName = system.actorOf(Props(new Client("Y44986741", date, NON, "Eram", "null",
    "null", 0, searchActor)), "searchForName")
  Thread.sleep(1000)
  val search4 = system.actorOf(Props(new Client("Y44986742", date, NON, "null", "Tehran",
    "Iran", 5, searchActor)), "search4")
  Thread.sleep(1000)
  val search5 = system.actorOf(Props(new Client("Y44986743", date, NON, "null", "null",
    "null", 5, searchActor)), "search5")
  Thread.sleep(1000)
  system.terminate()
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
