object hammurabi extends App {
 
  def printIntroductoryMessage()  = {
    println("""Congratulations, you are the newest ruler of ancient Samaria, elected
  for a ten year term of office. Your duties are to dispense food, direct
  farming, and buy and sell land as needed to support your people. Watch
  out for rat infestations and the plague! Grain is the general currency,
  measured in bushels. The following will help you in your decisions:
  * Each person needs at least 20 bushels of grain per year to survive.
  * Each person can farm at most 10 acres of land.
  * It takes 2 bushels of grain to farm an acre of land.
  * The market price for land fluctuates yearly.
  Rule wisely and you will be showered with appreciation at the end of
  your term. Rule poorly and you will be kicked out of office!""")
  }
  
  def readInt(message: String): Int = {
    try {
      readLine(message).toInt
    } catch {
    case _ : Throwable =>
      println("That's not an integer. Please enter an integer.")
      readInt(message)
          }
  }
  
  def negativeNumber(response : Int) = {
    val result = response < 0
    if (result) 
      println("I'm afraid, great lord, we have not yet invented negative numbers")
    result
  }
  
  def askBuyLand(bushels : Int , price : Int) = {
    var acresToBuy = -1
    do {
      acresToBuy = readInt("How much land do you want to buy?")
      negativeNumber(acresToBuy)
  
      if (acresToBuy * price > bushels)
        println("I'm afraid, great lord, that we have only " + bushels +
                " and that would cost us " + (acresToBuy * price))          
    }
    while (acresToBuy < 0 || acresToBuy * price > bushels) 
    acresToBuy
  }
  
  def askSellLand(acres : Int, price : Int) = {
    
    var acresToSell = readInt("How much land do you want to sell?")
    while (acresToSell > acres || acresToSell < 0) {
      negativeNumber(acresToSell)
      if (acresToSell > acres) 
        println("I'm afraid, great lord, that we have only " + " acres")
      acresToSell = readInt("How much land do you want to sell?")
    }
    println("That cost us "+ acres*acresToSell + " bushels")
    acresToSell
    
  }
  def askFeedPeople(population : Int, bushelsInStorage : Int) = {
    var bushelsToFeed = readInt("How much grain should we feed the people?")
    while (bushelsToFeed < 0 || 
           bushelsToFeed > bushelsInStorage ||
           bushelsToFeed / 20  > population) {
      negativeNumber(bushelsToFeed)
      if (bushelsToFeed > bushelsInStorage) 
        println("We have only " + bushelsInStorage)
      else if (bushelsToFeed / 20  > population) 
        println("We have only "+ population + " and that would feed " + bushelsToFeed / 20)
    bushelsToFeed = readInt("How much grain should we feed the people?")
    }
           
  }

  def hammurabi() = {
    
    var starved = 0 // how many people starved
    var immigrants = 5 // how many people came to the city
    var population = 100
    var harvest = 3000 // total bushels harvested
    var bushelsPerAcre = 3 // amount harvested for each acre planted
    var rats_ate = 200 // bushels destroyed by rats
    var bushelsInStorage = 2800
    var acresOwned = 1000
    var pricePerAcre = 19 // each acre costs this many bushels
    var plagueDeaths = 0
    var year = 0
    
    
    printIntroductoryMessage()
    
    for (year <- 1 to 10) {
          println("O great Hammurabi!")
          println("You are in year " + year + " of your ten year rule.")
          println("In the previous year " + starved + " people starved to death.")
          println("In the previous year " + immigrants + " people entered the kingdom.")
          println("The population is now " + population + ".")
          println("We harvested " + harvest + " bushels at " + bushelsPerAcre + " bushels per acre.")
          println("Rats destroyed " + rats_ate + " bushels, leaving " + bushelsInStorage + "bushels in storage.")
          println("The city owns " + acresOwned + " acres of land.")
          println("Land is currently worth " + pricePerAcre + "bushels per acre.")
          println("There were " + plagueDeaths + " deaths from the plague.")
          println("")
          
          val landBought = askBuyLand(bushelsInStorage, pricePerAcre)
          val landSold   = askSellLand(acresOwned, pricePerAcre)
          val peopleFed  = askFeedPeople(population, bushelsInStorage)
          
    }
               
  }
  
  hammurabi()
  println("Check 5")
  
}