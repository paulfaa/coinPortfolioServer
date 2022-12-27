# coinPortfolioServer
Spring boot REST app utilizing CoinMarketCap API

###### Dependencies
- Spring Boot Starter 2.5.6
- JUnit 4.12

###### Endpoints:
@GetMapping("/getCoin") 
>parameter is CoinMarketCap coin id

@GetMapping("/getRate") 
>parameter is coin name and currency to return rate for

